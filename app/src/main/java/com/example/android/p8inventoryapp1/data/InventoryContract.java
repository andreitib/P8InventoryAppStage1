package com.example.android.p8inventoryapp1.data;
import android.provider.BaseColumns;

public final class InventoryContract {

    private InventoryContract() {}

    public static final class InventoryEntry implements BaseColumns {

        public final static String TABLE_NAME = "Inventory";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_ITEM_NAME = "name";
        public final static String COLUMN_ITEM_PRICE = "price";
        public final static String COLUMN_AMOUNT = "items";
        public final static String COLUMN_CLIENT_NAME = "client";


        public final static String COLUMN_SIZE = "size";


        /**
         * Possible values for the size of the clothes.
         */
        public static final String SIZE_SMALL = "S";
        public static final String SIZE_MEDIUM = "M";
        public static final String SIZE_LARGE = "L";

    }
}
