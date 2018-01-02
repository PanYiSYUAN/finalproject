package com.example.student.billing;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.provider.SearchRecentSuggestions;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btn_all;
    Button btn_income;
    Button btn_remove;
    public SQLiteDatabase dbrw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_all = (Button)findViewById(R.id.btn_all);
        btn_income = (Button)findViewById(R.id.btn_income);
        btn_remove = (Button)findViewById(R.id.btn_remove);
        MyDBHelper dbHelper = new MyDBHelper(this);
        dbrw = dbHelper.getWritableDatabase();
        btn_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_income = new Intent();
                i_income.setClass(MainActivity.this,incomeMan.class);
                startActivity(i_income);
            }
        });


        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_all = new Intent();
                i_all.setClass(MainActivity.this,allMan.class);
                startActivity(i_all);
            }
        });

    }

}
