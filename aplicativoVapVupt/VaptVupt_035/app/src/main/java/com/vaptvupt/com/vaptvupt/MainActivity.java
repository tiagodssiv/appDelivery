package com.vaptvupt.com.vaptvupt;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.arturogutierrez.Badges;
import com.github.arturogutierrez.BadgesNotSupportedException;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.vaptvupt.com.vaptvupt.DB_helper.ConexaoSqlite;
import com.vaptvupt.com.vaptvupt.badge_count.BadgeDrawable;
import com.vaptvupt.com.vaptvupt.badge_count.Converter;
import com.vaptvupt.com.vaptvupt.controller.ProdutoController;
import com.vaptvupt.com.vaptvupt.dao_serve.Dao_server;
import com.vaptvupt.com.vaptvupt.interfaces.AddorRemoveCallbacks;
import com.vaptvupt.com.vaptvupt.model.Produto;


import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;



import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , AddorRemoveCallbacks {



    String id_pedido="";
    int qnt_carrinho=0;
    int qnt_carinho_1=0;

//método badge


/*
    public  void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }*/
    //fim metodo badge





    final Pattern EMAIL_ADDRES = Pattern.compile(
            "[a-zA-z0-9\\+\\.\\_\\%\\-\\+]{1,256}"
                    +"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"
                    +"("+"\\."+"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+")+"
    );

    DBhelper banco = new DBhelper(MainActivity.this);
Fragment_Tela_principal ftp = new Fragment_Tela_principal();
    List<Carrinho> listaC;
    ImageView foto;
    private ProgressBar circula;
    ImageView image;
    Globals sharedData = Globals.getInstance();
/*
    ConexaoSqlite conexaoSqlite = ConexaoSqlite.getInstanciaConexao(this);
    ProdutoController controller = new ProdutoController(conexaoSqlite);*/
    AdapterCarrinho adapter;
    Dialog dialog;
    String forma_Pagamento = "";
    FrameLayout frame;
    Double total = 0.0;
    //AlertDialog.Builder builder;
    Carrinho car = new Carrinho();



    private void modalCarrinho() {
if(sharedData.getValue().size()>0){
        //CARRINHO DE COMPRAS


        //CARRINHO DE COMPRAS
//    Toast.makeText(this, " tamanho "+banco.selectItensCarrinho().size(), Toast.LENGTH_SHORT).show();

    dialog = new Dialog(this);
    dialog.setContentView(R.layout.layout_listview);

    dialog.setCancelable(false);
    dialog.setTitle("Carrinho");


    final android.support.v7.widget.AppCompatButton btn_finalizar = (android.support.v7.widget.AppCompatButton) dialog.findViewById(R.id.btn);

    final ListView listView = (ListView) dialog.findViewById(R.id.lista);
    final TextView val = (TextView) dialog.findViewById(R.id.valor);
    final ImageView sair = (ImageView)dialog.findViewById(R.id.img_sair);


    //-------------------------
    /*
     * BOTÃO FINALIZAR
     * */



    btn_finalizar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final AlertDialog.Builder di = new AlertDialog.Builder(MainActivity.this);


            final View view = getLayoutInflater().inflate(R.layout.layout_carrinho_resumo, null);
            di.setView(view);

/*
            dialogg.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });*/


            final TextInputLayout rua = (TextInputLayout)view.findViewById(R.id.ru);
            final AppCompatEditText Rua = (AppCompatEditText)view.findViewById(R.id.rua);


            final TextView prd = (TextView)view.findViewById(R.id.itens);
            final TextView valor_prod = (TextView)view.findViewById(R.id.valor_tot1);
            final TextView valor_tot2 = (TextView)view.findViewById(R.id.valt);
            final TextView deliv = (TextView)view.findViewById(R.id.deli);
            //itens informativo o layout
            valor_prod.setText("R$ "+total);
            final AlertDialog ad= di.show();
            car.setPreco_total(total);
            if( total < 15.0 ){
                deliv.setText("R$ "+10.0);
                car.setPreco_total(total+10.0);

            }
            prd.setText("Produtos ("+adapter.carrinho.size()+")");
            valor_tot2.setText("Você pagará R$ "+car.getPreco_total());

            //fim itens informativo o layout

            final TextInputLayout numero = (TextInputLayout)view.findViewById(R.id.nu);
            final AppCompatEditText Numero = (AppCompatEditText)view.findViewById(R.id.numero);

            final TextInputLayout Nomee = (TextInputLayout)view.findViewById(R.id.no);
            final AppCompatEditText nomee = (AppCompatEditText)view.findViewById(R.id.nome);

            final TextInputLayout Telefone = (TextInputLayout)view.findViewById(R.id.te);
            final AppCompatEditText telefone = (AppCompatEditText)view.findViewById(R.id.telefone);

            //máscara no campo telefone
            SimpleMaskFormatter smf = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
            MaskTextWatcher mtw = new MaskTextWatcher(telefone,smf);
            telefone.addTextChangedListener(mtw);
            //fim da máscara campo telefone

            final TextInputLayout Email = (TextInputLayout)view.findViewById(R.id.em);
            final AppCompatEditText email = (AppCompatEditText)view.findViewById(R.id.email);

            final TextInputLayout Cidade = (TextInputLayout)view.findViewById(R.id.ci);
            final AppCompatEditText cidade = (AppCompatEditText)view.findViewById(R.id.cidade);

            final TextInputLayout Bairro = (TextInputLayout)view.findViewById(R.id.ba);
            final AppCompatEditText bairro = (AppCompatEditText)view.findViewById(R.id.bairro);

            final RadioGroup radiogroup=(RadioGroup)view.findViewById(R.id.group);
            final RadioButton radio_1 =(RadioButton)view.findViewById(R.id.radio1);
            final RadioButton radio_2 =(RadioButton)view.findViewById(R.id.radio2);
            final RadioButton radio_3 =(RadioButton)view.findViewById(R.id.radio3);
            final AppCompatButton btn_pedido =(AppCompatButton)view.findViewById(R.id.btn_pedido);

//caso não  haja escolha
            if(radio_1.isChecked()){forma_Pagamento="Dinheiro";}
//caso não  haja escolha




            //VALIDAÇÃO RADIOBUTTOM








            radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup,  int i) {


                    String radiovalue =((RadioButton)view.findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                    forma_Pagamento =radiovalue;

                }});

            //FIM RADIOBUTTOM









            btn_pedido.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {

                    //VALIDAÇÃO DO FORMULÁRIO

                    if(nomee.getText().toString().isEmpty()){
                        Nomee.setErrorEnabled(true);
                        Nomee.setError("Preencha este campo");


                    }else

                    if(Rua.getText().toString().isEmpty()){
                        rua.setErrorEnabled(true);
                        rua.setError("Preencha este campo");
                        Nomee.setErrorEnabled(false);


                    } else if(Numero.getText().toString().isEmpty()){
                        numero.setErrorEnabled(true);
                        numero.setError("Preencha este campo");

                        rua.setErrorEnabled(false);

                    }

                    else if(bairro.getText().toString().isEmpty()){
                        Bairro.setErrorEnabled(true);
                        Bairro.setError("Preencha este campo");
                        numero.setErrorEnabled(false);


                    }
                    else if(cidade.getText().toString().isEmpty()){
                        Cidade.setErrorEnabled(true);
                        Cidade.setError("Preencha este campo");
                        Bairro.setErrorEnabled(false);


                    }
                    else if(telefone.getText().toString().isEmpty()){
                        Telefone.setErrorEnabled(true);
                        Telefone.setError("Preencha este campo");

                        Cidade.setErrorEnabled(false);

                    }   else if(email.getText().toString().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()){
                        Email.setErrorEnabled(true);
                        Email.setError("Campo vazio ou e-mail inválido");
                        Telefone.setErrorEnabled(false);


                    }
                    else{
                       /* circula = (ProgressBar)findViewById(R.id.probar);
                        image = (ImageView)findViewById(R.id.logo);*/




//CRIA PEDIDO

                        final Pedidos ped = new Pedidos();
                        Date data = new Date();
                        ped.setValor_total(total);
                        //  ped.setData_pedido(data);
                        ped.setValor_total(car.getPreco_total());
                        ped.setStatus("AGUARDANDO APROVAÇÃO");
                        //   ped.setId_pedidos(1);
                        ped.setId_usuarios(1);
                        ped.adicinarPedido(ped);
                        ped.setForma_Pagamento(forma_Pagamento);
                        forma_Pagamento="";
                        rua.setErrorEnabled(false);
                        numero.setErrorEnabled(false);
                        val.setText("00,00");
                        //car.setPreco_total(0.0);
                        btn_finalizar.setVisibility(GONE);
                        dialog.dismiss();
                        ad.dismiss();
                        String da="";


                        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            da= out.format(new Date());
                        }


                        final ProgressDialog d = new ProgressDialog(MainActivity.this);
                        d.setCancelable(false);

                        d.setMessage("Por favor Aguarde...");

                        d.create();
                        d.show();



                        //FIM CRIA PEDIDO
//CADASTRA PEDIDO
                        Ion.with(MainActivity.this)

                                .load("http://sistemerc.freetzi.com/vaptvupt_app/cadastrar_pedido_vaptvupt.php")

                                .setBodyParameter("forma_pagamento", ped.getForma_Pagamento())
                                .setBodyParameter("id_usuario",String.valueOf(ped.getId_usuarios()))

                                .setBodyParameter("status",ped.getStatus())
                                .setBodyParameter("valor_total",String.valueOf(ped.getValor_total()))
                                .setBodyParameter("data_pedido",da)
                                .asJsonObject()
                                .setCallback(new FutureCallback<JsonObject>() {
                                    @Override
                                    public void onCompleted(Exception e, JsonObject result) {
                                        //cadastra a lista no banco






                                        //fim cadastra lista no banco

                                    }

                                });




                        // salvar itens_carrinho
                        for(int i =0;i < adapter.carrinho.size();i++) {
                            String URL = "http://sistemerc.freetzi.com/vaptvupt_app/cadastrar_itens_pedido_vaptvupt.php";


                            Ion.with(MainActivity.this)

                                    .load(URL)

                                    .setBodyParameter("nome_item", adapter.carrinho.get(i).getTitulo())
                                    .setBodyParameter("qnt", String.valueOf(adapter.carrinho.get(i).getQnt()))

                                    .setBodyParameter("preco_item", String.valueOf(adapter.carrinho.get(i).getPreco()))
                                    .setBodyParameter("preco_itens", String.valueOf(adapter.carrinho.get(i).getPreco_item()))
                                    .setBodyParameter("cod_pedido",id_pedido)
                                    .asJsonObject()
                                    .setCallback(new FutureCallback<JsonObject>() {
                                        @Override
                                        public void onCompleted(Exception e, JsonObject result) {
String callback ="";
callback =result.get("CREATE").getAsString();
if(callback .equals("OK")){

    d.dismiss();
    AlertDialog.Builder di = new AlertDialog.Builder(MainActivity.this);
    di.setTitle(" Pedido Registrado com sucesso !")

            .setCancelable(false)
            .setMessage("Seu pedido foi registrado com sucesso . Agora você pode consultar o andamento de seu pedido na aba meus pedidos")
            .setPositiveButton("OK",null)
            .show();
}

                                            // print the response code, ie, 200

                                 /*   System.out.println(result.getHeaders().code());
                                    // print the String that was downloaded
                                    System.out.println(result.getResult());*/

                                        }
                                    });
                        }




                        //fim itens_carrinho


//CADASTRA PEDIDO
                        //limpa as listas
                        adapter.carrinho.clear();
                        listaC.clear();
                        sharedData.setValue(adapter.carrinho);


                        //fim limpa as listas
                     /*   if(salvar_pedido.salvarPedido(ped)){
                            Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();


                        }else{

                            Toast.makeText(MainActivity.this, "ERRO", Toast.LENGTH_SHORT).show();
                        }


*/

                    }



                    //FIM DA VALIDAÇÃO DO FORMULÁRIO

                }
            });



        }


    });

//----------------------------


        //AO CLICAR NO ICONE DE SAIR
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //FIM AO CLICAR NO ITEM DE SAIR


        val.setText(String.valueOf(total));


        // val.setText(String.valueOf(banco.valorTotal(banco.selectItensCarrinho())));

        if (listaC.size() > 0) {
            btn_finalizar.setVisibility(View.VISIBLE);
        }

        //ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.list_item, R.id.tv , myImageNameList);
        listView.setAdapter(adapter);


        // AO CLICAR NA LISTA
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                // APAGAR AOM CLICAR TABELA

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Apagar este item ?");
                builder.setNegativeButton("CANCELAR", null);
                builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        if (sharedData.getValue().size()>0){

                            //car.setId_carrinho(position);
                           // banco.mostarLivro(car);
                          //  banco.aualizarCarrinho(position);
                          //  Toast.makeText(MainActivity.this, "", Toast.LENGTH_LONG).show();


                             // banco.apagarCarrinho(car);


                            if (listaC.size() > 0) {
                                total = Double.parseDouble(val.getText().toString())- listaC.get(position).getPreco_item();
                                 car.setPreco_total(total);
                                 val.setText(String.valueOf(total));

                             }

                            }


                        adapter.carrinho.remove(position);
                        adapter.notifyDataSetChanged();
                        sharedData.setValue(adapter.carrinho);

                        //INFORMA O VALOR DO CARRINHO NO ICONE DE CARRINHO
                         
                        onAddProduct();
                        //FIM INFORMA O VALOR DO CARRINHO NO ICONE DE CARRINHO

                        listaC= adapter.carrinho;
                        if(listaC.size()==0){
                            total = 0.0;
                            car.setPreco_total(total);
                            val.setText(String.valueOf(car.getPreco_total()));
                            btn_finalizar.setVisibility(GONE);

                        }

                        }








                });
builder.create().show();
//FIM APAGAR AOM CLICAR TABELA


            }
        });
        //FIM AO CLICAR NA LISTA
        val.setText(String.valueOf(total));
        dialog.show();

//FIM CARRINHO DE COMPRAS

//FIM CARRINHO DE COMPRAS


    }
else{


    Toast.makeText(this, "Carrinho vazio !", Toast.LENGTH_LONG).show();
}

}

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            // trabsições normais que já tem disponíveis no android explode e fade
             Explode trans1 = new Explode();
            Fade trans2 = new Fade();
            trans1.setDuration(3000);
            trans2.setDuration(3000);

            getWindow().setExitTransition(trans1);
            getWindow().setReenterTransition(trans2);

            //trabalhar com elementos compartilhados
          //  getWindow().setSharedElementEnterTransition(new ChangeBounds());

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BADGES

        //VALOR ZERO
        /*
        try {
            Badges.removeBadge(MainActivity.this);
            // Alternative way
            Badges.setBadge(MainActivity.this, 0);
        } catch (BadgesNotSupportedException badgesNotSupportedException) {

        }*/
        //-------
        //COM VALOR
        try {
            Badges.setBadge(MainActivity.this, 5);
        } catch (BadgesNotSupportedException badgesNotSupportedException) {

        }
        //---
        //FIM BADGES


      //tratamento do banco  com MVC / inserir
     /*   ConexaoSqlite conexaoSqlite = ConexaoSqlite.getInstanciaConexao(this);


//inserir itens no banco mvc
        ProdutoController produtoController = new ProdutoController(conexaoSqlite);
       long resultado = produtoController.salvarProdutoCtrl(car);
        Toast.makeText(this, "RESULTADO"+resultado, Toast.LENGTH_SHORT).show();
      // LISTAR ITENS
        ProdutoController produtoController1 = new ProdutoController(conexaoSqlite);
        // retorna uma lista
        List<Produto>listaRecebeListaDoClotrole = produtoController.getListaProdutoCTRL();

        //ecluir produto do banco

        if(produtoController.excluirProdutoCtrl(1)){

            //excluido com sucesso
        }*/
        //-------------------;



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Pattern EMAIL_ADDRES = Pattern.compile(
                "[a-zA-z0-9\\+\\.\\_\\%\\-\\+]{1,256}"
                        +"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"
                        +"("+"\\."+"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+")+"
        );


//sharedData.setValue(adapter.carrinho);

if(sharedData.getValue().size()>0){
    listaC=sharedData.getValue()/*banco.selectItensCarrinho()*/;
    adapter= new AdapterCarrinho(listaC,MainActivity.this);


    for(int i = 0;i <listaC.size();i++){

        total= total+listaC.get(i).getPreco_item();


    }








}






































     /*   BottomNavigationView   navigationVieW = (BottomNavigationView) findViewById(R.id.bottomNav);
navigationVieW.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_fav: {
                Toast.makeText(MainActivity.this, "cardápio", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_loc: {
                Toast.makeText(MainActivity.this, "Restaurante", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.menu_track: {
                Toast.makeText(MainActivity.this, "Sobre", Toast.LENGTH_SHORT).show();
                break;
            }

            //  return true;
        }

    }
});
*/


        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);


/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Toast.makeText(MainActivity.this, "tttt", Toast.LENGTH_SHORT).show();

            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_camera);
        Fragment fragment = new Fragment_Tela_principal();
        displaySelectedFragment(fragment);
        //Select Home by default
      //--------------------------------------


        //000000




    }

    private void displaySelectedFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        qnt_carrinho=  sharedData.getValue().size();


        getMenuInflater().inflate(R.menu.main, menu);




       // getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.sacola);
        menuItem.setIcon(Converter.convertLayoutToImage(MainActivity.this,qnt_carrinho,R.drawable.ic_car));
       //MenuItem menuItem2 = menu.findItem(R.id.sino);



/*
        MenuItem itemCart = menu.findItem(R.id.sacola);
        LayerDrawable icon = (LayerDrawable) itemCart.getIcon();
        setBadgeCount(this, icon, "9");*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sino) {
            Toast.makeText(this, "Notificação", Toast.LENGTH_SHORT).show();


        }

        if (id == R.id.sacola) {
          /*  try {
                Badges.removeBadge(MainActivity.this);
                // Alternative way
                Badges.setBadge(MainActivity.this, 0);
            } catch (BadgesNotSupportedException badgesNotSupportedException) {

            }*/
            //-------
            //COM VALOR
            try {
                Badges.setBadge(MainActivity.this, 5);
            } catch (BadgesNotSupportedException badgesNotSupportedException) {

            }
            //---
            //FIM BADGES

            modalCarrinho();



        }

        if (id == R.id.selecionar) {
            Toast.makeText(this, "Selecionar", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.opcoes) {
            Toast.makeText(this, "Opções de lanches", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
           Fragment fragment = new Fragment_Tela_principal();
            displaySelectedFragment(fragment);
        }

        else if (id == R.id.nav_slideshow) {


            Fragment fragment = new MessageFragment();
            displaySelectedFragment(fragment);

        } else if (id == R.id.nav_manage) {

        }/* else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onAddProduct() {

        qnt_carrinho=sharedData.getValue().size();

        invalidateOptionsMenu();


    }

    @Override
    public void onRemoveProduct() {

    }




    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }


    //INÍCIO CLASS CARREGAMENTO

    //FIM INÍCIO CLASS CARREGAMENTO




}