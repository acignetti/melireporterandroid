package com.groppius.melireport.entities.payment;

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
public class PaymentRepository  implements MeliReportDbGlobalsInterface {

    private DataBaseHelper dataBaseHelper;
    private Context context;
    private final String[] COLUMNS = {PAYMENT_COLUMN_ID, PAYMENT_COLUMN_NAME, PAYMENT_COLUMN_DESCRIPTION};

    public PaymentRepository(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context);
    }

    public ArrayList<Payment> cursorToPayment(Cursor cursor) {
        ArrayList<Payment> payments = new ArrayList<Payment>();
        if(cursor != null) {
            cursor.moveToFirst();
            do {
                Payment payment = new Payment();
                payment.setPayment_id(cursor.getLong(cursor.getColumnIndex(PAYMENT_COLUMN_ID)));
                payment.setPayment_name(cursor.getString(cursor.getColumnIndex(PAYMENT_COLUMN_NAME)));
                payment.setPayment_description(cursor.getString(cursor.getColumnIndex(PAYMENT_COLUMN_DESCRIPTION)));
                payments.add(payment);
            } while (cursor.moveToNext());
        }
        return payments;
    }

    public void insert(Payment payment) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PAYMENT_COLUMN_ID, payment.getPayment_id());
        values.put(PAYMENT_COLUMN_NAME, payment.getPayment_name());
        values.put(PAYMENT_COLUMN_DESCRIPTION, payment.getPayment_description());

        db.insert(PAYMENT_TABLE_NAME,
                null,
                values);
        db.close();
    }

    public int update(Payment payment) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PAYMENT_COLUMN_ID, payment.getPayment_id());
        values.put(PAYMENT_COLUMN_NAME, payment.getPayment_name());
        values.put(PAYMENT_COLUMN_DESCRIPTION, payment.getPayment_description());

        int response = db.update(PAYMENT_TABLE_NAME,
                values,
                PAYMENT_COLUMN_ID + "= ?",
                new String[] {String.valueOf(payment.getPayment_id())});
        db.close();
        return response;
    }

    public void delete(Payment payment) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        db.delete(PAYMENT_TABLE_NAME,
                PAYMENT_COLUMN_ID +" = ?",
                new String[] { String.valueOf(payment.getPayment_id())});
        db.close();
    }

    public Payment get(int paymentId) {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor =
                db.query(PAYMENT_TABLE_NAME, // a. table
                        COLUMNS, // b. column names
                        PAYMENT_COLUMN_ID + " = ?", // c. selections
                        new String[] { String.valueOf(paymentId) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        return cursorToPayment(cursor).get(0);
    }

    public List<Payment> get() {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String query = "SELECT * FROM " + PAYMENT_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursorToPayment(cursor);
    }

}
