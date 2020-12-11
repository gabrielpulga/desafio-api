package com.gft.desafioapi.controller;

import com.gft.desafioapi.model.Cliente;
import com.gft.desafioapi.model.Cliente;
import com.gft.desafioapi.repository.ClienteRepository;
import com.gft.desafioapi.repository.ClienteRepository;
import com.gft.desafioapi.repository.ClienteRepository;
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

@Api(tags = "Cliente")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @ApiOperation("Criar um novo cliente")
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Cliente> criar(@ApiParam(name = "corpo", value = "Representação de um novo cliente") @Validated @RequestBody Cliente cliente, HttpServletResponse httpServletResponse) {
        Cliente clienteSalvo = clienteRepository.save(cliente);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(clienteSalvo.getId()).toUri();
        httpServletResponse.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(clienteSalvo);
    }

    @ApiOperation("Buscar um cliente pelo seu ID")
    @GetMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Cliente> buscarPeloid(@ApiParam(value = "ID", example = "1") @PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todos os clientes")
    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todos os clientes por nome")
    @GetMapping("/nome/{nome}")
    public List<Cliente> listarPorNome(@PathVariable String nome) {
        return clienteRepository.findByNomeContaining(nome);
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todos os clientes por nome em ordem crescente")
    @GetMapping("/nome/asc")
    public List<Cliente> listarPorNomeOrdemCrescente() {
        return clienteRepository.findAllByOrderByNomeAsc();
    }


    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todos os clientes por nome em ordem decrescente")
    @GetMapping("/nome/desc")
    public List<Cliente> listarPorNomeOrdemDecrescente() {
        return clienteRepository.findAllByOrderByNomeDesc();
    }

    @ApiOperation("Deletar um cliente")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public void deletarPeloid(@ApiParam(value = "ID", example = "1") @PathVariable Long id) {
        Cliente clienteDeletado = clienteRepository.getOne(id);
        clienteRepository.delete(clienteDeletado);
    }

    @ApiOperation("Atualizar um cliente")
    @PutMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Cliente> atualizar(@ApiParam(value = "ID", example = "1") @PathVariable Long id, @ApiParam(name = "corpo", value = "Representação de um novo cliente") @Validated @RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.getOne(id);
        if (clienteSalvo == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(cliente, clienteSalvo, "id");
        clienteRepository.save(clienteSalvo);
        return ResponseEntity.ok(clienteSalvo);
    }
}

