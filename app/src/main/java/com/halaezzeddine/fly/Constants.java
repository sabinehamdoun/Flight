package com.halaezzeddine.fly;

import android.database.sqlite.SQLiteDatabase;

public class Constants {

    public static final String DB_NAME = "flyDB"; // the name of the database
    public static final int DB_VERSION = 1; // the version of the database
    public static final String TABLE_NAME = "flight"; //table name

    //columns' names:
    public static final String ID = "id";
    public static final String SOURCE = "source";
    public static final String DESTINATION = "destination";
    public static final String DEPARTURE = "departure";
    public static final String ARRIVAL = "arrival";
    public static final String TRAVELCLASS = "travelclass";



    ///creating table query:
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("
            + ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SOURCE + " TEXT NOT NULL,"
            + DESTINATION + " TEXT,"
            + DEPARTURE+ " TEXT,"
            + ARRIVAL+" TEXT,"
            + TRAVELCLASS+" TEXT);";

}
