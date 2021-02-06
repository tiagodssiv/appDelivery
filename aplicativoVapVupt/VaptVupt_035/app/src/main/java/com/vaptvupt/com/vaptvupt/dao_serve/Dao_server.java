package com.vaptvupt.com.vaptvupt.dao_serve;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.ConvertFuture;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.vaptvupt.com.vaptvupt.Carrinho;
import com.vaptvupt.com.vaptvupt.Globals;
import com.vaptvupt.com.vaptvupt.MainActivity;
import com.vaptvupt.com.vaptvupt.Pedidos;

import java.util.ArrayList;
import java.util.List;

public class Dao_server {
    int idRetornado;
    int id = 0;
    Carrinho car = new Carrinho();
List<Carrinho>lista = new ArrayList<>();
    String data_teste="30/10/2019";
    String URL="http://sistemerc.freetzi.com/vaptvupt_app/";
    Context context;
    public Dao_server(Context context , List<Carrinho>li){
        this.context=context;
        this.lista=li;
    }

   /* public int  salvarPedido(final Pedidos pedidos  ){
        final String[] salvar_pedido = {"cadastrar_pedido_vaptvupt.php"};


        // String doc_pedido="cadastrar_pedido_vaptvupt.php";
        final String[] forma_pagamento = {pedidos.getForma_Pagamento()};
int cod_usuario= pedidos.getId_usuarios();
final String status = pedidos.getStatus();
final Double valor_total = pedidos.getValor_total();


        Ion.with(context)

                .load(URL+ salvar_pedido[0])

                .setBodyParameter("forma_pagamento", forma_pagamento[0])
                .setBodyParameter("id_usuario",String.valueOf(cod_usuario))

                .setBodyParameter("status",status)
                .setBodyParameter("valor_total",String.valueOf(valor_total))
                .setBodyParameter("data_pedido",data_teste)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        id_pedido[0] = result.get("ID").getAsString();


                    }

                });





//return  salvar_ped;
       return id_pedido[0];
    }*/
}
