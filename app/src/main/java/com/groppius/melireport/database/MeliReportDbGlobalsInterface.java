package com.groppius.melireport.database;

/**
 * Created by julio on 11/10/14.
 */
public interface MeliReportDbGlobalsInterface {
    /**
     * BUYER TABLE
     */
    public static final String BUYER_TABLE_NAME = "buyer";
    public static final String BUYER_COLUMN_ID = "_id";
    public static final String BUYER_COLUMN_NAME = "buyer_name";
    public static final String BUYER_COLUMN_LAST_NAME = "buyer_last_name";
    public static final String BUYER_COLUMN_EMAIL = "buyer_email";
    public static final String BUYER_COLUMN_ADDRESS = "buyer_address";
    public static final String BUYER_COLUMN_REAL_NAME = "buyer_real_name";
    /**
     * ITEMS TABLE
     */
    public static final String ITEM_TABLE_NAME = "item";
    public static final String ITEM_COLUMN_ID = "_id";
    public static final String ITEM_COLUMN_NAME = "item_name";
    public static final String ITEM_COLUMN_DESCRIPTION = "item_description";
    public static final String ITEM_COLUMN_PRICE = "item_price";
    public static final String ITEM_COLUMN_COST = "item_cost";
    public static final String ITEM_COLUMN_PUBLISHED_ON = "item_published_on";
    public static final String ITEM_COLUMN_PUBLISHED_ENDED = "item_published_ended_on";
    public static final String ITEM_COLUMN_CATEGORY_ID_FK = "item_category_id_fk";
    public static final String ITEM_COLUMN_THUMBNAIL = "item_thumbnail";
    /**
     * CATEGORIES TABLE
     */
    public static final String CATEGORY_TABLE_NAME = "category";
    public static final String CATEGORY_COLUMN_ID = "_id";
    public static final String CATEGORY_COLUMN_NAME = "category_name";
    public static final String CATEGORY_COLUMN_DESCRIPTION = "category_description";
    public static final String CATEGORY_COLUMN_FATHER = "category_father";
    /**
     * USERS TABLE
     */
    public static final String USER_TABLE_NAME = "user";
    public static final String USER_COLUMN_ID = "_id";
    public static final String USER_COLUMN_NAME = "user_name";
    public static final String USER_COLUMN_REAL_NAME = "user_real_name";
    public static final String USER_COLUMN_LAST_NAME = "user_last_name";
    public static final String USER_COLUMN_PASSWORD = "user_password";
    public static final String USER_COLUMN_TOKEN = "user_token";
    public static final String USER_COLUMN_EMAIL = "user_email";
    public static final String USER_COLUMN_THUMBNAIL = "user_thumbnail";
    /**
     * PAYMENT TABLE
     */
    public static final String PAYMENT_TABLE_NAME = "payment";
    public static final String PAYMENT_COLUMN_ID = "_id";
    public static final String PAYMENT_COLUMN_NAME = "payment_name";
    public static final String PAYMENT_COLUMN_DESCRIPTION = "payment_description";
    /**
     * SALE TABLE
     */
    public static final String SALE_TABLE_NAME = "sale";
    public static final String SALE_COLUMN_ID = "_id";
    public static final String SALE_COLUMN_USER_ID = "sale_user_id";
    public static final String SALE_COLUMN_ITEM_ID = "sale_item_id";
    public static final String SALE_COLUMN_PAYMENT_ID = "sale_payment_id";
    public static final String SALE_COLUMN_QUANTITY = "sale_quantity";
    public static final String SALE_COLUMN_BOUGHT_ON = "sale_bought_on";
    public static final String SALE_COLUMN_PAYED_ON = "sale_payed_on";
    public static final String SALE_COLUMN_STATUS = "sale_status";
}
