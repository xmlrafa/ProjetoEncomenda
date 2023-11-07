package com.zelly.encomendas.encomendaszelly.service.produto;

import com.zelly.encomendas.encomendaszelly.model.produtoEntity;
import com.zelly.encomendas.encomendaszelly.repository.produtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private produtoRepository produtoRepository;

    public produtoEntity buscarProdutoPorId(Long id) {
        Optional<produtoEntity> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isPresent()) {
            return produtoOptional.get();
        } else {
            // Lide com a situação em que o produto com o ID fornecido não foi encontrado
            // Pode lançar uma exceção ou retornar um valor padrão, dependendo do seu caso de uso
           //throw new Exception(msg, "Produto com ID " + id + " não encontrado.");
            return null;
        }
    }
}
