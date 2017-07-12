package com.example.samson.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SAMSON on 7/12/2017.
 */

public class PetDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PetsData.db";

    public PetDbHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String CREATE_SQL_TABLE = "CREATE TABLE " + PetContract.PetEntry.DB_TABLE + "(" + PetContract.PetEntry._ID
                +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PetContract.PetEntry.PET_NAME +" TEXT NOT NULL, " + PetContract.PetEntry.PET_BREED + " TEXT, "
                + PetContract.PetEntry.PET_GENDER + " INTEGER NOT NULL, " +
                 PetContract.PetEntry.PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0" + ")";
    db.execSQL(CREATE_SQL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//        db.execSQL(PetContract.PetEntry.INSERT_NEW_DATA);

    }
}
