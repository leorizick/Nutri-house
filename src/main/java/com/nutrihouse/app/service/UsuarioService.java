package com.nutrihouse.app.service;

import com.nutrihouse.app.domain.Produto;
import com.nutrihouse.app.domain.Usuario;
import com.nutrihouse.app.dto.NewUsuarioDto;
import com.nutrihouse.app.enums.TipoCadastro;
import com.nutrihouse.app.repositories.UsuarioRepository;
import com.nutrihouse.app.security.UserSecurity;
import com.nutrihouse.app.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private UsuarioRepository repo;

    public static UserSecurity authenticated(){
        try{
            return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return null;
        }
    }

    public Usuario find(Integer id){
        Optional<Usuario> usuario = repo.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuario de id: " + id + ", não encontrado!"));
    }

    public List<Usuario> findAll(){
        return repo.findAll();
    }



    public Usuario save(Usuario usuario){
        usuario = repo.save(usuario);
        return usuario;
    }

    public Usuario update(Usuario usuario){
        Usuario updatedUsuario = find(usuario.getId());
        updateUsuario(usuario, updatedUsuario);
       return repo.save(updatedUsuario);
    }

    public void delete(Integer id){
        Usuario usuario = find(id);
        try{
            repo.delete(usuario);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Usuario nao pode ser excluido pois possui movimentação atrelada!");
        }
    }

    public Usuario fromDto(NewUsuarioDto newUsuarioDto){
        Usuario usuario = new Usuario(null, newUsuarioDto.getUsername(), newUsuarioDto.getPassword());
        return usuario;
    }


    private Usuario updateUsuario(Usuario usuario, Usuario updatedUsuario){
            updatedUsuario.setPassword(pe.encode(usuario.getPassword()));
        if(usuario.getUsername() != null)
            updatedUsuario.setUsername(usuario.getUsername());
        return updatedUsuario;
    }
}
