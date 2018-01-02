package com.example.student.billing;


import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class MyDBHelper extends SQLiteOpenHelper{

    private static final String database = "mydata.db";
    private static final int version = 2;

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version)
    {
        super(context,name,factory,version);
    }

    public  MyDBHelper(Context context){
        this(context,database,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE billTable(_id integer primary key autoincrement,"+"sel text no null,"+"type text no null,"+"dis text no null,"+"money real no null,"+"data text no null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS billTable");
        onCreate(db);
    }

}
