package com.vaptvupt.com.vaptvupt;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Usuario on 15/08/2019.
 */

public class ProdutosRecomendados extends BaseAdapter {
    List <Produtos>produtos;
    Activity activity;



    public ProdutosRecomendados(List<Produtos> produtos, Activity act) {
        this.produtos = produtos;
        this.activity =  act;
    }


    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(activity, R.layout.produtos,null);


        ImageView img = (ImageView)view.findViewById(R.id.foto);
        TextView nome = (TextView)view.findViewById(R.id.nome);
        TextView preco = (TextView)view.findViewById(R.id.pre);

        img.setImageResource(this.produtos.get(position).getImage());
        nome.setText(this.produtos.get(position).getNome().toString());
        preco.setText(String.valueOf(this.produtos.get(position).getPreco()));


        return view;
    }
}
