package com.gft.desafioapi.repository;

import com.gft.desafioapi.model.Cliente;
import com.gft.desafioapi.model.Fornecedor;
import com.gft.desafioapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public List<Cliente> findByNomeContaining(String nome);
    public List<Cliente> findAllByOrderByNomeAsc();
    public List<Cliente> findAllByOrderByNomeDesc();
}
