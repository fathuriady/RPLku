package com.rpl.rplku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 4/9/2017.
 */

public class LresepActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lvItem;
    private String JSON_STRING;
    private Button btnPindahTambah;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lresep);
        lvItem = (ListView)findViewById(R.id.listView);
        btnPindahTambah = (Button) findViewById(R.id.btnPindahTambah);
        getJSON();
        btnPindahTambah.setOnClickListener(this);
    }

    private void showBarang(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        try{
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0;i<result.length();i++){
                JSONObject jo = result.getJSONObject(i);
                String nama = jo.getString(Config.TAG_NAMA_BARANG);
                String jml = jo.getString(Config.TAG_JML_BARANG);


                HashMap<String,String> barang = new HashMap<>();
                barang.put(Config.TAG_NAMA_BARANG,nama);
                barang.put(Config.TAG_JML_BARANG,jml);

                list.add(barang);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(LresepActivity.this,list,R.layout.listview,
                new String[]{Config.TAG_NAMA_BARANG,Config.TAG_JML_BARANG},
                new int[]{R.id.txtNamaBarang,R.id.txtJmlBarang});

        lvItem.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LresepActivity.this,"Ambil Data","Tunggu Sebentar");
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showBarang();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GETALL);
                return s;
            }
        }
        GetJSON gj= new GetJSON();
        gj.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == btnPindahTambah){
            Intent i = new Intent(this,TambahDataActivity.class);
            startActivity(i);
            finish();
        }
    }


}
