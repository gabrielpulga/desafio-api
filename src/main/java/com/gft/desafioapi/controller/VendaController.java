package com.gft.desafioapi.controller;

import com.gft.desafioapi.model.Cliente;
import com.gft.desafioapi.model.Venda;
import com.gft.desafioapi.model.Venda;
import com.gft.desafioapi.repository.VendaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@Api(tags = "Venda")
@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaRepository vendaRepository;

    @ApiOperation("Criar uma nova venda")
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Venda> criar(@ApiParam(name = "corpo", value = "Representação de uma nova venda") @Validated @RequestBody Venda venda, HttpServletResponse httpServletResponse) {
        Venda vendaSalvo = vendaRepository.save(venda);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(vendaSalvo.getId()).toUri();
        httpServletResponse.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(vendaSalvo);
    }

    @ApiOperation("Buscar uma venda pelo seu ID")
    @GetMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Venda> buscarPeloid(@ApiParam(value = "ID", example = "1") @PathVariable Long id) {
        Venda venda = vendaRepository.findById(id).orElse(null);
        return venda != null ? ResponseEntity.ok(venda) : ResponseEntity.notFound().build();
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todas as vendas")
    @GetMapping
    public List<Venda> listar() {
        return vendaRepository.findAll();
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todas as vendas por data de compra em ordem crescente")
    @GetMapping("/asc")
    public List<Venda> listarPorNomeOrdemCrescente() {
        return vendaRepository.findAllByOrderByDataCompraAsc();
    }


    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todas as vendas por data de compra em ordem decrescente")
    @GetMapping("/desc")
    public List<Venda> listarPorNomeOrdemDecrescente() {
        return vendaRepository.findAllByOrderByDataCompraDesc();
    }

    @ApiOperation("Deletar uma venda")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public void deletarPeloid(@ApiParam(value = "ID", example = "1") @PathVariable Long id) {
        Venda vendaDeletado = vendaRepository.getOne(id);
        vendaRepository.delete(vendaDeletado);
    }

    @ApiOperation("Atualizar uma venda")
    @PutMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Venda> atualizar(@ApiParam(value = "ID", example = "1") @PathVariable Long id, @ApiParam(name = "corpo", value = "Representação de uma nova venda") @Validated @RequestBody Venda venda) {
        Venda vendaSalvo = vendaRepository.getOne(id);
        if (vendaSalvo == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(venda, vendaSalvo, "id");
        vendaRepository.save(vendaSalvo);
        return ResponseEntity.ok(vendaSalvo);
    }
}
