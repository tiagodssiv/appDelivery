package com.vaptvupt.com.vaptvupt;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;



import java.util.List;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class Fragment_Tela_principal extends Fragment {


    FlipperLayout flipperLayout;
    Carrinho car = new Carrinho();
    int item=0;
    DBhelper banco = new DBhelper(getContext());
    Globals sharedData = Globals.getInstance();
    RecyclerCarrinho rc = new RecyclerCarrinho((Activity) getContext());
    public void setar(Carrinho carrinho){
    item=carrinho.getId_banco();

}
    Fragment fragment =  new MessageFragment();
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_principal, container, false);



        flipperLayout = (FlipperLayout)view.findViewById(R.id.flipper);
        //add your pics in draable

        int  images []={R.drawable.bolo,R.drawable.paes,R.drawable.salgado,R.drawable.bebidas};
        String DescriptionImages []={"Refrigerantes","Coxinhas recheadas","Salgados finos "};
//SE QUER PEGAR IMAGEM DE URL
       /* String[]imageUrls = new String[]{
                "http://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg",
                "http://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg",
                "http://cdn.pixabay.com/photo/2017/12/24/09/09/road-3036620_960_720.jpg"

        };*/
        String[]imageUrls = new String[]{
                "http://sistemerc.freetzi.com/postagens/I8AvTAJqeruLPBO8KpvusFSDVrNZpqmLWZdiOJb4I3uTTCff05M7gao.jpg",
                "http://sistemerc.freetzi.com/postagens/ulCp44dHm84Pp2qJfVMEczByixD2tUpHeBpZGG9lOWBvfzT28XpO69m.jpg",
                "http://sistemerc.freetzi.com/postagens/m2Dn5nuiTfXbw0Vn1HErtTFmW1p6Qt28nmDTaimxhPAT6KcrKuoMW26.jpg",
                "http://sistemerc.freetzi.com/postagens/h6yr26Jg6cLiqXKcRGKH0ECpl6ZEX1vV9y9Fi0wQcLEaJlcIPGM4gEm.jpg",
                "http://sistemerc.freetzi.com/postagens/B08PHxwzeTrDWHKvdRTu1JAeTiMJqw0ABqDSS2pTJ7SbFHH9oEzSxSw.jpg",
                "http://sistemerc.freetzi.com/postagens/ineHdwtsh88hIg65xSwIb26uc7DDLiGXRZ1rrDiFMkHiCbGiXzd3grv.jpg",
                "http://sistemerc.freetzi.com/postagens/soFrRmVogzuP3D4mqeg84oKNTFMxnA6T39ae28GkT6L4rWSKa7thNZk.jpg"
        };

        //-----
        for (int i=0;i<images.length;i++){

            FlipperView flipperView =  new FlipperView(getContext());
            flipperView.setImageDrawable(images[i]);
            //  flipperView.setImageUrl(imageUrls[i]);

            //flipperView.setDescription(DescriptionImages[i]);
            final int finalI = i;
            flipperView.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                @Override
                public void onFlipperClick(FlipperView flipperView) {
                    Toast.makeText(getContext(), "Anúncio " + finalI, Toast.LENGTH_SHORT).show();

                }
            });
            flipperLayout.addFlipperView(flipperView);



        }

        //---------------------------


        ListView lista = (ListView)view.findViewById(R.id.lis_navegacao);
        AdapterListaTelaPrincipal adapter = new AdapterListaTelaPrincipal((Activity) getContext());
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0 ){



                        abrirFragment(fragment);



                }else if(position == 1){
List<Carrinho>ListT=sharedData.getValue();
sharedData.setValue(ListT);
ListT=sharedData.getValue();


    Toast.makeText(getContext()," LISTA TEST "+ListT.size(), Toast.LENGTH_SHORT).show();








                 //   Toast.makeText(getContext(), "Restaurante", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Sobre", Toast.LENGTH_SHORT).show();

                }
            }
        });
        return view ;
}


    public void abrirFragment (Fragment fragment){

        FragmentTransaction ft =  getActivity().getSupportFragmentManager().beginTransaction();
        // Fragment login = new LoginFragment();
        ft.setCustomAnimations(R.anim.pai_entrando, R.anim.botton_out);
        ft.replace(R.id.frame, fragment, "login");
        ft.commit();



    }


}
