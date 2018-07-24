package com.example.jude.inventoryapp1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jude.inventoryapp1.data.InventoryContract;
import com.example.jude.inventoryapp1.data.InventoryDBHelper;

public class EditorActivity extends AppCompatActivity {

    private EditText productNameEditText;

    private EditText productPriceEditText;

    private EditText productQuantityEditText;

    private EditText supplierNameEditText;

    private EditText supplierPhoneNumberEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        productNameEditText = findViewById(R.id.product_name);

        productPriceEditText = findViewById(R.id.product_price);

        productQuantityEditText = findViewById(R.id.product_quantity);

        supplierNameEditText = findViewById(R.id.supplier_name);

        supplierPhoneNumberEditText = findViewById(R.id.supplier_phone_number);

    }

    private void insertItem() {

        if(productNameEditText.getText().toString().isEmpty() || productPriceEditText.getText().toString().isEmpty() || productQuantityEditText.getText().toString().isEmpty())

            Toast.makeText(this, "The first three fields are mandatory!", Toast.LENGTH_LONG).show();

        else {
            String productName = productNameEditText.getText().toString().trim().toUpperCase();
            double productPrice = Double.parseDouble(productPriceEditText.getText().toString());
            int productQuantity = Integer.parseInt(productQuantityEditText.getText().toString());
            String supplierName = supplierNameEditText.getText().toString().toUpperCase();
            String supplierPhoneNumber = supplierPhoneNumberEditText.getText().toString();

            InventoryDBHelper dbHelper = new InventoryDBHelper(this);

            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(InventoryContract.InventoryEntry.PRODUCT_NAME, productName);
            values.put(InventoryContract.InventoryEntry.PRODUCT_PRICE, productPrice);
            values.put(InventoryContract.InventoryEntry.PRODUCT_QUANTITY, productQuantity);
            values.put(InventoryContract.InventoryEntry.SUPPLIER_NAME, supplierName);
            values.put(InventoryContract.InventoryEntry.SUPPLIER_PHONE_NUMBER, supplierPhoneNumber);

            db.insert(InventoryContract.InventoryEntry.TABLE_NAME, null, values);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_save:

                insertItem();
                finish();
                return true;

            case R.id.action_delete:

                finish();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.editor_menu, menu);
        return true;
    }
}
