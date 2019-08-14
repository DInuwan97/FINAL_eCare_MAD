package com.xyz.kalubo.ecare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Ecare.db";
    public static final String TABLE_NAME = "patient_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "PATIENT_NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PASSWORD";
    public static final String COL_5 = "C_PASSWORD";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,PATIENT_NAME TEXT,EMAIL TEXT,PASSWORD TEXT,C_PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String username,String email,String password,String confirm_password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,username);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,password);
        contentValues.put(COL_5,confirm_password);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;

    }


    //for login activity
    public boolean checkUser(String email,String password){

        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_3 + "=?" + " and " + COL_4 + "=?";
        String[] selectionArgs = {email,password};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count > 0)
            return true;
        else
            return false;
    }
}
