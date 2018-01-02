package com.example.student.billing;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class incomeMan extends AppCompatActivity {
    public Button addincome;
    public EditText inmoney,indata,indis,intypes,insel;
    public SQLiteDatabase dbrw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_man);

        MyDBHelper dbHelper = new MyDBHelper(this);
        dbrw = dbHelper.getWritableDatabase();

        addincome = (Button)findViewById(R.id.addincome);
        inmoney = (EditText)findViewById(R.id.inmoney);
        indata = (EditText)findViewById(R.id.indata);
        indis = (EditText)findViewById(R.id.indis);

        intypes = (EditText) findViewById(R.id.intypes);
        insel = (EditText) findViewById(R.id.insel);

        addincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inmoney.getText().toString().equals("")||indata.getText().toString().equals(""))
                    Toast.makeText(incomeMan.this,"請輸入完整資料",Toast.LENGTH_LONG).show();
                else{
                    ContentValues incv = new ContentValues();

                    incv.put("type",intypes.getText().toString());
                    incv.put("sel",insel.getText().toString());
                    incv.put("dis",indis.getText().toString());
                    incv.put("money",inmoney.getText().toString());
                    incv.put("data",indata.getText().toString());
                    dbrw.insert("billTable",null,incv);

                    Toast.makeText(incomeMan.this,"新增完成",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
