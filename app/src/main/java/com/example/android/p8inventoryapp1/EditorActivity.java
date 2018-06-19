package com.example.android.p8inventoryapp1;


import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.p8inventoryapp1.data.InventoryContract.InventoryEntry;
import com.example.android.p8inventoryapp1.data.InventoryDbHelper;

public class EditorActivity extends AppCompatActivity {

    private EditText mItemnameEditText;

    private EditText mItempriceEditText;

    private EditText mAmountEditText;


    /** EditText field to enter the product size */
    private Spinner mSizeSpinner;
    /**
     * Size of the items. The possible valid values are in the InventoryContract.java file:
     * {@link InventoryEntry#SIZE_SMALL}, {@link InventoryEntry#SIZE_MEDIUM}, or
     * {@link InventoryEntry#SIZE_LARGE},{@link InventoryEntry#SIZE_XLARGE}.
     */
    private String mSize = InventoryEntry.SIZE_SMALL;


    private EditText mClientnameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mItemnameEditText = (EditText)findViewById(R.id.edit_item_name);
        mItempriceEditText =(EditText) findViewById(R.id.edit_price);
        mAmountEditText = (EditText)findViewById(R.id.edit_amount);

        mSizeSpinner =(Spinner) findViewById(R.id.spinner_size);

        mClientnameEditText =(EditText) findViewById(R.id.edit_client_name);

        setupSpinner();
    }
    /**
     * Setup the dropdown spinner that allows the user to select the size  of the clothes.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter sizeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_size_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        sizeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mSizeSpinner.setAdapter(sizeSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.size_medium))) {
                        mSize = InventoryEntry.SIZE_MEDIUM;
                    } else if (selection.equals(getString(R.string.size_large))) {
                        mSize = InventoryEntry.SIZE_LARGE;
                    }else if (selection.equals(getString(R.string.size_xlarge))) {
                        mSize = InventoryEntry.SIZE_XLARGE;
                    } else {
                        mSize = InventoryEntry.SIZE_SMALL;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSize = InventoryEntry.SIZE_SMALL;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    // Gets user input from editor and saves pet info into database
    private void insertItem() {

        String itemnameString = mItemnameEditText.getText().toString().trim();
        String priceString = mItempriceEditText.getText().toString().trim();
        int price = Integer.parseInt(priceString);
        String amountString = mAmountEditText.getText().toString().trim();
        int amount = Integer.parseInt(amountString);
        String clientString = mClientnameEditText.getText().toString().trim();


        InventoryDbHelper mdbHelper = new InventoryDbHelper(this);

        SQLiteDatabase db = mdbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_ITEM_NAME, itemnameString);
        values.put(InventoryEntry.COLUMN_ITEM_PRICE, price);
        values.put(InventoryEntry.COLUMN_AMOUNT, amount);
        values.put(InventoryEntry.COLUMN_SIZE, mSize);
        values.put(InventoryEntry.COLUMN_CLIENT_NAME, clientString);

        long newRowId = db.insert(InventoryEntry.TABLE_NAME, null, values);


        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving item", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "item saved with row id", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save item to database
                insertItem();
                //Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
