package com.offcasoftware.shop2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.offcasoftware.shop2.model.Product;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by RENT on 2017-03-06.
 */

public class DatabaseImp extends SQLiteOpenHelper implements Database {

    private final static String NAME = "database.db";
    private final static int VERSION = 3; //14 - zmiania wartosc 1 na 2
    private static final String DB_CREATE_TODO_TABLE = //8
            "CREATE TABLE products(" +
                    "id INTEGER PRIMARY KEY," +
                    "name TEXT NOT NULL, " +
                    "price INTEGER DEFAULT 0," +
                    "home TEXT" +
                    ");";
    private static final String ADD_COLUMN = "ALERT TABLE products" + "ADD test TEXT"; //12
    private static final java.lang.String DROP_TODO_TABLE = "DROP TABLE IF EXISTS products";
    //4

    public DatabaseImp(Context context) { // null to kursor  , zaimplementuj pierwszy koncturktor i sobie go zmodyfikowac
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //9
        db.execSQL(DB_CREATE_TODO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//13
        db.execSQL(DROP_TODO_TABLE); // implementuje dodatkowa kolumne a potem ja usuwam - teest
//        db.execSQL(ADD_COLUMN);
        onCreate(db);

    }

    @Override
    public void saveProducts(List<Product> products) { //nadpisuje metode bo doszla w database a tutaj implementujemy database

        SQLiteDatabase db = getWritableDatabase();
        for (Product product : products) {


            ContentValues contentValues = new ContentValues();
            contentValues.put("id", product.getId());
            contentValues.put("name", product.getName());
            contentValues.put("price", product.getPrice());
            contentValues.put("home", product.getImageResId());
            long id = db.insert("products", null, contentValues);
            Log.i("database", "id insert: " + id);
        }
    }

    @Override // nadpisujemy 20 potym jak zminiles w repository - sciagam  cala kolumne products
    public List<Product> getProducts() {

       List<Product> products = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("products",null,null,null,null,null,null);
         cursor.moveToFirst();
        do {
            int id = cursor.getInt(0);
            int nameColumnIndex = cursor.getColumnIndex("name");
            String name = cursor.getString(nameColumnIndex);
             String price = cursor.getString(2);
            String imageName = cursor.getString(3);

            Product product = new Product(id,name,price,imageName);
            products.add(product);

        }while (cursor.moveToNext());
        cursor.close();

        return products;
    }
}


//        long time = Calendar.getInstance().getTimeInMillis();
//
//    try // na koncu transakcje bo szybciej niz zwykla peta
//
//    {
//        db.beginTransaction();
//        for (int i = 1000; i < 2000; i++) {
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("id",i );
//            contentValues.put("name", "Dom I");
//            contentValues.put("price", "10000");
//
//        }
//        db.setTransactionSuccessful();
//    } finally {
//
//
//        db.endTransaction();
//    }
//    Log.i("database", "time: " + (Calendar.getInstance().getTimeInMillis() - time));



