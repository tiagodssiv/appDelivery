package com.vaptvupt.com.vaptvupt.DB_helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class ConexaoSqlite extends SQLiteOpenHelper {

    private static ConexaoSqlite INSTANCIA_CONEXAO;
    private static final int VERSAO_DB = 1;
    private static final String NOME_DB=" bl_produtos_app";
    public ConexaoSqlite(@Nullable Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    public static ConexaoSqlite getInstanciaConexao(Context context) {
        if(INSTANCIA_CONEXAO==null){

            INSTANCIA_CONEXAO= new ConexaoSqlite(context);

        }

        return INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String sqlTabelaProdutos ="CREATE TABLE carrinho  ( " +
                " id INTEGER PRIMARY KEY  AUTOINCREMENT, cod_position INTEGER , "  +
                " preco REAL , "
                +" titulo TEXT , " +
                " preco_itens REAL , " +
                " qnt INTEGER ," +
                " img BLOB," +
                "status TEXT )";
        db.execSQL(sqlTabelaProdutos);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
