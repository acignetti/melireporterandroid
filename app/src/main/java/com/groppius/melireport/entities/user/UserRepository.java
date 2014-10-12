package com.groppius.melireport.entities.user;

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
public class UserRepository implements MeliReportDbGlobalsInterface {
    private DataBaseHelper dataBaseHelper;
    private Context context;
    private final String[] COLUMNS = {USER_COLUMN_ID, USER_COLUMN_NAME, USER_COLUMN_LAST_NAME,
            USER_COLUMN_EMAIL, USER_COLUMN_PASSWORD, USER_COLUMN_REAL_NAME,
            USER_COLUMN_THUMBNAIL, USER_COLUMN_TOKEN};

    public UserRepository(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context);
    }

    public ArrayList<User> cursorToUser(Cursor cursor) {
        ArrayList<User> users = new ArrayList<User>();
        if(cursor != null) {
            cursor.moveToFirst();
            do {
                User user = new User();
                user.setUser_id(cursor.getLong(cursor.getColumnIndex(USER_COLUMN_ID)));
                user.setUser_name(cursor.getString(cursor.getColumnIndex(USER_COLUMN_NAME)));
                user.setUser_last_name(cursor.getString(cursor.getColumnIndex(USER_COLUMN_LAST_NAME)));
                user.setUser_email(cursor.getString(cursor.getColumnIndex(USER_COLUMN_EMAIL)));
                user.setUser_email(cursor.getString(cursor.getColumnIndex(USER_COLUMN_PASSWORD)));
                user.setUser_email(cursor.getString(cursor.getColumnIndex(USER_COLUMN_REAL_NAME)));
                user.setUser_email(cursor.getString(cursor.getColumnIndex(USER_COLUMN_THUMBNAIL)));
                user.setUser_email(cursor.getString(cursor.getColumnIndex(USER_COLUMN_TOKEN)));

                users.add(user);
            } while (cursor.moveToNext());
        }
        return users;
    }

    public void insert(User user) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_NAME, user.getUser_name());
        values.put(USER_COLUMN_LAST_NAME, user.getUser_last_name());
        values.put(USER_COLUMN_EMAIL, user.getUser_email());
        values.put(USER_COLUMN_PASSWORD, user.getUser_password());
        values.put(USER_COLUMN_REAL_NAME, user.getUser_real_name());
        values.put(USER_COLUMN_THUMBNAIL, user.getUser_thumbnail());
        values.put(USER_COLUMN_TOKEN, user.getUser_email());

        db.insert(USER_TABLE_NAME,
                null,
                values);
        db.close();
    }

    public int update(User user) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_NAME, user.getUser_name());
        values.put(USER_COLUMN_LAST_NAME, user.getUser_last_name());
        values.put(USER_COLUMN_EMAIL, user.getUser_email());
        values.put(USER_COLUMN_PASSWORD, user.getUser_password());
        values.put(USER_COLUMN_REAL_NAME, user.getUser_real_name());
        values.put(USER_COLUMN_THUMBNAIL, user.getUser_thumbnail());
        values.put(USER_COLUMN_TOKEN, user.getUser_email());

        int response = db.update(USER_TABLE_NAME,
                values,
                USER_COLUMN_ID + "= ?",
                new String[] {String.valueOf(user.getUser_id())});
        db.close();
        return response;
    }

    public void delete(User user) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        db.delete(USER_TABLE_NAME,
                USER_COLUMN_ID +" = ?",
                new String[] { String.valueOf(user.getUser_id())});
        db.close();
    }

    public User get(int userId) {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor =
                db.query(USER_TABLE_NAME, // a. table
                        COLUMNS, // b. column names
                        USER_COLUMN_ID + " = ?", // c. selections
                        new String[] { String.valueOf(userId) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        return cursorToUser(cursor).get(0);
    }

    public User get(String userName) {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor =
                db.query(USER_TABLE_NAME, // a. table
                        COLUMNS, // b. column names
                        USER_COLUMN_NAME + " = ?", // c. selections
                        new String[] { userName }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        return cursorToUser(cursor).get(0);
    }

    public List<User> get() {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String query = "SELECT * FROM " + USER_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursorToUser(cursor);
    }


}
