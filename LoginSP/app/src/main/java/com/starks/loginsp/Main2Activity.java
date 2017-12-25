package com.starks.loginsp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    DataBaseHelper db = new DataBaseHelper(this);
    Button button;
    TextView namevw,passvw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        button = (Button)findViewById(R.id.button);
        namevw = (TextView)findViewById(R.id.textView);
        passvw = (TextView)findViewById(R.id.textView2);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        SharedPreferences sharedPreferences =getSharedPreferences("MainActivity", Context.MODE_PRIVATE);
        final String name = sharedPreferences.getString("username","NA");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pass=db.readData(name);

                namevw.setText(name);
                passvw.setText(pass);


            }
        });
    }

}
