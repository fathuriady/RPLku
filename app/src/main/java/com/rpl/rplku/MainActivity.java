package com.rpl.rplku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnResep,btnTentang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnResep = (Button)findViewById(R.id.btnResep);
        btnTentang = (Button)findViewById(R.id.btnTentang);

        btnTentang.setOnClickListener(this);
        btnResep.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==btnResep){
            startActivity(new Intent(this,LresepActivity.class));
        }

        if(v==btnTentang){
            startActivity(new Intent(this,TentangActivity.class));
        }
    }
}
