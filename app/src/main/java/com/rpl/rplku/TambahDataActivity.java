package com.rpl.rplku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Fathur on 4/14/2017.
 */

public class TambahDataActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edttxtNamaBarang,edttxtJmlBarang;
    private Button btnTambahData,btnPindahListV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        edttxtNamaBarang = (EditText) findViewById(R.id.edtxtNamaBarang);
        edttxtJmlBarang = (EditText) findViewById(R.id.edtxtJmlBarang);

        btnTambahData = (Button) findViewById(R.id.btnTambahData);
        btnPindahListV = (Button) findViewById(R.id.btnPindahListV);
        btnTambahData.setOnClickListener(this);
        btnPindahListV.setOnClickListener(this);

    }

    public void tambahData(){
        final String namabarang = edttxtNamaBarang.getText().toString().trim();
        final String jmlbarang = edttxtJmlBarang.getText().toString().trim();

        class TambahData extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDataActivity.this,"Menambahkan","Harap Tunggu",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TambahDataActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_BARANG_NAMA,namabarang);
                params.put(Config.KEY_BARANG_JML,jmlbarang);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD,params);
                return res;
            }
        }

        TambahData td = new TambahData();
        td.execute();
    }

    @Override
    public void onClick(View v) {
        if(v==btnPindahListV){
            Intent i = new Intent(this,LresepActivity.class);
            startActivity(i);
            finish();
        }
        if(v==btnTambahData){
            tambahData();
        }
    }
}
