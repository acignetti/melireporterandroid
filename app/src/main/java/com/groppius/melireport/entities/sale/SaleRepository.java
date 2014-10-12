package com.groppius.melireport.entities.sale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.groppius.melireport.database.DataBaseHelper;
import com.groppius.melireport.database.MeliReportDbGlobalsInterface;
import com.groppius.melireport.misc.Helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julio on 11/10/14.
 */
public class SaleRepository implements MeliReportDbGlobalsInterface {
    private DataBaseHelper dataBaseHelper;
    private Context context;
    private final String[] COLUMNS = {SALE_COLUMN_ID, SALE_COLUMN_ITEM_ID, SALE_COLUMN_BOUGHT_ON,
            SALE_COLUMN_PAID_ON, SALE_COLUMN_PAYMENT_ID, SALE_COLUMN_QUANTITY,
            SALE_COLUMN_STATUS, SALE_COLUMN_USER_ID};

    public SaleRepository(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context);
    }

    public ArrayList<Sale> cursorToSale(Cursor cursor) {
        ArrayList<Sale> sales = new ArrayList<Sale>();
        if(cursor != null) {
            cursor.moveToFirst();
            do {
                Sale sale = new Sale();
                sale.setSale_id(cursor.getLong(cursor.getColumnIndex(SALE_COLUMN_ID)));
                sale.setSale_id(cursor.getLong(cursor.getColumnIndex(SALE_COLUMN_ITEM_ID)));
                sale.setSale_bought_on(Helper.dateFormatter(cursor.getString(cursor.getColumnIndex(SALE_COLUMN_BOUGHT_ON))));
                sale.setSale_payed_on(Helper.dateFormatter(cursor.getString(cursor.getColumnIndex(SALE_COLUMN_PAID_ON))));
                sale.setSale_payment_id(cursor.getLong(cursor.getColumnIndex(SALE_COLUMN_PAYMENT_ID)));
                sale.setSale_quantity(cursor.getInt(cursor.getColumnIndex(SALE_COLUMN_QUANTITY)));
                sale.setSale_status(cursor.getInt(cursor.getColumnIndex(SALE_COLUMN_STATUS)));
                sale.setSale_user_id(cursor.getLong(cursor.getColumnIndex(SALE_COLUMN_USER_ID)));
                sales.add(sale);
            } while (cursor.moveToNext());
        }
        return sales;
    }

    public void insert(Sale sale) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SALE_COLUMN_ID, sale.getSale_id());
        values.put(SALE_COLUMN_ITEM_ID, sale.getSale_item_id());
        values.put(SALE_COLUMN_BOUGHT_ON, sale.getSale_bought_on().toString());
        values.put(SALE_COLUMN_PAID_ON, sale.getSale_payed_on().toString());
        values.put(SALE_COLUMN_PAYMENT_ID, sale.getSale_payment_id());
        values.put(SALE_COLUMN_QUANTITY, sale.getSale_quantity());
        values.put(SALE_COLUMN_STATUS, sale.getSale_status());
        values.put(SALE_COLUMN_USER_ID, sale.getSale_user_id());

        db.insert(SALE_TABLE_NAME,
                null,
                values);
        db.close();
    }

    public int update(Sale sale) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SALE_COLUMN_ID, sale.getSale_id());
        values.put(SALE_COLUMN_ITEM_ID, sale.getSale_item_id());
        values.put(SALE_COLUMN_BOUGHT_ON, sale.getSale_bought_on().toString());
        values.put(SALE_COLUMN_PAID_ON, sale.getSale_payed_on().toString());
        values.put(SALE_COLUMN_PAYMENT_ID, sale.getSale_payment_id());
        values.put(SALE_COLUMN_QUANTITY, sale.getSale_quantity());
        values.put(SALE_COLUMN_STATUS, sale.getSale_status());
        values.put(SALE_COLUMN_USER_ID, sale.getSale_user_id());

        int response = db.update(SALE_TABLE_NAME,
                values,
                SALE_COLUMN_ID + "= ?",
                new String[] {String.valueOf(sale.getSale_id())});
        db.close();
        return response;
    }

    public void delete(Sale sale) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        db.delete(SALE_TABLE_NAME,
                SALE_COLUMN_ID +" = ?",
                new String[] { String.valueOf(sale.getSale_id())});
        db.close();
    }

    public Sale get(int saleId) {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor =
                db.query(SALE_TABLE_NAME, // a. table
                        COLUMNS, // b. column names
                        SALE_COLUMN_ID + " = ?", // c. selections
                        new String[] { String.valueOf(saleId) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        return cursorToSale(cursor).get(0);
    }

    public List<Sale> get() {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String query = "SELECT * FROM " + SALE_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursorToSale(cursor);
    }

}
