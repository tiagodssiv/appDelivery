package com.vaptvupt.com.vaptvupt;

import android.app.Activity;

import java.util.List;

/**
 * Created by Usuario on 17/08/2019.
 */

public class Carrinho {
    Activity activity;
public List<Integer>apagar;
    public int getId_banco() {
        return id_banco;
    }

    public void setId_banco(int id_banco) {
        this.id_banco = id_banco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
    private int id_banco;
List<Carrinho>listCarrinho;
    public Double getValotal_itens_adapter() {
        return valotal_itens_adapter;
    }

    public void setValotal_itens_adapter(Double valotal_itens_adapter) {
        this.valotal_itens_adapter = valotal_itens_adapter;
    }

    Double valotal_itens_adapter;
    private int imagem;

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getPreco_item() {
        return preco_item;
    }

    public void setPreco_item(Double preco_item) {
        this.preco_item = preco_item;
    }

    public Double getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(Double preco_total) {
        this.preco_total = preco_total;
    }

    private int qnt;
    private String titulo;
    private Double preco;
    private Double preco_item;
    private Double preco_total;

    public int getId_carrinho() {
        return id_carrinho;
    }

    public void setId_carrinho(int id_carrinho) {
        this.id_carrinho = id_carrinho;
    }

    private int id_carrinho;

    public byte[] getImg_byte() {
        return img_byte;
    }

    public void setImg_byte(byte[] img_byte) {
        this.img_byte = img_byte;
    }

    private byte[] img_byte;









}
