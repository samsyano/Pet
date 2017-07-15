package com.example.samson.pets.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by SAMSON on 7/14/2017.
 */

public class PetProvider extends ContentProvider {

    final static String LOGCAT = PetProvider.class.getSimpleName();

    private static final int PETS = 100;
    private static final int PETS_ID = 101;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(PetContract.PetEntry.CONTENT_AUTHORITY, PetContract.PetEntry.PATH_PET, PETS);

        uriMatcher.addURI(PetContract.PetEntry.CONTENT_AUTHORITY, PetContract.PetEntry.PATH_PET + "=/#", PETS_ID);
    }
    PetDbHelper dbHelper;
    @Override
    public boolean onCreate() {

        dbHelper = new PetDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor;
        int match = uriMatcher.match(uri);

        switch (match){
            case PETS:

                cursor = db.query(PetContract.PetEntry.DB_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PETS_ID:
                selection = PetContract.PetEntry.PATH_PET + "=/#";

                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                cursor = db.query(PetContract.PetEntry.DB_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Can not query this file: " + uri);
        }
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
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
