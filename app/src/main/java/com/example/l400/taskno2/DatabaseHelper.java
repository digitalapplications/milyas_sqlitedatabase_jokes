package com.example.l400.taskno2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by l400 on 10/7/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "jokes.db";
    public static final String TABLE_NAME= "entery";
    public static final int VERSION =10;
    public static final String Jokes_TITLE = "title";
    public static final String Jokes_TYPE = "type";
    public static String Jokes_DESCRIPTION = "description";

    SQLiteDatabase db;
    public DatabaseHelper(Context context ) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qure = "CREATE TABLE " +TABLE_NAME+ " (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, type TEXT);)";
        sqLiteDatabase.execSQL(qure);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
          String drop = "DROP TABLE IF EXISTS " +TABLE_NAME ;
        sqLiteDatabase.execSQL(drop);
        onCreate(sqLiteDatabase);
        Log.d("createdatabase" , "database has been created");
    }


    public boolean insertdata(String title,String description, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Jokes_TITLE,title);
        values.put(Jokes_DESCRIPTION,description);
        values.put(Jokes_TYPE,type);
       long res=  db.insert(TABLE_NAME,null,values);
        if(res == -1){
            return false;
        }
        else
            return true;
    }


   public List<String> getTitle (){
       db = this.getReadableDatabase();
        String title = "SELECT DISTINCT type FROM entery" ;
        List<String> Title = new ArrayList<>();
        Cursor cursor = db.rawQuery(title,null);
        if(cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
             String  t = cursor.getString(cursor.getColumnIndex("type"));
                Title.add(t);
            }while (cursor.moveToNext());
        }
        return Title;
    }
    public List<String> getFamily (String ite){
        db = this.getReadableDatabase();
        String title = "SELECT * FROM entery WHERE type = '"+ite+"' " ;
        List<String> Title = new ArrayList<>();
        Cursor cursor = db.rawQuery(title,null);
        if(cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                String  t = cursor.getString(cursor.getColumnIndex("description"));
                Title.add(t);
            }while (cursor.moveToNext());
        }
        return Title;
    }
    public int countData(String title){
        String query = "SELECT * FROM entery WHERE type = '"+title+"' " ;
        List<String> Title = new ArrayList<>();
        Cursor cursor = db.rawQuery(query,null);

        int num = cursor.getCount();
        return num;
    }

    public int countData1(String title){
        String query = "SELECT * FROM entery WHERE description = '"+title+"' " ;
        List<String> Title = new ArrayList<>();
        Cursor cursor = db.rawQuery(query,null);

        int num = cursor.getCount();
        return num;
    }


public List<String> getData(String title){
    db = this.getReadableDatabase();
    List<String> data = new ArrayList<>();
    String qurre = "SELECT * FROM entery WHERE title = '"+title+"'";
    Cursor cursor=db.rawQuery(qurre,null);

    if(cursor.getCount()>0){
        cursor.moveToFirst();
        do {
            String d = cursor.getString(cursor.getColumnIndex("type"));
            data.add(d);
        }while (cursor.moveToNext());
    }
    return data;
}
    public String gettype (String tt ){
        db = this.getReadableDatabase();
        String title = "SELECT * FROM entery WHERE type = '"+tt+"'";
        String t = "";
        Cursor cursor = db.rawQuery(title,null);
        if(cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                  t = cursor.getString(cursor.getColumnIndex("title"));
            }while (cursor.moveToNext());
        }
        return t;
    }

    public String getDescription ( String tit){
        db = this.getReadableDatabase();
        String title = "SELECT * FROM entery WHERE type = '"+tit+"' ";
        String t = "";
        Cursor cursor = db.rawQuery(title,null);
        if(cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do {
                t = cursor.getString(cursor.getColumnIndex("type"));
            }while (cursor.moveToNext());
        }
        return t;
    }
    public void deleteData(String  d){
        db = this.getWritableDatabase();
        String q = "DELETE FROM entery WHERE description == '"+d+"'";
        db.execSQL(q);
        db.close();
    }

    public void open(){
     db = this.getWritableDatabase();
    }
    }

