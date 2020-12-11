package com.gft.desafioapi.repository;

import com.gft.desafioapi.model.Cliente;
import com.gft.desafioapi.model.Fornecedor;
import com.gft.desafioapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor,Long> {
    public List<Fornecedor> findByNomeContaining(String nome);
    public List<Fornecedor> findAllByOrderByNomeAsc();
    public List<Fornecedor> findAllByOrderByNomeDesc();
}
