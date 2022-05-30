package com.nutrihouse.app.service;

import com.nutrihouse.app.domain.Produto;
import com.nutrihouse.app.domain.Usuario;
import com.nutrihouse.app.enums.TipoCadastro;
import com.nutrihouse.app.repositories.UsuarioRepository;
import com.nutrihouse.app.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private UsuarioRepository repo;

    public Usuario find(Integer id){
        Optional<Usuario> usuario = repo.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuario de id: " + id + ", não encontrado!"));
    }

    public Usuario save(Usuario usuario){
        usuario.setPassword(pe.encode(usuario.getPassword()));
        repo.save(usuario);
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


    private Usuario updateUsuario(Usuario usuario, Usuario updatedUsuario){
        updatedUsuario.setPassword(pe.encode(usuario.getPassword()));
        if(usuario.getUsername() != null)
            updatedUsuario.setUsername(usuario.getUsername());
        return updatedUsuario;
    }
}
