package com.example.face;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "student_details.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE StuPer (Stu_Id text, Stu_Name text, Stu_Age text, Stu_Contact text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS StuPer");
        onCreate(sqLiteDatabase);
    }
    public Boolean insertrow(String id, String name, String age, String contact)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues db_value=new ContentValues();
        db_value.put("Stu_Id",id);
        db_value.put("Stu_Name",name);
        db_value.put("Stu_Age",age);
        db_value.put("Stu_Contact",contact);
        long res =  db.insert("StuPer",null,db_value);
        if(res==-1)
            return false;
        else
            return true;


    }

    public Boolean updaterow(String id, String name, String age, String contact)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues db_value=new ContentValues();
        db_value.put("Stu_Name",name);
        db_value.put("Stu_Age",age);
        db_value.put("Stu_Contact",contact);
        Cursor cursor= db.rawQuery("select * from StuPer where Stu_id = ?",new String[]{id});
        if (cursor.getCount()>0) {
            long res = db.update("StuPer", db_value, "Stu_id=?", new String[]{id});
            if (res == -1)
                return false;
            else
                return true;
        }else
        {
            return false;
        }


    }


    public Boolean deleterow(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor= db.rawQuery("select * from StuPer where Stu_id = ?",new String[]{id});
        if (cursor.getCount()>0) {
            long res = db.delete("StuPer",  "Stu_id=?", new String[]{id});
            if (res == -1)
                return false;
            else
                return true;
        }else
        {
            return false;
        }


    }


    public Cursor viewrows()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor= db.rawQuery("select * from StuPer",null);
        return cursor;


    }
}