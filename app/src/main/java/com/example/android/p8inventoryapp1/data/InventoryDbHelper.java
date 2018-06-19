package com.example.android.p8inventoryapp1.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.p8inventoryapp1.CatalogActivity;
import com.example.android.p8inventoryapp1.data.InventoryContract.InventoryEntry;


public class InventoryDbHelper extends SQLiteOpenHelper {
    private static final String LOD_TAG = CatalogActivity.class.getSimpleName();

    private static final String DATABASE_NAME = "inventory.db";

    private static final int DATABASE_VERSION = 1;


    public InventoryDbHelper(Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE " + InventoryEntry.TABLE_NAME + "("
                + InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryEntry.COLUMN_ITEM_NAME + " TEXT NOT NULL, "
                + InventoryEntry.COLUMN_ITEM_PRICE + " INTEGER NOT NULL,, "
                + InventoryEntry.COLUMN_AMOUNT + " INTEGER NOT NULL,, "
                + InventoryEntry.COLUMN_SIZE + " TEXT, "
                + InventoryEntry.COLUMN_CLIENT_NAME + " TEXT );";

            Log.e(LOD_TAG, "There is a problem making the HTTP request.");
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
