package com.starks.loginsp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper  extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME="usersdata.db";
    private static final String TABLE_NAME="usersdetails";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="username";
    private static final String COLUMN_PASS="password";

    private static final String TABLE_CREATE="create table usersdetails(id int not null primary key,username text not null,password text not null)";

    SQLiteDatabase db;
    


    DataBaseHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(TABLE_CREATE);
    this.db = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS"+ TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        this.onCreate(sqLiteDatabase);
    }

    public void insertData(LoginData log) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select * from  usersdetails";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME,log.getUser());
        values.put(COLUMN_PASS,log.getPass());
        db.insert(TABLE_NAME,null,values);


    }

    public String readData(String name) {

        db= this.getReadableDatabase();
        String query = "select username,password from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        b = "not found";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                if(a.equals(name)){
                        b = cursor.getString(1);
                        break;

                }

            }while (cursor.moveToNext());
        }
        return b;
    }
}
