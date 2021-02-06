package com.vaptvupt.com.vaptvupt;

import java.util.ArrayList;
import java.util.List;

public class Globals {


List<Carrinho>listTeste=new ArrayList<>();

Carrinho car = new Carrinho();
    private static Globals instance = new Globals();

    // Getter-Setters
    public static Globals getInstance() {
        return instance;
    }

    public static void setInstance(Globals instance) {
        Globals.instance = instance;
    }

    private int notification_index=0;


    public Globals() {

    }


    public /*int*/List<Carrinho> getValue() {
      //  return  notification_index;
        return listTeste;
    }


    public void setValue(List<Carrinho>list) {

        //int id_carrinho,int id_banco,int qnt,int image,String titulo,Double preco,Double preco_itens
       // this.notification_index = notification_index;
        this.listTeste=list;
    }




}
/*MEIO PARA ACESSAR ESTA CLASSE :
Globals sharedData = Globals.getInstance();
SETAR DADOS NESTA CLASSE
sharedData.setValue("kundan");
RECUPERAR DADOS
String n = sharedData.getValue();
*
*/