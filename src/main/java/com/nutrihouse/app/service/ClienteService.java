package com.nutrihouse.app.service;

import com.nutrihouse.app.domain.Cliente;
import com.nutrihouse.app.dto.ClienteDto;
import com.nutrihouse.app.enums.TipoCadastro;
import com.nutrihouse.app.repositories.ClienteRepository;
import com.nutrihouse.app.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @Autowired
    private ClienteRepository repo;


    public Cliente find(Integer id){
        Optional<Cliente> obj =repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id ));
    }

    public List<Cliente> findAll(){
        return repo.findAll();
    }

    public ClienteDto save(ClienteDto clienteDto){
        Cliente cliente = fromDto(clienteDto);
        repo.save(cliente);
        return toDto(cliente);
    }

    public Cliente update(Cliente cliente){
        Cliente updatedCliente = find(cliente.getId());
        updateData(updatedCliente, cliente);
        return repo.save(updatedCliente);
    }

    public void updateData(Cliente updatedCliente, Cliente cliente) {
        updatedCliente.setNome(cliente.getNome());
        if(cliente.getDescricao() != null) {
            updatedCliente.setDescricao(cliente.getDescricao());
        }
        if(cliente.getDocumento() != null){
            updatedCliente.setDocumento(cliente.getDocumento());
        }
        if(cliente.getTipoCadastro() != TipoCadastro.ATIVO){
            updatedCliente.setTipoCadastro(cliente.getTipoCadastro());
        }
    }

    public void delete(Integer id){
        Cliente cliente = find(id);
        cliente.setTipoCadastro(TipoCadastro.DESATIVO);
        repo.save(cliente);
        try{
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Não é possivel excluir um cliente que possua dependencias! Cliente desativado!");
        }
    }


    private ClienteDto toDto(Cliente cliente) {
        ClienteDto clienteDto = ClienteDto.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .descricao(cliente.getDescricao())
                .tipoCliente(cliente.getTipoCliente())
                .documento(cliente.getDocumento())
                .build();

        return clienteDto;
    }

    public Cliente fromDto(ClienteDto clienteDto){
        Cliente cliente = Cliente.builder()
                .nome(clienteDto.getNome())
                .descricao(clienteDto.getDescricao())
                .tipoCliente(clienteDto.getTipoCliente())
                .documento(clienteDto.getDocumento())
                .build();
        return cliente;
    }
}
