package com.vaptvupt.com.vaptvupt;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 02/09/2019.
 */

public class RecyclerCarrinho extends RecyclerView.Adapter<RecyclerCarrinho.ViewHolder>{
      List<Integer>apager= new ArrayList<>();
    List<Carrinho> carrinho = new ArrayList<>();/*todosOsCursos();*///new ArrayList<>();
    Carrinho ca = new Carrinho();
    RecyclerViewOnClickListnerHack mRecyclerViewOnClickListnerHack;
    public RecyclerCarrinho(Activity activity ){

        this.act=activity;

    }

Activity act;
Double vt = 0.0;


    public void setRecyclerViewOnClickListnerHack(RecyclerViewOnClickListnerHack r){


    mRecyclerViewOnClickListnerHack = r;
}

   public void insertItem(Carrinho user ) {
        carrinho.add(user);
        notifyItemInserted(getItemCount());

       // notifyItemInserted(posi);
    }
    public void removerItemClick(int position){

        carrinho.remove(position);
        notifyItemRemoved(position);

    }
    public void limparLista(){
        carrinho.clear();
        notifyDataSetChanged();

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.teste, viewGroup, false);
        RecyclerCarrinho.ViewHolder viewHolder = new ViewHolder(v);





        return viewHolder; }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.nome.setText(carrinho.get(i).getTitulo());
       viewHolder.foto.setImageResource(carrinho.get(i).getImagem());
        viewHolder.qnt.setText(String.valueOf(carrinho.get(i).getQnt()));
        viewHolder.valor_itens.setText(String.valueOf(carrinho.get(i).getPreco_item()));

        Double ti =0.0;

        

    }


    @Override
    public int getItemCount() {
        return carrinho.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public int currentItem;
        int posi = getAdapterPosition();
        //  public ImageView itemImage,itemOpiniao;
        public TextView nome;
        public ImageView foto;
        public TextView qnt;
        public TextView valor_itens;
        public ImageView exc;

        public ViewHolder(final View itemView ) {

            super(itemView);
           foto= (ImageView)itemView.findViewById(R.id.f);
          qnt= (TextView)itemView.findViewById(R.id.qnt);
            nome = (TextView)itemView.findViewById(R.id.lista_curso_personalizada_nome);
            valor_itens =(TextView)itemView.findViewById(R.id.valor_itens);
            itemView.setOnClickListener(this);

           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });*/
        }

        @Override
        public void onClick(View v) {

            if(mRecyclerViewOnClickListnerHack != null){
                mRecyclerViewOnClickListnerHack.onClikListener(v,getPosition());


            }

        }
    }



}
