package com.gft.desafioapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@ApiModel(value = "Produto", description = "Representa um produto")
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    @ApiModelProperty(hidden = true)
    private String idProduto;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Boolean promocao;

    @NotNull
    private BigDecimal valorPromo;

    @NotNull
    private String categoria;

    @NotNull
    private String imagem;

    @NotNull
    private Long quantidade;

    @OneToOne
    @JsonIgnoreProperties({"nome", "cnpj", "produtos"})
    private Fornecedor fornecedor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonIgnore
    public String getidProduto() {
        return idProduto;
    }

    @JsonIgnore
    public void setidProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Boolean getPromocao() {
        return promocao;
    }

    public void setPromocao(Boolean promocao) {
        this.promocao = promocao;
    }

    public BigDecimal getValorPromo() {
        return valorPromo;
    }

    public void setValorPromo(BigDecimal valorPromo) {
        this.valorPromo = valorPromo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
