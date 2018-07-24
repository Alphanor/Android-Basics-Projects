package com.example.jude.inventoryapp1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.jude.inventoryapp1.data.InventoryContract;
import com.example.jude.inventoryapp1.data.InventoryDBHelper;

public class MainActivity extends AppCompatActivity {

    private InventoryDBHelper dbHelper;

    private TextView displayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);

        displayView = findViewById(R.id.text_view_inventory);

        displayView.setMovementMethod(new ScrollingMovementMethod());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);

            }
        });

        dbHelper = new InventoryDBHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayView.setText("");
        displayDatabaseInfo();
    }

    public void displayDatabaseInfo() {

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String[] projections = {
                InventoryContract.InventoryEntry.ID,
                InventoryContract.InventoryEntry.PRODUCT_NAME,
                InventoryContract.InventoryEntry.PRODUCT_PRICE,
                InventoryContract.InventoryEntry.PRODUCT_QUANTITY,
                InventoryContract.InventoryEntry.SUPPLIER_NAME,
                InventoryContract.InventoryEntry.SUPPLIER_PHONE_NUMBER
        };

        Cursor cursor = database.query(InventoryContract.InventoryEntry.TABLE_NAME, projections, null, null, null, null, null);

        try {
            TextView numberOfRows = findViewById(R.id.text_view_number_of_rows);

            numberOfRows.setText("\n" + "NUMBER OF ROWS IN THE DATABASE: " + cursor.getCount() +
                                "\n" + InventoryContract.InventoryEntry.ID.toUpperCase() + " - " +
                                InventoryContract.InventoryEntry.PRODUCT_NAME.toUpperCase() +
                                 " - " + InventoryContract.InventoryEntry.PRODUCT_PRICE.toUpperCase() +
                                " - " + InventoryContract.InventoryEntry.PRODUCT_QUANTITY.toUpperCase() +
                                " - " + InventoryContract.InventoryEntry.SUPPLIER_NAME.toUpperCase() +
                                " - " + InventoryContract.InventoryEntry.SUPPLIER_PHONE_NUMBER.toUpperCase());

            int idColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.ID);
            int nameColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.PRODUCT_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.SUPPLIER_NAME);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.SUPPLIER_PHONE_NUMBER);


            while(cursor.moveToNext()) {

                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex).toUpperCase();
                String currentPrice = cursor.getString(priceColumnIndex);
                String currentQuantity = cursor.getString(quantityColumnIndex);
                String currentSupplier = cursor.getString(supplierNameColumnIndex).toUpperCase();
                String currentSupplierNumber = cursor.getString(supplierPhoneNumberColumnIndex);

                if(currentSupplier.isEmpty())
                    currentSupplier = "UNKNOWN";

                if(currentSupplierNumber.isEmpty())
                    currentSupplierNumber = "NO NUMBER";

                displayView.append("\n\n" + currentID + " - " + currentName + " - " + currentPrice + " - " + currentQuantity + " - " + currentSupplier + " - " + currentSupplierNumber);

            }
    } finally {

        cursor.close();
    }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_delete_all_entries:

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("delete from "+ InventoryContract.InventoryEntry.TABLE_NAME);
                onStart();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
