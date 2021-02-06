package com.vaptvupt.com.vaptvupt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 10/11/2017.
 */

public class DBhelper extends SQLiteOpenHelper{




    private static String NOME_BASE="CarrinhoDeCompras";
    private static int VERSAO_BASE=1;


    public DBhelper(Context context) {
        super(context, NOME_BASE, null, VERSAO_BASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    String sqlCreateTabelaLivro ="CREATE TABLE carrinho  ( " +
            " id INTEGER PRIMARY KEY  AUTOINCREMENT," +
            " cod_position INTEGER , "  +
            " preco DOUBLE , "
            +" titulo TEXT , " +
            " preco_itens DOUBLE , " +
            " qnt INTEGER ," +
            " img BLOB," +
            "status INTEGER )";

        db.execSQL(sqlCreateTabelaLivro);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlDropTableCarrinho = " DROP TABLE carrinho";
        db.execSQL(sqlDropTableCarrinho );
        onCreate( db);
    }


    public void insertCarrinho(Carrinho carrinho) {

        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
     //   cv.put("id", carrinho.getId_carrinho());
        cv.put("cod_position", carrinho.getId_carrinho());

        cv.put("titulo", carrinho.getTitulo());
        cv.put("preco",carrinho.getPreco());
        cv.put("preco_itens", carrinho.getPreco_item());
        cv.put("qnt", carrinho.getQnt());
        cv.put("status", Integer.parseInt(carrinho.getStatus()));

   //     cv.put("img", carrinho.getImg_byte());
        db.insert("carrinho",null,cv);
        db.close();


    }








    public List<Carrinho> selectItensCarrinho() {

int vlor4=1;
        List<Carrinho> listLivros = new ArrayList<Carrinho>();
        SQLiteDatabase db = getReadableDatabase();
        String itensCarrinho = "SELECT * FROM carrinho WHERE status = '"+vlor4+"'";

        Cursor c = db.rawQuery(itensCarrinho, null);
        if (c.moveToFirst()) {
            do {
            //    CarrinhoTeste carrinho = new CarrinhoTeste(c.getString(2),c.getDouble(1),c.getDouble(3),c.getInt(4),0);
                Carrinho carrinho = new Carrinho();


                carrinho.setId_banco(c.getInt(0));
                carrinho.setId_carrinho(c.getInt(1));

                carrinho.setPreco(c.getDouble(2));
                carrinho.setTitulo(c.getString(3));
                carrinho.setPreco_item(c.getDouble(4));

                carrinho.setQnt(c.getInt(5));
                carrinho.setStatus(String.valueOf(c.getInt(7)));
              //  carrinho.setImg_byte(c.getBlob(6));

                   //       carrinho.setPreco_total(c.getDouble(6));

                listLivros.add(carrinho);
            }
            while (c.moveToNext());
        }
db.close();
        return listLivros;
    }





    public void  mostarCarrinhoLivre(int id){



        SQLiteDatabase db = getReadableDatabase();

        String sqltodosItens = "SELECT * FROM carrinho WHERE  cod_position = ' "+id+"' " ;

        Cursor c = db.rawQuery(sqltodosItens, null);
        if (c.moveToFirst()) {
            Carrinho carrinho = new Carrinho();

            carrinho.setId_banco(c.getInt(0));

            carrinho.setId_carrinho(c.getInt(1));
            carrinho.setPreco(c.getDouble(2));
            carrinho.setTitulo(c.getString(3));
            carrinho.setPreco_item(c.getDouble(4));

            carrinho.setQnt(c.getInt(5));
            carrinho.setStatus(c.getString(7));

            db.close();

        }




    }




public void mostarLivro(Carrinho carrinho){



    SQLiteDatabase db = getReadableDatabase();
    String sqltodosItens = "SELECT * FROM carrinho WHERE  cod_position = ' " + carrinho.getId_carrinho()+ " ' " ;

    Cursor c = db.rawQuery(sqltodosItens, null);
    if (c.moveToFirst()) {

        carrinho.setId_banco(c.getInt(0));

        carrinho.setId_carrinho(c.getInt(1));
        carrinho.setPreco(c.getDouble(2));
        carrinho.setTitulo(c.getString(3));
        carrinho.setPreco_item(c.getDouble(4));

        carrinho.setQnt(c.getInt(5));
        carrinho.setStatus(c.getString(7));
        //  carrinho.setImg_byte(c.getBlob(6));

        db.close();

     }





}

public void aualizarCarrinho( int position){
        int carrinho =0;
    SQLiteDatabase db = getWritableDatabase();
    db.execSQL(" UPDATE carrinho SET status = ' " +carrinho+"'" + " WHERE cod_position = '"+position+" ' ");
   db.close();
    // db.execSQL("UPDATE  SET YOUR_COLUMN='newValue' WHERE id=6 ");



}

   public  void apagarCarrinho (Carrinho carrinho) {

       SQLiteDatabase db = getReadableDatabase();
       String sqltodosItens = "DELETE FROM carrinho WHERE cod_position = ' "+ carrinho.getId_carrinho()+"' ";
       db.execSQL(sqltodosItens);
       db.close();

     }


    public  void apagarCarrinho2 (int carrinho) {

        SQLiteDatabase db = getReadableDatabase();
        String sqltodosItens = "DELETE FROM carrinho WHERE id = ' " + carrinho + "' ";
        db.execSQL(sqltodosItens);
        db.close();

    }




    public List<Carrinho> selecionaItensNaoExcuidos() {
int vaor3=1;

        List<Carrinho> listLivros = new ArrayList<Carrinho>();
        SQLiteDatabase db = getReadableDatabase();
        String itensCarrinho = "SELECT * FROM carrinho WHERE status ='"+vaor3+"'";

        Cursor c = db.rawQuery(itensCarrinho, null);
        if (c.moveToFirst()) {
            do {
                //    CarrinhoTeste carrinho = new CarrinhoTeste(c.getString(2),c.getDouble(1),c.getDouble(3),c.getInt(4),0);
                Carrinho carrinho = new Carrinho();


                carrinho.setId_banco(c.getInt(0));
                carrinho.setId_carrinho(c.getInt(1));

                carrinho.setPreco(c.getDouble(2));
                carrinho.setTitulo(c.getString(3));
                carrinho.setPreco_item(c.getDouble(4));

                carrinho.setQnt(c.getInt(5));
                carrinho.setStatus(String.valueOf(c.getString(7)));
                //  carrinho.setImg_byte(c.getBlob(6));

                //       carrinho.setPreco_total(c.getDouble(6));

                listLivros.add(carrinho);
            }
            while (c.moveToNext());
        }
        db.close();
        return listLivros;
    }





}


