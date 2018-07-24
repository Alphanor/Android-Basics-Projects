package com.example.jude.inventoryapp1.data;

import android.provider.BaseColumns;

public class InventoryContract {

    private InventoryContract() {

    }

    public static final class InventoryEntry implements BaseColumns {

        public static final String TABLE_NAME = "inventory";

        public static final String ID = BaseColumns._ID;

        public static final String PRODUCT_NAME = "name";

        public static final String PRODUCT_PRICE = "price";

        public static final String PRODUCT_QUANTITY = "quantity";

        public static final String SUPPLIER_NAME = "supplier";

        public static final String SUPPLIER_PHONE_NUMBER = "phone";

    }
}
