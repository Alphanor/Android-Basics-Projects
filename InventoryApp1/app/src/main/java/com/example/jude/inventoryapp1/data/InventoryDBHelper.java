package com.example.jude.inventoryapp1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InventoryDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "inventory.db";

    public static final int DATABASE_VERSION = 1;

    public InventoryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE " + InventoryContract.InventoryEntry.TABLE_NAME + "("+
                InventoryContract.InventoryEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                InventoryContract.InventoryEntry.PRODUCT_NAME + " TEXT NOT NULL, "+
                InventoryContract.InventoryEntry.PRODUCT_PRICE + " REAL NOT NULL, "+
                InventoryContract.InventoryEntry.PRODUCT_QUANTITY + " INTEGER NOT NULL, "+
                InventoryContract.InventoryEntry.SUPPLIER_NAME + " TEXT NOT NULL DEFAULT 'UNKNOWN', "+
                InventoryContract.InventoryEntry.SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL DEFAULT 'NO NUMBER');";

        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
