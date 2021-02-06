package com.vaptvupt.com.vaptvupt;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaCodec;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.vaptvupt.com.vaptvupt.DB_helper.ConexaoSqlite;
import com.vaptvupt.com.vaptvupt.controller.ProdutoController;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static android.view.View.GONE;

public class Main2Activity extends AppCompatActivity implements RecyclerViewOnClickListnerHack{
    DBhelper banco  = new DBhelper(Main2Activity.this);
    ProdutoController produtoController = new ProdutoController(ConexaoSqlite.getInstanciaConexao(this));
    Globals sharedData = Globals.getInstance();


    Produtos pp = new Produtos(0,0,0,0,"","",0.0);
    RecyclerCarrinho adapt = new RecyclerCarrinho(Main2Activity.this);
    ImageView foto;
    private ProgressBar circula;
    ImageView image;
    Double pt = 0.0;
    Carrinho car = new Carrinho();
    String forma_Pagamento ="" ;
    List<Produtos> listProduts= todosOsCursos();
    List<Carrinho>listCarrinho= new ArrayList();//ProdutosCarrinho();
    List<Double> listCarrinho2 =new ArrayList<Double>();
    List<String>listcarrinho3= new ArrayList<String>();
    ImageView img_teste;
    android.support.v7.widget.AppCompatButton   btn_finalizar;
    ProdutosRecomendados adapter = new ProdutosRecomendados(listProduts , Main2Activity.this);

    TextView  text;

    String nome,descricao;
    int imagem ;Double preco;





    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            android.transition.Explode trans1 = new android.transition.Explode();
            Fade trans2 = new Fade();
            trans1.setDuration(3000);
            trans2.setDuration(3000);

            getWindow().setEnterTransition(trans1);
            getWindow().setReturnTransition(trans2);
            // jeito que compartilha elementos .LEMBRANDO QUE PODE CRIAR DIFERENTES ANIMAÇÕES NO XML
            //  getWindow().setSharedElementEnterTransition(new ChangeBounds());
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);




        final Pattern EMAIL_ADDRES = Pattern.compile(
                "[a-zA-z0-9\\+\\.\\_\\%\\-\\+]{1,256}"
                        +"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"
                        +"("+"\\."+"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+")+"
        );


        final TextView tex =(TextView)findViewById(R.id.desc);
        final TextView pre =(TextView)findViewById(R.id.pre);
        final ImageView foto = (ImageView)findViewById(R.id.foto);
        View vip = getLayoutInflater().inflate(R.layout.produtos,null);
        View v = getLayoutInflater().inflate(R.layout.lista_curso_personalizada,null);
        final TextView vi = (TextView)v.findViewById(R.id.valor_itens);
        final TextView p = (TextView)vi.findViewById(R.id.pre);
        btn_finalizar= ( android.support.v7.widget.AppCompatButton)findViewById(R.id.  btn);
        final TextView vt = (TextView)findViewById(R.id.pretotal);


//CARREGA  O CARRINHO SE TIVER DADOS


adapt.carrinho=sharedData.getValue();
adapt.notifyDataSetChanged();
if(adapt.carrinho.size()>0) {

    btn_finalizar.setVisibility(View.VISIBLE);
    Double total = 0.0;

    for (int i = 0; i < adapt.carrinho.size(); i++) {


        total = total +/*banco.selectItensCarrinho()*/adapt.carrinho.get(i).getPreco_item();
        vt.setText(String.valueOf(total));

        //listCarrinho2.add(Double.parseDouble(vt.getText().toString()));
        car.setPreco_total(total);

    }

}


//--------------------------------------


//RECYCLERVIEW CARRINHO

        RecyclerView recyclerVie = (RecyclerView)findViewById(R.id.lista_recomendado);
        //  RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerVie.setAdapter(adapt);
        recyclerVie.setHasFixedSize(true);
        recyclerVie.setLayoutManager(layoutManager);
        adapt.setRecyclerViewOnClickListnerHack(this);



//FIM RECYCLERVIEW CARRINHO

        //PEGA OS DADOS DO ACTIVITY ANTERIOR e seta dentro dos campos do produto
        final int vu = getIntent().getIntExtra("valor",0);
        final ArrayList<Produtos> listDeProduts = (ArrayList<Produtos>) getIntent().getSerializableExtra("valo");
        tex.setText(listDeProduts.get(vu).getDescricao());
        foto.setImageResource(listDeProduts.get(vu).getImage());
        pre.setText(String.valueOf(listDeProduts.get(vu).getPreco()));
//-------------------------
        /*
         * BOTÃO FINALIZAR
         * */



        btn_finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialog =new AlertDialog.Builder(Main2Activity.this);


                final View view = getLayoutInflater().inflate(R.layout.layout_carrinho_resumo, null);
                final TextInputLayout rua = (TextInputLayout)view.findViewById(R.id.ru);
                final AppCompatEditText Rua = (AppCompatEditText)view.findViewById(R.id.rua);


                final TextView prd = (TextView)view.findViewById(R.id.itens);
                final TextView valor_prod = (TextView)view.findViewById(R.id.valor_tot1);
                final TextView valor_tot2 = (TextView)view.findViewById(R.id.valt);
                final TextView deliv = (TextView)view.findViewById(R.id.deli);
                //itens informativo o layout
                valor_prod.setText("R$ "+car.getPreco_total());

                if( car.getPreco_total() < 15.0 ){
                    deliv.setText("R$ "+10.0);
                    car.setPreco_total(car.getPreco_total()+10.0);

                }
                prd.setText("Produtos ("+adapt.carrinho.size()+")");
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






                dialog.setView(view);
                dialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                final AlertDialog ad = dialog.show();


                btn_pedido.setOnClickListener(new View.OnClickListener() {
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
                            circula = (ProgressBar)findViewById(R.id.probar);
                            image = (ImageView)findViewById(R.id.logo);

                            pt=car.getPreco_total();
                            rua.setErrorEnabled(false);
                            numero.setErrorEnabled(false);
                            vt.setText("00,00");
                            car.setPreco_total(0.0);
                            btn_finalizar.setVisibility(View.GONE);
                            new AsyncCircular().execute();
                            ad.dismiss();











                        }



                        //FIM DA VALIDAÇÃO DO FORMULÁRIO

                    }
                });

            }
        });

//----------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(listDeProduts.get(vu).getNome());//SETA TITULO



        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        //AO ABERTAR O BOTÃO FLOAT
        FloatingActionButton ff = (FloatingActionButton) findViewById(R.id.ff);
        ff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {









                AlertDialog.Builder dialog = new AlertDialog.Builder(Main2Activity.this);
                //  dialog.setTitle("Atenção ! ");
                dialog.setMessage(" Deseja adicionar este item ao carrinho ?");
                dialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//LAYOUT EXTERNO CAMPO FLOAT

                        final AlertDialog.Builder dialogg = new AlertDialog.Builder(Main2Activity.this);
                        View view = getLayoutInflater().inflate(R.layout.campo_qnt_carrinho, null);
                        dialogg.setView(view);
                        dialogg.setMessage("Por favor , informe a quantidade");


                        final AppCompatEditText qnt =(AppCompatEditText)view.findViewById(R.id.senha);
                        dialogg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (qnt.getText().toString().equals("")) {
                                    Toast.makeText(Main2Activity.this, "Informe ao menos (1) quantidade", Toast.LENGTH_SHORT).show();
                                }
                                else{



                                    int qn, po = 0, i = 0;
                                    int ii = i;
                                    Double vtotal = 0.0;
                                    Double vtst = 0.0;


                                    imagem = listDeProduts.get(vu).getImage();
                                    nome = listDeProduts.get(vu).getNome();
                                    preco = listDeProduts.get(vu).getPreco();


                                    car.setImagem(imagem);
                                    car.setTitulo(nome);
                                    car.setPreco(preco);
                                    qn = Integer.parseInt(qnt.getText().toString());
                                    car.setPreco_item(qn * preco);
                                    car.setQnt(qn);
                                    vtotal = car.getPreco_item();
                                    //  car.setPreco_total(vtotal);
                                    vt.setText(String.valueOf(vtotal));


                                        adapt.insertItem(car);

                                      //  car.setId_carrinho(0);
                                      //  car.setStatus("1");
                                      //  banco.insertCarrinho(car);
                                        sharedData.setValue(adapt.carrinho);



                                        listCarrinho2.add(Double.parseDouble(vt.getText().toString()));
                                        btn_finalizar.setVisibility(View.VISIBLE);

                                        for (i = 0; i < listCarrinho2.size(); i++) {
                                            vtst += listCarrinho2.get(i).doubleValue();
                                            vt.setText(String.valueOf(vtst));
                                            car.setPreco_total(vtst);
                                        }

                                       Toast.makeText(Main2Activity.this, "Item adicionado  "+car.getTitulo(), Toast.LENGTH_SHORT).show();






/*

    banco.insertCarrinho(car);

    adapt.insertItem(car);
    for(int cont =0; cont < adapt.carrinho.size();cont++){
        listCarrinho2.add(adapt.carrinho.get(cont).getPreco_item());
    }

    for (i = 0; i < listCarrinho2.size(); i++) {
        vtst += listCarrinho2.get(i).doubleValue();
        vt.setText(String.valueOf(vtst));
        car.setPreco_total(vtst);
    }

    Toast.makeText(Main2Activity.this, "Item adicionado  "+car.getTitulo(), Toast.LENGTH_SHORT).show();





    banco.insertCarrinho(car);

    adapt.insertItem(car);

    listCarrinho2.add(Double.parseDouble(vt.getText().toString()));


    btn_finalizar.setVisibility(View.VISIBLE);




    for (i = 0; i < listCarrinho2.size(); i++) {
        vtst += listCarrinho2.get(i).doubleValue();
        vt.setText(String.valueOf(vtst));
        car.setPreco_total(vtst);
    }
*/
                                  //  Toast.makeText(Main2Activity.this, "Item adicionado  "+car.getTitulo(), Toast.LENGTH_SHORT).show();



                                    // listCarrinho.add(car);







                                    // byte[]imagem =   convertImageViewToByteArray( viewHolder.foto);
                                    //  ca.setImg_byte(imagem);


                                    // banco.insertCarrinho(car);
                                    //adapt.alualizar();

                                    //fim imagem sqlite







                                    //teste imagem do sqlite
                                    // img_teste = (ImageView)findViewById(R.id.teste_image);
                                    //      ArrayList<Carrinho>listc = (ArrayList<Carrinho>) banco.selectItensCarrinho();

                                    // byte[]imagem = listc.get(0).getImg_byte();
                             /*       Bitmap bitmap = BitmapFactory.decodeByteArray(imagem , 0, imagem.length);
                                    img_teste.setImageBitmap(bitmap);*/
                                    //fim imagem sqlite





                                }

                            }
                        });
                        dialogg.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();

//FIM CAMPO LAYOUT EXTERNO NO CAMPO FLOAT




                        //  Toast.makeText(Main2Activity.this, "Item adicionado ao carrinho ! ", Toast.LENGTH_SHORT).show();

                    }
                }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                });

                dialog.create();
                dialog.show();









            }
        });





        //adapter lista recomendados

        ListView listaa = (ListView)findViewById(R.id.lista_recomendados);
//ListView lis = (ListView)findViewById(R.id.lista_ot);
//lis.setAdapter(adapter);
        listaa.setAdapter(adapter);
        listaa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final AlertDialog.Builder dialog = new AlertDialog.Builder(Main2Activity.this);
                view = getLayoutInflater().inflate(R.layout.campo_qnt_carrinho, null);
                dialog.setView(view);
                dialog.setMessage("Por favor , informe a quantidade ");
                final View finalView = view;
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        AppCompatEditText qnt =(AppCompatEditText) finalView.findViewById(R.id.senha);
                        if(qnt.getText().toString().equals("")){


                            Toast.makeText(Main2Activity.this, "Informe pelo menos (1) quantidade", Toast.LENGTH_SHORT).show();
                        }






                        else  {

                            int qn ,po = 0,i =0;
                            int ii = i;
                            Double vtotal = 0.0;
                            Double vtst=0.0;





                            imagem = listProduts.get(position).getImage();
                            nome = listProduts.get(position).getNome();
                            preco = listProduts.get(position).getPreco();



                            car.setImagem(imagem);
                            car.setTitulo(nome);
                            car.setPreco(preco);
                            qn =Integer.parseInt(qnt.getText().toString());
                            car.setPreco_item(qn * preco);
                            car.setQnt(qn);
                            vtotal =car.getPreco_item();
                            //  car.setPreco_total(vtotal);
                            vt.setText(String.valueOf(vtotal));

                            //  listCarrinho.add(car);
                            //  listCarrinho.add(car);


                            adapt.insertItem(car);
                            sharedData.setValue(adapt.carrinho);
                            Toast.makeText(Main2Activity.this, "Item adicionado  "+car.getTitulo(), Toast.LENGTH_SHORT).show();

                            listCarrinho2.add(Double.parseDouble(vt.getText().toString()));
                            btn_finalizar.setVisibility(View.VISIBLE);


                            for(i=0;i<listCarrinho2.size();i++){
                                vtst+=listCarrinho2.get(i).doubleValue();
                                vt.setText(String.valueOf(vtst));
                                car.setPreco_total(vtst);
                            }
                        }
                    }
                });


                dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();




                    }
                });
                dialog.create().show();



            }
        });




        //FIM adapter lista recomendados
        //  lista_recomendados

/*
        Slide slide = new Slide(Gravity.BOTTOM);
        getWindow().setEnterTransition(new android.transition.Explode());*/


        //Ativar o botão
        // getSupportActionBar().setTitle("Seu titulo aqui");


    }





    private List<Produtos> todosOsCursos() {
        return new ArrayList<>(Arrays.asList(


                new Produtos(0 , R.drawable.guaranaa,0,0,"Guaraná antártica  ","Guaraná antártica  lata 200ml",2.35),
                new Produtos(0, R.drawable.guarana,0,0,"Guaraná antártica ","Guaraná antártica 2L",4.25),
                new Produtos(0, R.drawable.sucolaranja,0,0,"Suco de jaranja ","Suco de jaranja copo 200ml",1.30),
                new Produtos(0, R.drawable.sucoabacaxi,0,0,"Suco de abacaxi ","Suco de abacaxi copo  200ml",1.30),
                new Produtos(0, R.drawable. fantagarrafa,0,0,"Fanta laranja ","Fanta laranja  2L",4.15),
                new Produtos(0, R.drawable.coca,0,0,"Coca Cola 2L","Coca Cola",4.50)



        ));}










    //LISTA CARRINHO








    //FIM LISTA CARRINHO
    //SETA VOLTAR
    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                startActivity(new Intent(this, MainActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }

    // BOTÃO VOLTAR DO DISPOSITIVO

    @SuppressLint("NewApi")
    @Override
    public void onBackPressed(){

        //Botão BACK padrão do android

        finishAffinity(); //Método para matar a activity e não deixa-lá indexada na pilhagem
        overridePendingTransition(R.anim.pai_entrando, R.anim.botton_out);

        //ENVIAR LISTA PARA ACTTIVITY1
        Intent inten = new Intent(Main2Activity.this,MainActivity.class);
        boolean car_teste = true;
        inten.putExtra("valo",car_teste );

        inten.putExtra("val", (Serializable) listCarrinho);
        // startActivity(new Intent(this, MainActivity.class)); //O efeito ao ser pressionado do botão (no caso abre a activity)
        //  startActivity(new Intent(this, MainActivity.class));
        startActivity(inten);
        //FIM ENVIAR LISTA PARA ACTTIVITY1

        return;
    }

    @Override
    public void onClikListener(View view, final int position) {




        final AlertDialog.Builder dialog = new AlertDialog.Builder(Main2Activity.this);
        dialog.setMessage("Desejar retirar este item do carrinho ?");
        final View finalView = view;
        dialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(sharedData.getValue().size()> 0){

                    car.setId_carrinho(position);
                   //car.setStatus("1");
                  //banco.aualizarCarrinho(position);


                    Double pg =0.0;
                    Double pgt=0.0;
                    final TextView vt = (TextView)findViewById(R.id.pretotal);
                    adapt.removerItemClick(position);
                    sharedData.setValue(adapt.carrinho);
                    listCarrinho2.remove(position);

                    for(int cr =0; cr <adapt.carrinho.size();cr++){

                        pg+=listCarrinho2.get(cr).doubleValue();
                    }
                    car.setPreco_total(pg);


                    // Toast.makeText(this, "VALOR"+pg, Toast.LENGTH_LONG).show();
                    vt.setText(String.valueOf(pg));
                    pg=0.0;
                    pgt=0.0;
                    if(adapt.carrinho.size() < 1){
                        btn_finalizar.setVisibility(GONE);
                    }








                }else{
                    Double pg =0.0;
                    Double pgt=0.0;
                    final TextView vt = (TextView)findViewById(R.id.pretotal);
                    adapt.removerItemClick(position);
                    listCarrinho2.remove(position);

                    for(int cr =0; cr <listCarrinho2.size();cr++){

                        pg+=listCarrinho2.get(cr).doubleValue();
                    }
                    car.setPreco_total(pg);


                    // Toast.makeText(this, "VALOR"+pg, Toast.LENGTH_LONG).show();
                    vt.setText(String.valueOf(pg));
                    pg=0.0;
                    pgt=0.0;
                    if(adapt.carrinho.size() < 1){
                        btn_finalizar.setVisibility(GONE);
                    }

                }

            }

        });
        dialog.create().show();

    }



    //INÍCIO CLASS CARREGAMENTO


    public class AsyncCircular extends AsyncTask<Void, Integer, Void> {
        String color ="#ffd149";
        CoordinatorLayout cord = (CoordinatorLayout)findViewById(R.id.test_3);
        TextView tes_1 = (TextView)findViewById(R.id.tes_2);
        LinearLayout tes = (LinearLayout) findViewById(R.id.tes);

        @Override
        protected void onPreExecute() {
            cord.setVisibility(GONE);
            tes_1.setVisibility(GONE);
            tes.setVisibility(GONE);
            image.setVisibility(View.VISIBLE);
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
            cord.setVisibility(View.VISIBLE);
            tes_1.setVisibility(View.VISIBLE);
            tes.setVisibility(View.VISIBLE);
            //CRIA PEDIDO

            Pedidos ped = new Pedidos();
            Date data = new Date();
            ped.setData_pedido(data);
            ped.setValor_total(pt);
            ped.setStatus("EM ESPERA");
            ped.setId_pedidos(1);
            ped.setId_usuarios(1);
            ped.adicinarPedido(ped);
            ped.setForma_Pagamento(forma_Pagamento);
            forma_Pagamento="";
            ped.setForma_Pagamento("");
            //FIM CRIA PEDIDO
            final AlertDialog.Builder aler = new AlertDialog.Builder(Main2Activity.this);
            aler.setMessage("Seu pedido foi finalizado com sucesso.Agora você pode acompanhá-lo na aba pedidos.O valor a pagar é : R$ "+pt)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    }).create().show();

            // linearBar.setVisibility(View.GONE);
        }
    }



    //FIM INÍCIO CLASS CARREGAMENTO
}
