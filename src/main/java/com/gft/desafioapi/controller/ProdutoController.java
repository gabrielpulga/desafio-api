package com.gft.desafioapi.controller;

import com.gft.desafioapi.model.Produto;
import com.gft.desafioapi.model.Produto;
import com.gft.desafioapi.repository.ProdutoRepository;
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

@Api(tags = "Produto")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @ApiOperation("Criar um novo produto")
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Produto> criar(@ApiParam(name = "corpo", value = "Representação de um novo produto") @Validated @RequestBody Produto produto, HttpServletResponse httpServletResponse) {
        Produto produtoSalvo = produtoRepository.save(produto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(produtoSalvo.getId()).toUri();
        httpServletResponse.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(produtoSalvo);
    }

    @ApiOperation("Buscar um produto pelo seu ID")
    @GetMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Produto> buscarPeloid(@ApiParam(value = "ID", example = "1") @PathVariable Long id) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todos os produtos")
    @GetMapping
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todos os produtos por nome")
    @GetMapping("/nome/{nome}")
    public List<Produto> listarPorNome(@PathVariable String nome) {
        return produtoRepository.findByNomeContaining(nome);
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todos os produtos por nome em ordem crescente")
    @GetMapping("/asc")
    public List<Produto> listarPorNomeOrdemCrescente() {
        return produtoRepository.findAllByOrderByNomeAsc();
    }


    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todos os produtos por nome em ordem decrescente")
    @GetMapping("/desc")
    public List<Produto> listarPorNomeOrdemDecrescente() {
        return produtoRepository.findAllByOrderByNomeDesc();
    }

    @ApiOperation("Deletar um produto")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public void deletarPeloid(@ApiParam(value = "ID", example = "1") @PathVariable Long id) {
        Produto produtoDeletado = produtoRepository.getOne(id);
        produtoRepository.delete(produtoDeletado);
    }

    @ApiOperation("Atualizar um produto")
    @PutMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Produto> atualizar(@ApiParam(value = "ID", example = "1") @PathVariable Long id, @ApiParam(name = "corpo", value = "Representação de um novo produto") @Validated @RequestBody Produto produto) {
        Produto produtoSalvo = produtoRepository.getOne(id);
        if (produtoSalvo == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(produto, produtoSalvo, "id");
        produtoRepository.save(produtoSalvo);
        return ResponseEntity.ok(produtoSalvo);
    }
}
