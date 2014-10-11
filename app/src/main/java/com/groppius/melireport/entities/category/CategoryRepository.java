package com.groppius.melireport.entities.category;

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
public class CategoryRepository implements MeliReportDbGlobalsInterface {
    private DataBaseHelper dataBaseHelper;
    private Context context;
    private final String[] COLUMNS = {CATEGORY_COLUMN_ID, CATEGORY_COLUMN_NAME, CATEGORY_COLUMN_DESCRIPTION,
            CATEGORY_COLUMN_FATHER };

    public CategoryRepository(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context);
    }

    public ArrayList<Category> cursorToBuyer(Cursor cursor) {
        ArrayList<Category> categories = new ArrayList<Category>();
        if(cursor != null) {
            cursor.moveToFirst();
            do {
                Category category = new Category();
                category.setCategory_id(cursor.getLong(cursor.getColumnIndex(CATEGORY_COLUMN_ID)));
                category.setCategory_name(cursor.getString(cursor.getColumnIndex(CATEGORY_COLUMN_NAME)));
                category.setCategory_description(cursor.getString(cursor.getColumnIndex(CATEGORY_COLUMN_DESCRIPTION)));
                category.setCategory_father(cursor.getLong(cursor.getColumnIndex(CATEGORY_COLUMN_FATHER)));
                categories.add(category);
            } while (cursor.moveToNext());
        }
        return categories;
    }

    public void insert(Category category) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CATEGORY_COLUMN_ID, category.getCategory_id());
        values.put(CATEGORY_COLUMN_NAME, category.getCategory_name());
        values.put(CATEGORY_COLUMN_DESCRIPTION, category.getCategory_description());
        values.put(CATEGORY_COLUMN_FATHER, category.getCategory_father());

        db.insert(CATEGORY_TABLE_NAME,
                null,
                values);
        db.close();
    }

    public int update(Category category) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CATEGORY_COLUMN_ID, category.getCategory_id());
        values.put(CATEGORY_COLUMN_NAME, category.getCategory_name());
        values.put(CATEGORY_COLUMN_DESCRIPTION, category.getCategory_description());
        values.put(CATEGORY_COLUMN_FATHER, category.getCategory_father());

        int response = db.update(CATEGORY_TABLE_NAME,
                values,
                CATEGORY_COLUMN_ID + "= ?",
                new String[] {String.valueOf(category.getCategory_id())});
        db.close();
        return response;
    }

    public void delete(Category category) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        db.delete(CATEGORY_TABLE_NAME,
                CATEGORY_COLUMN_ID +" = ?",
                new String[] { String.valueOf(category.getCategory_id())});
        db.close();
    }

    public Category get(int categoryId) {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor =
                db.query(CATEGORY_TABLE_NAME, // a. table
                        COLUMNS, // b. column names
                        CATEGORY_COLUMN_ID + " = ?", // c. selections
                        new String[] { String.valueOf(categoryId) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        return cursorToBuyer(cursor).get(0);
    }

    public List<Category> get() {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String query = "SELECT * FROM " + CATEGORY_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursorToBuyer(cursor);
    }

}
