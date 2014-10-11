package com.groppius.melireport.entities.item;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.groppius.melireport.database.DataBaseHelper;
import com.groppius.melireport.database.MeliReportDbGlobalsInterface;
import com.groppius.melireport.misc.Helper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by julio on 11/10/14.
 */
public class ItemRepository implements MeliReportDbGlobalsInterface {
    private DataBaseHelper dataBaseHelper;
    private Context context;
    private final String[] COLUMNS = {ITEM_COLUMN_ID, ITEM_COLUMN_NAME, ITEM_COLUMN_DESCRIPTION,
            ITEM_COLUMN_PRICE, ITEM_COLUMN_PUBLISHED_ON, ITEM_COLUMN_PUBLISHED_ENDED,
            ITEM_COLUMN_THUMBNAIL, ITEM_COLUMN_CATEGORY_ID_FK, ITEM_COLUMN_COST};

    public ItemRepository(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context);
    }

    public ArrayList<Item> cursorToItem(Cursor cursor) {
        ArrayList<Item> items = new ArrayList<Item>();
        if(cursor != null) {
            cursor.moveToFirst();
            do {
                Item item = new Item();
                item.setItem_id(cursor.getLong(cursor.getColumnIndex(ITEM_COLUMN_ID)));
                item.setItem_name(cursor.getString(cursor.getColumnIndex(ITEM_COLUMN_NAME)));
                item.setItem_description(cursor.getString(cursor.getColumnIndex(ITEM_COLUMN_DESCRIPTION)));
                item.setItem_price(cursor.getDouble(cursor.getColumnIndex(ITEM_COLUMN_PRICE)));
                item.setItem_published_on(Helper.dateFormatter(cursor.getString(cursor.getColumnIndex(ITEM_COLUMN_PUBLISHED_ON))));
                item.setGetItem_published_ended_on(Helper.dateFormatter(cursor.getString(cursor.getColumnIndex(ITEM_COLUMN_PUBLISHED_ENDED))));
                item.setItem_thumbnail(cursor.getString(cursor.getColumnIndex(ITEM_COLUMN_THUMBNAIL)));
                item.setItem_category_id(cursor.getLong(cursor.getColumnIndex(ITEM_COLUMN_CATEGORY_ID_FK)));
                item.setItem_cost(cursor.getDouble(cursor.getColumnIndex(ITEM_COLUMN_COST)));
                items.add(item);
            } while (cursor.moveToNext());
        }
        return items;
    }

    public void insert(Item item) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_COLUMN_ID, item.getItem_id());
        values.put(ITEM_COLUMN_NAME, item.getItem_name());
        values.put(ITEM_COLUMN_DESCRIPTION, item.getItem_description());
        values.put(ITEM_COLUMN_PRICE, item.getItem_price());
        values.put(ITEM_COLUMN_PUBLISHED_ON, item.getItem_published_on().toString());
        values.put(ITEM_COLUMN_PUBLISHED_ENDED, item.getGetItem_published_ended_on().toString());
        values.put(ITEM_COLUMN_THUMBNAIL, item.getItem_thumbnail());
        values.put(ITEM_COLUMN_CATEGORY_ID_FK, item.getItem_category_id());
        values.put(ITEM_COLUMN_COST, item.getItem_cost());

        db.insert(ITEM_TABLE_NAME,
                null,
                values);
        db.close();
    }

    public int update(Item item) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ITEM_COLUMN_ID, item.getItem_id());
        values.put(ITEM_COLUMN_NAME, item.getItem_name());
        values.put(ITEM_COLUMN_DESCRIPTION, item.getItem_description());
        values.put(ITEM_COLUMN_PRICE, item.getItem_price());
        values.put(ITEM_COLUMN_PUBLISHED_ON, item.getItem_published_on().toString());
        values.put(ITEM_COLUMN_PUBLISHED_ENDED, item.getGetItem_published_ended_on().toString());
        values.put(ITEM_COLUMN_THUMBNAIL, item.getItem_thumbnail().toString());
        values.put(ITEM_COLUMN_CATEGORY_ID_FK, item.getItem_category_id());
        values.put(ITEM_COLUMN_COST, item.getItem_cost());

        int response = db.update(ITEM_TABLE_NAME,
                values,
                ITEM_COLUMN_ID + "= ?",
                new String[] {String.valueOf(item.getItem_id())});
        db.close();
        return response;
    }

    public void delete(Item item) {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        db.delete(ITEM_TABLE_NAME,
                ITEM_COLUMN_ID +" = ?",
                new String[] { String.valueOf(item.getItem_id())});
        db.close();
    }

    public Item get(int itemId) {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor =
                db.query(ITEM_TABLE_NAME, // a. table
                        COLUMNS, // b. column names
                        ITEM_COLUMN_ID + " = ?", // c. selections
                        new String[] { String.valueOf(itemId) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit
        return cursorToItem(cursor).get(0);
    }

    public List<Item> get() {
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String query = "SELECT * FROM " + ITEM_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursorToItem(cursor);
    }
}
