package com.vaptvupt.com.vaptvupt;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Usuario on 05/08/2019.
 */

public class AdapterCarrinho extends BaseAdapter {

public List<Carrinho> carrinho;

private Activity act;
Carrinho car = new Carrinho();
Double tvI =0.0;

    DBhelper banco = new DBhelper(act);





    public AdapterCarrinho(List<Carrinho> carrinho, Activity act) {
        this.carrinho = carrinho;
        this.act =  act;
    }
    @Override
    public int getCount() {
        return carrinho.size();
    }

    @Override
    public Object getItem(int position) {
        return carrinho.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removerItemDoCarrinho(  int posicao ){



      this.carrinho.remove(posicao);



        notifyDataSetChanged();


    }


    public void atualizar(List<Carrinho>carrinho){
        this.carrinho.clear();
        this.carrinho=carrinho;
        this.notifyDataSetChanged();

    }
    public void addItemDoCarrinho(Carrinho carrinho){
        this.carrinho.add(carrinho);
        this.notifyDataSetChanged();


    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {



        View view = View.inflate(this.act, R.layout.lista_curso_personalizada,null);//act.getLayoutInflater().inflate(R.layout.lista_curso_personalizada, parent, false);

       //Carrinho carri = carrinho.get(position);

        TextView nome = (TextView)
                view.findViewById(R.id.lista_curso_personalizada_nome);

       TextView qnt = (TextView)
                view.findViewById(R.id.qnt);

        TextView valor_itens = (TextView)
                view.findViewById(R.id.valor_itens);
        ImageView imagem =(ImageView)view.findViewById(R.id.f);

       imagem.setImageResource(carrinho.get(position).getImagem());
       nome.setText( this.carrinho.get(position).getTitulo());

        qnt.setText(String.valueOf(this.carrinho.get(position).getQnt()));
       valor_itens.setText(String.valueOf(this.carrinho.get(position).getPreco_item()));






        return view;
    }







}
