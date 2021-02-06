package com.vaptvupt.com.vaptvupt;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Usuario on 11/08/2019.
 */

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Main2Activity pp = new Main2Activity();
    List<Produtos>listP= todosOsCursos();
    public Context context;











    private List<Produtos> todosOsCursos() {

        return new ArrayList<>(Arrays.asList(






                new Produtos( R.drawable.estrelas , R.drawable.joelho2,0,0,"Massa de salgado rechada com queijo mussarela e presunto .200g  ","Joelho e presunto",5.00),
                new Produtos(R.drawable.estrelas, R.drawable.empada_grande,0,0,"Empadão recheado com frango cremoso.200g ","Empadão",7.00),
                new Produtos(R.drawable.zero, R.drawable.empada_pequena,0,0,"Empada pequena recheada com frango cremoso.50g ","Empada pequena ",1.00),
                new Produtos( R.drawable.quatro, R.drawable.pizza_caabresa,0,0," Pizza coberta com rodelas de linguiça calabresa, cebola, azeitonas, orégano.Tamanho médio","Pizza calabresa",15.00),
                new Produtos( R.drawable.tres, R.drawable.pizza_portuguesa,0,0,"Pizza coberta com rodelas de ovo cozido, cebola, azeitonas,pimentão,linguiça calabresa e orégano.Tamanho médio","Pizza portuguesa",15.00),
                new Produtos(R.drawable.duas, R.drawable.pizza_doce,0,0,"Pizza coberta com chocolate e chocolate branco.Tamanho médio\", \"Salgado com recheio de salsicha e molho","Pizza doce",15.00),
                new Produtos(R.drawable.uma, R.drawable.salsicha1,0,0,"Massa de salgado com recheio de salsicha e molho de tomate","Enroladinho de salsicha",2.00),
                new Produtos(R.drawable.estrelas, R.drawable.croi,0,0,"Massa folhada recheada com branco","Croissant",5.00),
                new Produtos(  R.drawable.duas, R.drawable.bicmac,0,0,"Hamburguer com dois hamburguers ,alface queijo,molho especial ,picles,cebola e pão com gergilim","Hamburguer",8.25)


        ));}









    static class ViewHolder extends RecyclerView.ViewHolder{

        List<Produtos>dados=todosOsCursos() ;

        private List<Produtos> todosOsCursos() {
            return new ArrayList<>(Arrays.asList(






                    new Produtos( R.drawable.estrelas , R.drawable.joelho2,0,0,"Massa de salgado rechada com queijo mussarela e presunto .200g  ","Joelho e presunto",5.00),
                    new Produtos(R.drawable.estrelas, R.drawable.empada_grande,0,0,"Empadão recheado com frango cremoso.200g ","Empadão",7.00),
                    new Produtos(R.drawable.zero, R.drawable.empada_pequena,0,0,"Empada pequena recheada com frango cremoso.50g ","Empada pequena ",1.00),
                    new Produtos( R.drawable.quatro, R.drawable.pizza_caabresa,0,0," Pizza coberta com rodelas de linguiça calabresa, cebola, azeitonas, orégano.Tamanho médio","Pizza calabresa",15.00),
                    new Produtos( R.drawable.tres, R.drawable.pizza_portuguesa,0,0,"Pizza coberta com rodelas de ovo cozido, cebola, azeitonas,pimentão,linguiça calabresa e orégano.Tamanho médio","Pizza portuguesa",15.00),
                    new Produtos(R.drawable.duas, R.drawable.pizza_doce,0,0,"Pizza coberta com chocolate e chocolate branco.Tamanho médio\", \"Salgado com recheio de salsicha e molho","Pizza doce",15.00),
                    new Produtos(R.drawable.uma, R.drawable.salsicha1,0,0,"Massa de salgado com recheio de salsicha e molho de tomate","Enroladinho de salsicha",2.00),
                    new Produtos(R.drawable.estrelas, R.drawable.croi,0,0,"Massa folhada recheada com branco","Croissant",5.00),
                    new Produtos(  R.drawable.duas, R.drawable.bicmac,0,0,"Hamburguer com dois hamburguers ,alface queijo,molho especial ,picles,cebola e pão com gergilim","Hamburguer",8.25)


            ));}


        public int currentItem;
        public ImageView itemImage,itemOpiniao;
        public TextView itemTitle;
        public TextView itemDetail;
        public TextView itemPreco;
        public AppCompatButton btnPedir;

public void abrirFragment()
{
  /*  Fragment_Produto_Prevenda fragment = new Fragment_Produto_Prevenda();

    AppCompatActivity  activity =   ( AppCompatActivity )  itemView . getContext ();


    FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
    // Fragment login = new LoginFragment();
    ft.setCustomAnimations(R.anim.pai_entrando, R.anim.botton_out);
    ft.replace(R.id.frame, fragment, "login");
    ft.commit();*/

}

/*

        private void Fragment(final Fragment fragment) {
           // AppCompatActivity  activity =   ( AppCompatActivity )  itemView . getContext ();
            FragmentTransaction transaction = ((AppCompatActivity) itemView.getContext()).getSupportFragmentManager()
                    .beginTransaction();
            try {
                transaction.add(R.id.frame, fragment)
                        .commit();
            } catch (Exception e) {
                Toast.makeText(itemView.getContext(), "ViewHolder " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }*/
 public ViewHolder(final View itemView ) {





            super(itemView);
         itemImage = (ImageView)itemView.findViewById(R.id.item_image);
            itemTitle = (TextView)itemView.findViewById(R.id.item_title);
            itemDetail =
                    (TextView)itemView.findViewById(R.id.item_detail);
            itemPreco =
                    (TextView)itemView.findViewById(R.id.item_preco);
            itemOpiniao = (ImageView)itemView.findViewById(R.id.item_opiniao);
            btnPedir = (AppCompatButton)itemView.findViewById(R.id.btn);

            btnPedir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            btnPedir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    int position = getAdapterPosition();


                    Intent intent = new Intent(itemView.getContext(),Main2Activity.class);

                   intent.putExtra("valor",position);
                    intent.putExtra("valo", (Serializable) dados);


                  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) itemView.getContext(),null);
                    itemView.getContext().startActivity(intent , options.toBundle());


                     itemView.getContext().startActivity(intent);




                  } else{
                        itemView.getContext().startActivity(intent);
                  }




              /*  Intent intent = new Intent(itemView.getContext(),Main2Activity.class);
                    itemView.getContext().startActivity(intent );*/



                    /*

                    Intent intent = new Intent(itemView.getContext(),Main2Activity.class);
                   intent.putExtra("posição",position);
                  itemView.getContext().startActivity(intent);*/

                }
            });

/*
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });*/


        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {


        viewHolder.itemTitle.setText(listP.get(i).getNome());
        viewHolder.itemDetail.setText(listP.get(i).getDescricao());
        viewHolder.itemImage.setImageResource(listP.get(i).getImage());
        viewHolder.itemOpiniao.setImageResource(listP.get(i).getImagem_opiniao());
        viewHolder.itemPreco.setText(String.valueOf(listP.get(i).getPreco()));


    }

    @Override
    public int getItemCount() {
        return listP.size();
    }
}



