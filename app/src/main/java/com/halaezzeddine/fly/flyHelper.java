package com.halaezzeddine.fly;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class flyHelper extends SQLiteOpenHelper {

    //public SQLiteDatabase sqLiteDatabase;

    //constructor
    public flyHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Constants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int 	newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME);
        onCreate(db);
    }


    ///Add a form to db
    public long addForm(Form form){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.SOURCE, form.getFrom());
        contentValues.put(Constants.DESTINATION, form.getTo());
        contentValues.put(Constants.DEPARTURE, form.getDeparture());
        contentValues.put(Constants.ARRIVAL, form.getArrival());
        contentValues.put(Constants.TRAVELCLASS, form.getTravelClass());

        //sqLiteDatabase = this.getWritableDatabase();
        //sqLiteDatabase.insert(Constants.TABLE_NAME, null, contentValues);
        long id = db.insert(Constants.TABLE_NAME, null, contentValues);

        db.close();

        return id;

    }

    ///Update a form to db
    public void updateForm(String id, Form form){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.SOURCE, form.getFrom());
        contentValues.put(Constants.DESTINATION, form.getTo());
        contentValues.put(Constants.DEPARTURE, form.getDeparture());
        contentValues.put(Constants.ARRIVAL, form.getArrival());
        contentValues.put(Constants.TRAVELCLASS, form.getTravelClass());

        db.update(Constants.TABLE_NAME, contentValues, Constants.ID +" = ?", new String[]{id});
        db.close();




    }

    //Delete entries
    public void deleteInfo(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.ID+" = ?", new String[]{id});
        db.close();
    }

    //another way
    public long insertInfo(String from, String to, String dep, String arr, String tClass){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.SOURCE, from);
        values.put(Constants.DESTINATION, to);
        values.put(Constants.DEPARTURE, dep);
        values.put(Constants.ARRIVAL, arr);
        values.put(Constants.TRAVELCLASS, tClass);

        long id = db.insert(Constants.TABLE_NAME, null, values);
        db.close();

        return id;

    }

    public ArrayList<Form> getAllData(String orderBy){
        ArrayList<Form> arrayList = new ArrayList<>();

        //query for Select all info from db
        String selectQuery = " SELECT * FROM "+Constants.TABLE_NAME + " ORDER BY "+orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        //manage columns
        if(cursor.moveToNext()){

            do{
                @SuppressLint("Range") Form form = new Form(
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.ID)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.SOURCE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.DESTINATION)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.DEPARTURE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.ARRIVAL)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.TRAVELCLASS))
                );

                arrayList.add(form);

            }while (cursor.moveToNext());
        }

        db.close();
        return arrayList;
    }


}
