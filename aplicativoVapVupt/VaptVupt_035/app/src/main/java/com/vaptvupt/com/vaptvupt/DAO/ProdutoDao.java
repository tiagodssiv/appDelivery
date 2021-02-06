package com.vaptvupt.com.vaptvupt.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vaptvupt.com.vaptvupt.Carrinho;
import com.vaptvupt.com.vaptvupt.DB_helper.ConexaoSqlite;
import com.vaptvupt.com.vaptvupt.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {

    private ConexaoSqlite conexaoSqlite;
    public  ProdutoDao (ConexaoSqlite conexaoSqlite){

        this.conexaoSqlite=conexaoSqlite;
    }

    public long salvarProdutoDao(Carrinho carrinho){
        SQLiteDatabase db = conexaoSqlite.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("cod_position",carrinho.getId_carrinho());
            values.put("preco",carrinho.getPreco());
            values.put("titulo",carrinho.getTitulo());
            values.put("preco_itens",carrinho.getPreco_item());
            values.put("qnt",carrinho.getQnt());
            // retorna o último id inserido o banco
         long id_ProdutoInserido =   db.insert("carrinho",null,values);
            return id_ProdutoInserido;

        }catch (Exception e ){


        }finally {
            if(db != null){
                db.close();
            }
        }

       return 0;
    }



    public List<Carrinho>getListaProdutoDao(){
        List<Carrinho>listaProduto = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor c;
        String query ="SELECT * FROM carrinho";
        try{
            db= this.conexaoSqlite.getReadableDatabase();
            c=db.rawQuery(query,null);

            if(c.moveToFirst()){

                Carrinho carrinho = null;

                do{

                     carrinho = new Carrinho();


                    carrinho.setId_banco(c.getInt(0));
                    carrinho.setId_carrinho(c.getInt(1));

                    carrinho.setPreco(c.getDouble(2));
                    carrinho.setTitulo(c.getString(3));
                    carrinho.setPreco_item(c.getDouble(4));

                    carrinho.setQnt(c.getInt(5));
                    carrinho.setStatus(c.getString(7));

                    listaProduto.add(carrinho);



                }while (c.moveToNext());

            }



        }
        catch (Exception e){}
        finally {
            if(db != null){
                db.close();
            }
            return  listaProduto;
        }




    }
    public boolean excluirProduto(long produto){
        SQLiteDatabase db=null;


        try{
            db=this.conexaoSqlite.getReadableDatabase();
            db.delete("carrinho","cod_position = ?",new String[]{String.valueOf(produto)});

        }catch (Exception e ){

            return false;
        }finally {
            if(db!= null){

                db.close();
            }
        }
        return true;
    }
}
