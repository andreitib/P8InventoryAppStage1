package com.example.android.p8inventoryapp1;
import android.util.Log;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.android.p8inventoryapp1.data.InventoryContract.InventoryEntry;
import com.example.android.p8inventoryapp1.data.InventoryDbHelper;

public class CatalogActivity extends AppCompatActivity {
    /**
     * Tag for log message
     */

    private InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);


        // Setup FAB to open EditorActivity
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);


            }
        });
        // To access database, instantiate subclass of SQLiteOpenHelper and pass context, current activity
        mDbHelper = new InventoryDbHelper(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }



    private void displayDatabaseInfo() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_ITEM_NAME,
                InventoryEntry.COLUMN_ITEM_PRICE,
                InventoryEntry.COLUMN_AMOUNT,
                InventoryEntry.COLUMN_SIZE,
                InventoryEntry.COLUMN_CLIENT_NAME
        };


        Cursor cursor = db.query(
                InventoryEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);


        TextView displayView = findViewById(R.id.text_view_inventory);

        try {

            displayView.setText("The inventory table contains " + cursor.getCount() + " items.\n\n");
            displayView.append(InventoryEntry._ID + " - " +
                    InventoryEntry.COLUMN_ITEM_NAME + " - " +
                    InventoryEntry.COLUMN_ITEM_PRICE + " - " +
                    InventoryEntry.COLUMN_AMOUNT + " - " +
                    InventoryEntry.COLUMN_SIZE + " - " +
                    InventoryEntry.COLUMN_CLIENT_NAME + "\n");


            int idColumnIndex = cursor.getColumnIndex(InventoryEntry._ID);
            int ItemnameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_NAME);
            int ItempriceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_PRICE);
            int AmountColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_AMOUNT);
            int SizeColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_SIZE);
            int ClientnameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_CLIENT_NAME);


            while(cursor.moveToNext()) {

                int currentID = cursor.getInt(idColumnIndex);
                String currentItemname = cursor.getString(ItemnameColumnIndex);
                int currentItemprice = cursor.getInt(ItempriceColumnIndex);
                int currentAmount = cursor.getInt(AmountColumnIndex);
                String currentSize = cursor.getString(SizeColumnIndex);
                String currentClientName = cursor.getString(ClientnameColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentItemname + " - " +
                        currentItemprice + " - " +
                        currentAmount + " - " +
                        currentSize + " - " +
                        currentClientName));


            }
        } finally {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
}


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
