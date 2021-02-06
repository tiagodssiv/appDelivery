package com.vaptvupt.com.vaptvupt;

import java.io.Serializable;

/**
 * Created by Usuario on 15/08/2019.
 */

public class Produtos implements Serializable {



    public int getImagem_opiniao() {
        return imagem_opiniao;
    }

    public void setImagem_opiniao(int imagem_opiniao) {
        this.imagem_opiniao = imagem_opiniao;
    }




    private int imagem_opiniao;

    public Produtos(int imagem_opiniao, int image, int id, int posicao, String descricao, String nome, Double preco) {
        this.imagem_opiniao = imagem_opiniao;
        this.image = image;
        this.id = id;
        this.posicao = posicao;
        this.descricao = descricao;
        this.nome = nome;
        this.preco = preco;
    }

    private int image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    private int id;
    private int posicao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private String descricao;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    private String nome;
    private Double preco;
}
