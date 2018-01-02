package com.example.student.billing;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class allMan extends AppCompatActivity {
    Button serall,btn_remove;
    ListView all;
    public SQLiteDatabase dbrw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allman);
        all = (ListView) findViewById(R.id.all);
        serall = (Button)findViewById(R.id.serall);
        btn_remove = (Button)findViewById(R.id.btn_remove) ;
        MyDBHelper dbHelper = new MyDBHelper(this);
        dbrw = dbHelper.getWritableDatabase();

        serall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String index = "順序\n",sel = "收入/支出\n", type = "類型\n";
                String dis = "描述\n",money = "金額\n",data="日期\n";
                String[] colum = {"_ID","sel","type","dis","money","data"};

                Cursor c;
                c = dbrw.query("billTable",colum,null,null,null,null,null,null);

               if(c.getCount()>=0){
                    c.moveToFirst();
                    for(int i = 0; i<c.getCount(); i++){
                        index += (i+1) + "\n";
                        sel += c.getString(0)+"\n";
                        type += c.getString(1)+"\n";
                        dis += c.getString(2)+"\n";
                        money += c.getString(3) + "\n";
                        data += c.getString(4)+"\n";
                        c.moveToNext();
                    }
                    SimpleCursorAdapter adapter = new SimpleCursorAdapter(allMan.this,R.layout.transall,c,new String[] {"sel","type","dis","money","data"},
                    new int[] {R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5,R.id.textView6},0);
                    all.setAdapter(adapter);
                    Toast.makeText(allMan.this,"共有"+c.getCount()+"筆記錄",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeType();
            }
        });
    }
    public  void  removeType() {

        LayoutInflater layoutInflater = LayoutInflater.from(allMan.this) ;
        final View view1 = layoutInflater.inflate(R.layout.remove, null) ;

        new AlertDialog.Builder(allMan.this)
                .setTitle("移除項目").setView(view1).setPositiveButton("確定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText rmtypes = (EditText) (view1.findViewById(R.id.rmtype));
                EditText rmmoney = (EditText) (view1.findViewById(R.id.rmmoney));
                String rm =  rmtypes.getText().toString() ;
                String rm2 = rmmoney.getText().toString() ;
                if (rmtypes.getText().toString().equals("")||rmmoney.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"請輸入要刪除的值",Toast.LENGTH_SHORT).show();}

                else
                {
                    dbrw.delete("billTable", "dis=" +"'" + rm +"'", null ) ;
                    Toast.makeText(getApplicationContext(),"移除成功",Toast.LENGTH_SHORT).show();
                }
            }
        }).show()  ;
    }
}
