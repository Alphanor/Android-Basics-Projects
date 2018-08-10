package com.example.jude.inventoryapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

public class InventoryProvider extends ContentProvider {

    public static final String LOG_TAG = InventoryProvider.class.getSimpleName();

    private InventoryDBHelper dbHelper;

    private static final int ITEMS = 1;

    private static final int ITEM_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        uriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY, ITEMS);

        uriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY, InventoryContract.PATH_INVENTORY + "/#", ITEM_ID);
    }

    @Override
    public boolean onCreate() {

        dbHelper = new InventoryDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor;

        int matchUri = uriMatcher.match(uri);

        switch (matchUri) {

            case ITEMS:
                cursor = sqLiteDatabase.query(InventoryContract.InventoryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case ITEM_ID:
                selection = InventoryContract.InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(InventoryContract.InventoryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Can't query unknown URI: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        int matchUri = uriMatcher.match(uri);

        switch (matchUri) {

            case ITEMS:
                return insertItem(uri, values);

            default:
                throw new IllegalArgumentException("Insertion in not supported for " + uri);
        }
    }

    private Uri insertItem(Uri uri, ContentValues values) {

        String name = values.getAsString(InventoryContract.InventoryEntry.COLUMN_ITEM_NAME);

        if (name == null) {
            throw new IllegalArgumentException("Item requires a name");
        }

        double price = values.getAsDouble(InventoryContract.InventoryEntry.COLUMN_ITEM_PRICE);
        if (String.valueOf(price).isEmpty() || price < 0) {
            throw new IllegalArgumentException("Item requires a valid price");
        }

        Integer quantity = values.getAsInteger(InventoryContract.InventoryEntry.COLUMN_ITEM_QUANTITY);
        if (quantity == null && quantity < 0) {
            throw new IllegalArgumentException("Item requires a valid quantity");
        }

        String seller = values.getAsString(InventoryContract.InventoryEntry.COLUMN_ITEM_SELLER);
        if (seller.isEmpty()) {
            throw new IllegalArgumentException("Item requires a valid seller");
        }

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        long id = database.insert(InventoryContract.InventoryEntry.TABLE_NAME, null, values);

        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        int rowsDeleted;

        final int match = uriMatcher.match(uri);

        switch (match) {

            case ITEMS:
                rowsDeleted = database.delete(InventoryContract.InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case ITEM_ID:
                selection = InventoryContract.InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(InventoryContract.InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        int matchUri = uriMatcher.match(uri);

        switch (matchUri) {

            case ITEMS:
                return updateItem(uri, values, selection, selectionArgs);

            case ITEM_ID:
                selection = InventoryContract.InventoryEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return updateItem(uri, values, selection, selectionArgs);

            default:
                throw new IllegalArgumentException("Update is not supported for: " + uri);
        }

    }

    private int updateItem(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.containsKey(InventoryContract.InventoryEntry.COLUMN_ITEM_NAME)) {
            String name = values.getAsString(InventoryContract.InventoryEntry.COLUMN_ITEM_NAME);
            if (TextUtils.isEmpty(name))
                throw new IllegalArgumentException("Item requires a valid name");
        }

        if (values.containsKey(InventoryContract.InventoryEntry.COLUMN_ITEM_SELLER)) {
            String seller = values.getAsString(InventoryContract.InventoryEntry.COLUMN_ITEM_SELLER);
            if (TextUtils.isEmpty(seller))
                throw new IllegalArgumentException("Item requires a valid seller");
        }

        if (values.containsKey(InventoryContract.InventoryEntry.COLUMN_ITEM_PRICE)) {
            double price = values.getAsDouble(InventoryContract.InventoryEntry.COLUMN_ITEM_PRICE);
            if (price < 0 || String.valueOf(price).isEmpty())
                throw new IllegalArgumentException("Item requires a valid price");
        }

        if (values.containsKey(InventoryContract.InventoryEntry.COLUMN_ITEM_QUANTITY)) {
            Integer quantity = values.getAsInteger(InventoryContract.InventoryEntry.COLUMN_ITEM_QUANTITY);
            if (quantity < 0 || String.valueOf(quantity).isEmpty())
                throw new IllegalArgumentException("Item requires a valid quantity");
        }

        if (values.size() == 0) {
            return 0;
        }
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        int rowsUpdated = sqLiteDatabase.update(InventoryContract.InventoryEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;

    }
}
