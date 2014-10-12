package com.groppius.melireport.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by julio on 11/10/14.
 */
public class DataBaseHelper extends SQLiteOpenHelper implements MeliReportDbGlobalsInterface{
    private static final String DATABASE_NAME = "report_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "MeliReport";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /**
         * BUYERS TABLE CREATION
         */
        sqLiteDatabase.execSQL("CREATE TABLE " + BUYER_TABLE_NAME + " ("
                + BUYER_COLUMN_ID + " INTEGER,"
                + BUYER_COLUMN_ADDRESS + " TEXT,"
                + BUYER_COLUMN_EMAIL + " TEXT,"
                + BUYER_COLUMN_LAST_NAME + " TEXT,"
                + BUYER_COLUMN_NAME + " TEXT,"
                + BUYER_COLUMN_REAL_NAME + " TEXT"
                + ");");
        Log.d(TAG, "Table " + BUYER_TABLE_NAME + " created");
        /**
         * CATEGORIES TABLE CREATION
         */
        sqLiteDatabase.execSQL("CREATE TABLE " + CATEGORY_TABLE_NAME + " ("
                + CATEGORY_COLUMN_ID + " INTEGER,"
                + CATEGORY_COLUMN_NAME + " TEXT,"
                + CATEGORY_COLUMN_DESCRIPTION + " TEXT,"
                + CATEGORY_COLUMN_FATHER + " INTEGER"
                + ");");
        /**
         * ITEMS TABLE CREATION
         */
        sqLiteDatabase.execSQL("CREATE TABLE " + ITEM_TABLE_NAME + " ("
                + ITEM_COLUMN_ID + " INTEGER,"
                + ITEM_COLUMN_CATEGORY_ID_FK + " INTEGER REFERENCES "+ CATEGORY_TABLE_NAME +","
                + ITEM_COLUMN_COST + " INTEGER,"
                + ITEM_COLUMN_NAME+ " TEXT,"
                + ITEM_COLUMN_DESCRIPTION + " TEXT,"
                + ITEM_COLUMN_PRICE + " INTEGER,"
                + ITEM_COLUMN_PUBLISHED_ON + " NUMERIC,"
                + ITEM_COLUMN_PUBLISHED_ENDED + " NUMERIC,"
                + ITEM_COLUMN_THUMBNAIL + " TEXT"
                + ");");
        /**
         * USERS TABLE CREATION
         */
        sqLiteDatabase.execSQL("CREATE TABLE " + USER_TABLE_NAME + " ("
                + USER_COLUMN_ID + " INTEGER PRIMARY KEY,"
                + USER_COLUMN_NAME + " TEXT,"
                + USER_COLUMN_REAL_NAME + " TEXT,"
                + USER_COLUMN_LAST_NAME+ " TEXT,"
                + USER_COLUMN_PASSWORD + " TEXT,"
                + USER_COLUMN_TOKEN + " TEXT,"
                + USER_COLUMN_EMAIL + " TEXT,"
                + USER_COLUMN_THUMBNAIL + " TEXT"
                + ");");
        /**
         * PAYMENTS TABLE CREATION
         */
        sqLiteDatabase.execSQL("CREATE TABLE " + PAYMENT_TABLE_NAME + " ("
                + PAYMENT_COLUMN_ID + " INTEGER,"
                + PAYMENT_COLUMN_NAME + " TEXT,"
                + PAYMENT_COLUMN_DESCRIPTION + " TEXT"
                + ");");
        /**
         * SALES TABLE CREATION
         */
        sqLiteDatabase.execSQL("CREATE TABLE " + SALE_TABLE_NAME + " ("
                + SALE_COLUMN_ID + " INTEGER,"
                + SALE_COLUMN_USER_ID + " INTEGER REFERENCES " + USER_TABLE_NAME + ","
                + SALE_COLUMN_ITEM_ID + " INTEGER REFERENCES " + ITEM_TABLE_NAME + ","
                + SALE_COLUMN_PAYMENT_ID + " INTEGER REFERENCES " + PAYMENT_TABLE_NAME + ","
                + SALE_COLUMN_QUANTITY + " INTEGER,"
                + SALE_COLUMN_BOUGHT_ON + " NUMERIC,"
                + SALE_COLUMN_PAYED_ON + " NUMERIC,"
                + SALE_COLUMN_STATUS + " INTEGER"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Logs that the database is being upgraded
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");

        // Kills the table and existing data
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS notes");

        // Recreates the database with a new version
        onCreate(sqLiteDatabase);
    }
}
