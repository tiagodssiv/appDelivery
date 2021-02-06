package com.vaptvupt.com.vaptvupt;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdapterListaTelaPrincipal extends BaseAdapter {


    private List<ListaTalaPrincipal> dadosListaTelaPrincipal() {
        return new ArrayList<>(Arrays.asList(


                new ListaTalaPrincipal("Lanches" , R.drawable.ic_restau),
                new ListaTalaPrincipal("Restaurante" , R.drawable.ic_location),
                new ListaTalaPrincipal("Sobre" , R.drawable.ic_info)





        ));}
    Activity activity ;
    public ArrayList<ListaTalaPrincipal> listaTelaPrincipal = (ArrayList<ListaTalaPrincipal>) dadosListaTelaPrincipal();

    public AdapterListaTelaPrincipal(Activity act){

        this.activity = act;

    }

    @Override
    public int getCount() {
        return listaTelaPrincipal.size();
    }

    @Override
    public Object getItem(int position) {
        return listaTelaPrincipal.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(this.activity, R.layout.adapter_lista_tela_principal,null);//act.getLayoutInflater().inflate(R.layout.lista_curso_personalizada, parent, false);

        ImageView icone = (ImageView)view.findViewById(R.id.im);
        TextView titulo = (TextView)view.findViewById(R.id.ti);
        icone.setImageResource(this.listaTelaPrincipal.get(position).getIcone());
        titulo.setText(this.listaTelaPrincipal.get(position).getTitulo());



        return view;

    }
}
