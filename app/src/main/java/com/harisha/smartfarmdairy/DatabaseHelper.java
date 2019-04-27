package com.harisha.smartfarmdairy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME="shyam.db";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE shyam(seeds INTEGER,fert INTEGER,pest INTEGER,elecandm INTEGER,wages INTEGER,others INTEGER,note TEXT)");
        db.execSQL("CREATE TABLE income(cost INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS shyam");
        onCreate(db);
    }
    public boolean insertdata(int seeds,int fert,int pest,int ecm,int wages,int others,String note){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("seeds",seeds);
        values.put("fert",fert);
        values.put("pest",pest);
        values.put("elecandm",ecm);
        values.put("wages",wages);
        values.put("note",note);
        values.put("others",others);
        long result=db.insert("shyam",null,values);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public boolean insertincome(int cost){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("cost",cost);
        long result=db.insert("income",null,values);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public Cursor getexpenses(){
    SQLiteDatabase db=getWritableDatabase();
    Cursor res=db.rawQuery("select * from shyam",null);
    return res;
    }
    public Cursor getincome(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor res=db.rawQuery("select * from income",null);
        return res;
    }
    public void deleteAll(){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DROP TABLE shyam");
        db.execSQL("DROP TABLE income");
        onCreate(db);

    }
}
