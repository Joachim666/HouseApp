package com.offcasoftware.shop2;

import android.app.Application;

import com.offcasoftware.shop2.database.Database;
import com.offcasoftware.shop2.database.DatabaseImp;

/**
 * Created by RENT on 2017-03-06.
 */

public class AndroidApp extends Application { // start z baza danych dodaj klase do manifesttu 1

    private static Database mDatabase; //5


    @Override
    public void onCreate() {
        super.onCreate();
        mDatabase = new DatabaseImp(this); //7
        ((DatabaseImp) mDatabase).getWritableDatabase(); // ?? 10


    }

    public static Database getDatabase() {

            return mDatabase; //6


    }
}

