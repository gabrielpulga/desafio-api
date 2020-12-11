package com.gft.desafioapi.controller;

import com.gft.desafioapi.model.Fornecedor;
import com.gft.desafioapi.model.Fornecedor;
import com.gft.desafioapi.repository.FornecedorRepository;
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

@Api(tags = "Fornecedor")
@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @ApiOperation("Criar um novo fornecedor")
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Fornecedor> criar(@ApiParam(name = "corpo", value = "Representação de um novo fornecedor") @Validated @RequestBody Fornecedor fornecedor, HttpServletResponse httpServletResponse) {
        Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedor);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(fornecedorSalvo.getId()).toUri();
        httpServletResponse.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(fornecedorSalvo);
    }

    @ApiOperation("Buscar um fornecedor pelo seu ID")
    @GetMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Fornecedor> buscarPeloid(@ApiParam(value = "ID", example = "1") @PathVariable Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id).orElse(null);
        return fornecedor != null ? ResponseEntity.ok(fornecedor) : ResponseEntity.notFound().build();
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todos os fornecedors por nome")
    @GetMapping("/nome/{nome}")
    public List<Fornecedor> listarPorNome(@PathVariable String nome) {
        return fornecedorRepository.findByNomeContaining(nome);
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todos os fornecedors por nome em ordem crescente")
    @GetMapping("/asc")
    public List<Fornecedor> listarPorNomeOrdemCrescente() {
        return fornecedorRepository.findAllByOrderByNomeAsc();
    }


    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todos os fornecedors por nome em ordem decrescente")
    @GetMapping("/desc")
    public List<Fornecedor> listarPorNomeOrdemDecrescente() {
        return fornecedorRepository.findAllByOrderByNomeDesc();
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todos os fornecedores")
    @GetMapping
    public List<Fornecedor> listar() {
        return fornecedorRepository.findAll();
    }

    @ApiOperation("Deletar um fornecedor")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public void deletarPeloid(@ApiParam(value = "ID", example = "1") @PathVariable Long id) {
        Fornecedor fornecedorDeletado = fornecedorRepository.getOne(id);
        fornecedorRepository.delete(fornecedorDeletado);
    }

    @ApiOperation("Atualizar um fornecedor")
    @PutMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Fornecedor> atualizar(@ApiParam(value = "ID", example = "1") @PathVariable Long id, @ApiParam(name = "corpo", value = "Representação de um novo fornecedor") @Validated @RequestBody Fornecedor fornecedor) {
        Fornecedor fornecedorSalvo = fornecedorRepository.getOne(id);
        if (fornecedorSalvo == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(fornecedor, fornecedorSalvo, "id");
        fornecedorRepository.save(fornecedorSalvo);
        return ResponseEntity.ok(fornecedorSalvo);
    }
}

