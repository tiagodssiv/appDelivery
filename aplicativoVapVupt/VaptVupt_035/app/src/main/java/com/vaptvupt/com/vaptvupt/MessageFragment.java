package com.vaptvupt.com.vaptvupt;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

//import android.support.design.widget.Snackbar;

/**
 * Created by Usuario on 15/06/2018.
 */

public class MessageFragment extends Fragment  {
    private ProgressBar circula;
    ImageView image;
android.app.Fragment fragment =  new Fragment_Produto_Prevenda();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public MessageFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_message, container, false);



         circula =(ProgressBar)view.findViewById(R.id.probar);
         image= (ImageView)view.findViewById(R.id.logo);
        circula.setVisibility(View.VISIBLE);

        new AsyncCircular().execute();

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this.getContext());


//000000000000
/*
        ListView lista = (ListView) view.findViewById(R.id.lista);
        List<Curso> cursos = todosOsCursos();
        AdapterCursosPersonalizado adapter = new AdapterCursosPersonalizado(cursos, (DialogInterface.OnClickListener) this.getContext());
        lista.setAdapter(adapter);
        Curso cirso = new Curso(null,null,null);*/



        //-------------




/*
        final Button btn = (Button)view.findViewById(R.id.tes);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); btn.setText("ok!");
            }
        });*/



return view;

    }








    public class AsyncCircular extends AsyncTask<Void, Integer, Void> {
        String color ="#ffd149";

        @Override
        protected void onPreExecute() {
          circula.setVisibility(View.VISIBLE);
          circula.getIndeterminateDrawable()
                    .setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            for (int i = 0; i < 100; i++) {
                try {
                    publishProgress(i);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
          //  linearBar.setProgress(values[0]);
           // linearBar.setSecondaryProgress(values[0] + 15);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            circula.setVisibility(View.GONE);
            image.setVisibility(View.GONE);
          recyclerView.setLayoutManager(layoutManager);

            adapter = new RecyclerAdapter();
            recyclerView.setAdapter(adapter);
           // linearBar.setVisibility(View.GONE);
        }
    }

}
