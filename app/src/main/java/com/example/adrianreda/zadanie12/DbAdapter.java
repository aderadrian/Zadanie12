package com.example.adrianreda.zadanie12;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter extends SQLiteOpenHelper{
    private static final String databaseName = "PhoneDatabase";
    private static final String databaseTableName = "PhonesTable";
    private String addedNumber;

    public DbAdapter(Context context) {
        super(context,databaseName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table " + databaseTableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,PRODUCER TEXT,MODEL TEXT,YEAR INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + databaseTableName);
        onCreate(database);
    }

    public boolean addData(String id,String producer,String model,String year){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("PRODUCER",producer);
        contentValues.put("MODEL",model);
        contentValues.put("YEAR",year);
        long b = sqLiteDatabase.insert(databaseTableName,null ,contentValues);
        addedNumber = id;

        if ( b == -1 ) return false;
        else return true;

    }




    public boolean updateData(String id,String producer,String model,String year){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID",id);
        contentValues.put("PRODUCER",producer);
        contentValues.put("MODEL",model);
        contentValues.put("YEAR",year);
        sqLiteDatabase.update(databaseTableName,contentValues,"ID = ?",new String[]{id});
        return true;
    }
    public String returnAddedNumber() {
        return addedNumber;
    }
    public SQLiteCursor getData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        SQLiteCursor cursor = (SQLiteCursor) sqLiteDatabase.rawQuery("SELECT * FROM " + databaseTableName, null);
        return cursor;
    }

    public Integer delateData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(databaseTableName,"ID = ?",new String[]{id});
    }
}

