package com.rpl.rplku;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by User on 4/9/2017.
 */

public class LresepActivity extends AppCompatActivity {
    protected ListView lvItem;
    protected String[] menu = new String[]{"Soto Betawi","Dodol Garut","Kerak Telor","Es Doger",
                                "Nasi Goreng","Nasi Uduk"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lresep);

        lvItem = (ListView)findViewById(R.id.list_view);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);

        lvItem.setAdapter(adapter);

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LresepActivity.this, "Kamu mengklik " + menu[position] , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
