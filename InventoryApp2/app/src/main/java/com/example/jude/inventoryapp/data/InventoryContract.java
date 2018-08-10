package com.example.jude.inventoryapp.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class InventoryContract {

    private InventoryContract() {

    }

    public static final String CONTENT_AUTHORITY = "com.example.jude.inventoryapp2";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_INVENTORY = "inventory";


    public static final class InventoryEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORY);

        public static final String TABLE_NAME = "inventory";

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_ITEM_NAME = "name";

        public static final String COLUMN_ITEM_PRICE = "price";

        public static final String COLUMN_ITEM_QUANTITY = "quantity";

        public static final String COLUMN_ITEM_SELLER = "seller";

        public static final String COLUMN_SELLER_PHONE = "phone";

        public static final String COLUMN_CATEGORY = "category"; // TODO - added


    }

}
