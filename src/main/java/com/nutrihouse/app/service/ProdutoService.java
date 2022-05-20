package com.nutrihouse.app.service;

import com.nutrihouse.app.domain.Produto;
import com.nutrihouse.app.dto.ProdutoDto;
import com.nutrihouse.app.enums.TipoCadastro;
import com.nutrihouse.app.repositories.ProdutoRepository;
import com.nutrihouse.app.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repo;

    public Produto find(Integer id){
        Optional<Produto> produto = repo.findById(id);
        return produto.orElseThrow(() -> new ObjectNotFoundException("Produto nao encontrado!"));
    }

    public List<Produto> findAll(){
        return repo.findAll();
    }

    public ProdutoDto save(ProdutoDto produtoDto){
        Produto produto = fromDto(produtoDto);
        repo.save(produto);
        return toDto(produto);
    }

    public Produto update(Produto produto){
        Produto updatedProduto = find(produto.getId());
        updateData(updatedProduto, produto);
        return repo.save(updatedProduto);
    }

    public void delete(Integer id){
        Produto produto = find(id);
        produto.setTipoCadastro(TipoCadastro.DESATIVO);
        try{
            repo.delete(produto);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Produto nao pode ser excluido pois possui movimentação atrelada! Produto desativado!");
        }
    }

    public void updateData(Produto updatedProduto, Produto produto){
        updatedProduto.setNome(produto.getNome());
        if(produto.getDescricao() != null) {
            updatedProduto.setDescricao(produto.getDescricao());
        }
        if(produto.getCodBarras() != null){
            updatedProduto.setCodBarras(produto.getCodBarras());
        }
        if(produto.getQuantidade() != null){
            updatedProduto.setQuantidade(produto.getQuantidade());
        }
            updatedProduto.setTipoCadastro(TipoCadastro.ATIVO);

    }

    private ProdutoDto toDto(Produto produto) {
        ProdutoDto produtoDto = ProdutoDto.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .codBarras(produto.getCodBarras())
                .tipoCadastro(produto.getTipoCadastro())
                .descricao(produto.getDescricao())
                .quantidade(produto.getQuantidade())
                .build();
        return produtoDto;
    }

    public Produto fromDto(ProdutoDto produtoDto) {
        Produto produto = Produto.builder()
                .id(produtoDto.getId())
                .nome(produtoDto.getNome())
                .codBarras(produtoDto.getCodBarras())
                .tipoCadastro(produtoDto.getTipoCadastro())
                .descricao(produtoDto.getDescricao())
                .quantidade(produtoDto.getQuantidade())
                .build();
        return produto;
    }

}
