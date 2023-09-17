package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.model.Category;
import com.example.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "mydatabase3.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userTable = "create table users(" +
                "id integer primary key autoincrement," +
                "name vavrchar(20)," +
                "summa integer," +
                "holat int default 0," +
                "c_id integer," +
                "sana TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";


        String categoryTable = "create table category(" +
                "id integer primary key autoincrement," +
                "name vavrchar(20))";

        db.execSQL(userTable);
        db.execSQL(categoryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists category");
    }

    public boolean insertUser(String name, int sum, int  holat, int c_id){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name",name);
        values.put("summa",sum);
        values.put("holat",holat);
        values.put("c_id",c_id);

        long result = db.insert("users",null,values);

        if (result ==-1){
            return  false;
        }
        else
            return true;
    }

    public boolean insertUser(int id,String name, int sum, int  holat, int c_id){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id",id);
        values.put("name",name);
        values.put("summa",sum);
        values.put("holat",holat);
        values.put("c_id",c_id);

        long result = db.insert("users",null,values);

        if (result ==-1){
            return  false;
        }
        else
            return true;
    }

    public boolean updateUser(int id,String name, int sum){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name",name);
        values.put("summa",sum);

        Cursor cursor = db.rawQuery("select * from users where id = ?",new String[]{id+""});

        if(cursor.getCount()>0){
            long result = db.update("users",values,"id=?",new String[]{id+""});

            if (result ==-1){
                return  false;
            }
            else
                return true;
        }else {
            return false;
        }
    }


 public boolean updateCategory(int id,String name){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id",id);
        values.put("name",name);

        Cursor cursor = db.rawQuery("select * from category where id = ?",new String[]{id+""});

        if(cursor.getCount()>0){
            long result = db.update("category",values,"id=?",new String[]{id+""});

            if (result ==-1){
                return  false;
            }
            else
                return true;
        }else {
            return false;
        }
    }



    public boolean deleteUser(int id){

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from users where id = ?",new String[]{id+""});

        if(cursor.getCount()>0){
            long result = db.delete("users","id=?",new String[]{id+""});

            if (result ==-1){
                return  false;
            }
            else
                return true;
        }else {
            return false;
        }
    }

    public boolean deleteCategory(int id){

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from category where id = ?",new String[]{id+""});

        if(cursor.getCount()>0){
            long result = db.delete("category","id=?",new String[]{id+""});

            if (result ==-1){
                return  false;
            }
            else
                return true;
        }else {
            return false;
        }
    }


    public List<User> getUsers(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select id,name,summa,holat,c_id,  STRFTIME('%d ',sana) || " +
                "case  STRFTIME('%m', sana)" +
                "when '01' then 'yan'" +
                " when '02' then 'feb'" +
                " when '03' then 'mar'" +
                " when '04' then 'apr' " +
                "when '05' then 'may' " +
                "when '06' then 'iyn' " +
                "when '07' then 'iyl' " +
                "when '08' then 'avg' " +
                "when '09' then 'sen' " +
                "when '10' then 'okt' " +
                "when '11' then 'noy' " +
                "when '12' then 'dek' " +
                "else '' end\n" +
                "as month  from users",null);

        List<User> list = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int sum = cursor.getInt(2);
            int holat = cursor.getInt(3);
            int c_id = cursor.getInt(4);
            String sana = cursor.getString(5);

            list.add(new User(id,name,sum,holat,c_id,sana));
        }

        return  list;
    }

    public List<Category> getCategory(){

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from category",null);

        List<Category> list = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            list.add(new Category(id,name));
        }

        return  list;
    }

    public boolean insertCategory(String name){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name",name);
        long result = db.insert("category",null,values);

        if (result ==-1){
            return  false;
        }
        else
            return true;
    }

    public boolean insertCategory(int id,String name){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id",id);
        values.put("name",name);

        long result = db.insert("category",null,values);

        if (result ==-1){
            return  false;
        }
        else
            return true;
    }
}
