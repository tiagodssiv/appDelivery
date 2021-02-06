package com.vaptvupt.com.vaptvupt;

import android.app.Activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Pedidos {

    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

Activity activity;


                        /*    formatador.format( data );*/

    ArrayList<Pedidos>listPedidos = new ArrayList<>();
    private int id_pedidos;
    private int id_usuarios;
    private String status;

    public String getForma_Pagamento() {
        return forma_Pagamento;
    }

    public void setForma_Pagamento(String forma_Pagamento) {
        this.forma_Pagamento = forma_Pagamento;
    }

    private String forma_Pagamento;

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    private Double valor_total ;

    public int getId_pedidos() {
        return id_pedidos;
    }

    public void setId_pedidos(int id_pedidos) {
        this.id_pedidos = id_pedidos;
    }

    public int getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(int id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(Date data_pedido) {
        this.data_pedido = data_pedido;
    }

    private Date data_pedido;

    public void adicinarPedido(Pedidos pedidos){

listPedidos.add(pedidos);

    }
}
