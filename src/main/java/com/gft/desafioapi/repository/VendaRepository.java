package com.gft.desafioapi.repository;

import com.gft.desafioapi.model.Fornecedor;
import com.gft.desafioapi.model.Produto;
import com.gft.desafioapi.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    public List<Venda> findAllByOrderByDataCompraAsc();
    public List<Venda> findAllByOrderByDataCompraDesc();
}
