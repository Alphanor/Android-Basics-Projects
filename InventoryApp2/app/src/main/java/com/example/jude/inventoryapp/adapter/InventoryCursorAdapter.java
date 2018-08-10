package com.example.jude.inventoryapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jude.inventoryapp.R;
import com.example.jude.inventoryapp.data.InventoryContract;

public class InventoryCursorAdapter extends android.support.v4.widget.CursorAdapter {

    public InventoryCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ImageView image = view.findViewById(R.id.product_image_view);

        TextView name = view.findViewById(R.id.product_name_text_view);

        TextView price = view.findViewById(R.id.product_price_text_view);

        TextView quantity = view.findViewById(R.id.product_quantity_text_view);

        int nameColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_ITEM_NAME);

        int priceColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_ITEM_PRICE);

        int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_ITEM_QUANTITY);

        name.setText(cursor.getString(nameColumnIndex));

        price.setText(cursor.getString(priceColumnIndex));

        quantity.setText(cursor.getString(quantityColumnIndex));

    }
}
