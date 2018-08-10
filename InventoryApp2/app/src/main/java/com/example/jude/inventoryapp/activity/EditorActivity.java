package com.example.jude.inventoryapp.activity;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jude.inventoryapp.R;
import com.example.jude.inventoryapp.data.InventoryContract;

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private Uri currentItemUri;

    private EditText name;

    private EditText price;

    private EditText quantity;

    private EditText seller;

    private EditText sellerPhone;

    private Spinner spinner;

    private String categorySelected;

    private boolean itemHasChanged = false;

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            itemHasChanged = true;
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_editor, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if(currentItemUri == null) {

            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_save:
                saveItem();
                finish();
                break;
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;

            case android.R.id.home:

                if(!itemHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }

                DialogInterface.OnClickListener discardButtonClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    }
                };
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Intent intent = getIntent();
        currentItemUri = intent.getData();

        if (currentItemUri == null) {
            setTitle("Add an Item");
        } else {
            setTitle("Edit an Item");

            getLoaderManager().initLoader(0, null, this);
        }

        name = findViewById(R.id.product_name_edit_text);

        price = findViewById(R.id.product_price_edit_text);

        quantity = findViewById(R.id.product_quantity_edit_text);

        seller = findViewById(R.id.supplier_name_edit_text);

        sellerPhone = findViewById(R.id.supplier_phone_number_edit_text);

        spinner = findViewById(R.id.spinner_category);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                categorySelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                categorySelected = "Other";
            }
        });

        name.setOnTouchListener(touchListener);

        price.setOnTouchListener(touchListener);

        quantity.setOnTouchListener(touchListener);

        seller.setOnTouchListener(touchListener);

        sellerPhone.setOnTouchListener(touchListener);

        spinner.setOnTouchListener(touchListener);
    }

    @Override
    public void onBackPressed() {

        if(!itemHasChanged) {
            super.onBackPressed();
            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        showUnsavedChangesDialog(discardButtonClickListener);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {
                InventoryContract.InventoryEntry._ID,
                InventoryContract.InventoryEntry.COLUMN_ITEM_NAME,
                InventoryContract.InventoryEntry.COLUMN_CATEGORY,
                InventoryContract.InventoryEntry.COLUMN_ITEM_PRICE,
                InventoryContract.InventoryEntry.COLUMN_ITEM_QUANTITY,
                InventoryContract.InventoryEntry.COLUMN_ITEM_SELLER,
                InventoryContract.InventoryEntry.COLUMN_SELLER_PHONE
        };

        return new CursorLoader(this, currentItemUri, projection, null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_ITEM_NAME);
            int categoryColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_CATEGORY);
            int priceColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_ITEM_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_ITEM_QUANTITY);
            int sellerColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_ITEM_SELLER);
            int sellerNumberColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_SELLER_PHONE);

            name.setText(cursor.getString(nameColumnIndex));
            spinner.setSelection(categoryColumnIndex);
            price.setText(cursor.getString(priceColumnIndex));
            quantity.setText(cursor.getString(quantityColumnIndex));
            seller.setText(cursor.getString(sellerColumnIndex));
            sellerPhone.setText(cursor.getString(sellerNumberColumnIndex));
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
// TODO - not needed
       /* name.setText("");
        price.setText("");
        quantity.setText("");
        seller.setText("");
        sellerPhone.setText("");*/
    }

    private void saveItem() {

        String nameEditText = name.getText().toString().trim();

        String priceEditText = price.getText().toString();

        String quantityEditText = quantity.getText().toString();

        String sellerEditText = seller.getText().toString();

        String sellerPhoneEditText = sellerPhone.getText().toString();

        ContentValues values = new ContentValues();

        values.put(InventoryContract.InventoryEntry.COLUMN_ITEM_NAME, nameEditText);
        values.put(InventoryContract.InventoryEntry.COLUMN_CATEGORY, categorySelected); // TODO change it as user choice
        values.put(InventoryContract.InventoryEntry.COLUMN_ITEM_PRICE, priceEditText);
        values.put(InventoryContract.InventoryEntry.COLUMN_ITEM_QUANTITY, quantityEditText);
        values.put(InventoryContract.InventoryEntry.COLUMN_ITEM_SELLER, sellerEditText);
        values.put(InventoryContract.InventoryEntry.COLUMN_SELLER_PHONE, sellerPhoneEditText);


        if (currentItemUri == null) {

            Uri newUri = getContentResolver().insert(InventoryContract.InventoryEntry.CONTENT_URI, values);

            if (newUri == null)
                Toast.makeText(this, "Error saving the new item", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Item saved successfully", Toast.LENGTH_LONG).show();
        } else {

            int rowsUpdated = getContentResolver().update(currentItemUri, values, null, null);

            if (rowsUpdated == 0)
                Toast.makeText(this, "Error updating the items", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Items updated successfully", Toast.LENGTH_LONG).show();
        }


    }

    private void deletePet() {

        if (currentItemUri != null) {

            int rowsDeleted = getContentResolver().delete(currentItemUri, null, null);

            if (rowsDeleted == 0)
                Toast.makeText(this, "Error with deleting item",
                        Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Item deleted successfully",
                        Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    private void showDeleteConfirmationDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Delete this item?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                deletePet();
            }
        });
        builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
