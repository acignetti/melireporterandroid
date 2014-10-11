package com.groppius.melireport.entities.buyer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.groppius.melireport.database.DataBaseHelper;
import com.groppius.melireport.database.MeliReportDbGlobalsInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julio on 11/10/14.
 */
public class BuyerRepository implements MeliReportDbGlobalsInterface {

    private DataBaseHelper dataBaseHelper;
    private Context context;
    private final String[] COLUMNS = {BUYER_COLUMN_ID, BUYER_COLUMN_NAME, BUYER_COLUMN_LAST_NAME,
            BUYER_COLUMN_EMAIL, BUYER_COLUMN_ADDRESS };

    public BuyerRepository(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context);
    }

    public ArrayList<Buyer> cursorToBuyer(Cursor cursor) {
        ArrayList<Buyer> buyers = new ArrayList<Buyer>();
        if(cursor != null) {
            cursor.moveToFirst();
            do {
                Buyer buyer = new Buyer();
                buyer.setBuyer_id(cursor.getLong(cursor.getColumnIndex(BUYER_COLUMN_ID)));
                buyer.setBuyer_name(cursor.getString(cursor.getColumnIndex(BUYER_COLUMN_NAME)));
                buyer.setBuyer_last_name(cursor.getString(cursor.getColumnIndex(BUYER_COLUMN_LAST_NAME)));
                buyer.setBuyer_email(cursor.getString(cursor.getColumnIndex(BUYER_COLUMN_EMAIL)));
                buyer.setBuyer_address(cursor.getString(cursor.getColumnIndex(BUYER_COLUMN_ADDRESS)));
                buyers.add(buyer);
            } while (cursor.moveToNext());
        }
        return buyers;
    }

    public void insert(Buyer buyer) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BUYER_COLUMN_ID, buyer.getBuyer_id());
        values.put(BUYER_COLUMN_NAME, buyer.getBuyer_name());
        values.put(BUYER_COLUMN_LAST_NAME, buyer.getBuyer_last_name());
        values.put(BUYER_COLUMN_ADDRESS, buyer.getBuyer_address());
        values.put(BUYER_COLUMN_EMAIL, buyer.getBuyer_email());

        db.insert(BUYER_TABLE_NAME,
                null,
                values);
        db.close();
    }

    public int update(Buyer buyer) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BUYER_COLUMN_ID, buyer.getBuyer_id());
        values.put(BUYER_COLUMN_NAME, buyer.getBuyer_name());
        values.put(BUYER_COLUMN_LAST_NAME, buyer.getBuyer_last_name());
        values.put(BUYER_COLUMN_ADDRESS, buyer.getBuyer_address());
        values.put(BUYER_COLUMN_EMAIL, buyer.getBuyer_email());

        int response = db.update(BUYER_TABLE_NAME,
                                values,
                                BUYER_COLUMN_ID + "= ?",
                                new String[] {String.valueOf(buyer.getBuyer_id())});
        db.close();
        return response;
    }

    public void delete(Buyer buyer) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        db.delete(BUYER_TABLE_NAME,
                BUYER_COLUMN_ID +" = ?",
                new String[] { String.valueOf(buyer.getBuyer_id())});
        db.close();
    }

    public Buyer get(int buyerId) {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor =
                db.query(BUYER_TABLE_NAME, // a. table
                        COLUMNS, // b. column names
                        BUYER_COLUMN_ID + " = ?", // c. selections
                        new String[] { String.valueOf(buyerId) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        return cursorToBuyer(cursor).get(0);
    }

    public List<Buyer> get() {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String query = "SELECT * FROM " + BUYER_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursorToBuyer(cursor);
    }

}
