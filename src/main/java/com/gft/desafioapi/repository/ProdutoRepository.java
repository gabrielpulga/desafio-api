package com.gft.desafioapi.repository;

import com.gft.desafioapi.model.Cliente;
import com.gft.desafioapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public List<Produto> findByNomeContaining(String nome);
    public List<Produto> findAllByOrderByNomeAsc();
    public List<Produto> findAllByOrderByNomeDesc();
}
