package com.rpl.rplku;


/**
 * Created by User on 4/16/2017.
 */

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class LihatData extends AppCompatActivity implements View.OnClickListener {

    private EditText edtxtNamaBarang;
    private EditText edtxtJmlBarang;
    private TextView txtId;
    private Button btnUpdate;
    private Button btnDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config.BARANG_ID);

        txtId = (TextView) findViewById(R.id.txtID);
        edtxtNamaBarang = (EditText) findViewById(R.id.edtxtNamaBarang);
        edtxtJmlBarang = (EditText) findViewById(R.id.edtxtJmlBarang);

        btnUpdate = (Button) findViewById(R.id.btnUpdateData);
        btnDelete = (Button) findViewById(R.id.btnDeleteData);

        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        txtId.setText(id);

        getBarang();
    }

    private void getBarang(){
        class GetBarang extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatData.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showBarang(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GETBARANG,id);
                return s;
            }
        }
        GetBarang ge = new GetBarang();
        ge.execute();
    }

    private void showBarang(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String namabarang = c.getString(Config.TAG_NAMA_BARANG);
            String jmlbarang = c.getString(Config.TAG_JML_BARANG);

            edtxtNamaBarang.setText(namabarang);
            edtxtJmlBarang.setText(jmlbarang);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateBarang(){
        final String namabarang = edtxtNamaBarang.getText().toString().trim();
        final String jmlbarang = edtxtJmlBarang.getText().toString().trim();

        class UpdateBarang extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatData.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(LihatData.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_BARANG_ID,id);
                hashMap.put(Config.KEY_BARANG_NAMA,namabarang);
                hashMap.put(Config.KEY_BARANG_JML,jmlbarang);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_BARANG,hashMap);

                return s;
            }
        }

        UpdateBarang ue = new UpdateBarang();
        ue.execute();
    }

    private void deleteBarang(){
        class DeleteBarang extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatData.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(LihatData.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_BARANG, id);
                return s;
            }
        }

        DeleteBarang de = new DeleteBarang();
        de.execute();
    }

    private void confirmDeleteBarang(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Yakin mau hapus barang ini?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteBarang();
                        startActivity(new Intent(LihatData.this,LresepActivity.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == btnUpdate){
            updateBarang();
        }

        if(v == btnDelete){
            confirmDeleteBarang();
        }
    }


}





/*
public class LihatData extends AppCompatActivity implements View.OnClickListener {

    private String id;
    private EditText edttxtNamaBarang,edttxtJmlBarang;
    Button btnUpdateData,btnDeleteData;
    private TextView txtId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        Intent intent = getIntent();
        id = intent.getStringExtra(Config.BARANG_ID);

        edttxtJmlBarang = (EditText)findViewById(R.id.edtxtJmlBarang);
        edttxtNamaBarang = (EditText)findViewById(R.id.edtxtNamaBarang);

        btnUpdateData =  (Button)findViewById(R.id.btnUpdateData);
        btnDeleteData = (Button)findViewById(R.id.btnDeleteData);

        txtId = (TextView)findViewById(R.id.txtID);
        txtId.setText(id);
        btnDeleteData.setOnClickListener(this);
        btnUpdateData.setOnClickListener(this);

    }

    private void getBarang(){
        class GetBarang extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatData.this,"Mengambil Data","Harap Sabar",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showData(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GETBARANG);
                return s;
            }
        }

        GetEmplo
    }

    @Override
    public void onClick(View v) {

    }
}*/
