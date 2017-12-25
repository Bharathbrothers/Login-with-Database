package com.starks.loginsp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText)findViewById(R.id.username);
        password= (EditText)findViewById(R.id.password);

       SharedPreferences  sharedPreferences =getSharedPreferences("MainActivity", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("username","NA");
        String pass = sharedPreferences.getString("password","NA");
        if(name!="NA" && pass!="NA"){
            Toast.makeText(this, "user exists", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,Main2Activity.class);
            startActivity(intent);
            finish();
        }
        else{
            return;
        }




    }

    public void submit(View view) {

       SharedPreferences sharedPreferences =getSharedPreferences("MainActivity", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();

        editor.putString("username",username.getText().toString());
        editor.putString("password",password.getText().toString());
        editor.commit();

        Toast.makeText(MainActivity.this, "done",Toast.LENGTH_SHORT).show();
        LoginData log = new LoginData();
        log.setUser(username.getText().toString());
        log.setPass(password.getText().toString());

        dataBaseHelper.insertData(log);
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
        finish();



    }
}
