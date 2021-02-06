package com.vaptvupt.com.vaptvupt.controller;

import com.vaptvupt.com.vaptvupt.Carrinho;
import com.vaptvupt.com.vaptvupt.DAO.ProdutoDao;
import com.vaptvupt.com.vaptvupt.DB_helper.ConexaoSqlite;
import com.vaptvupt.com.vaptvupt.model.Produto;

import java.util.List;

public class ProdutoController {

    private  final ProdutoDao produtoDao;

    public ProdutoController(ConexaoSqlite conexaoSqlite) {
        produtoDao = new ProdutoDao(conexaoSqlite);
    }



    public long salvarProdutoCtrl(Carrinho carrinho){

        return this.produtoDao.salvarProdutoDao(carrinho);

    }


    public List<Carrinho>getListaProdutoCTRL(){
        return  this.produtoDao.getListaProdutoDao();

    }

    public boolean excluirProdutoCtrl(long prduto){

        return this.produtoDao.excluirProduto(prduto);
    }

}
