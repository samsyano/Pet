package com.example.samson.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.samson.pets.data.PetContract;
import com.example.samson.pets.data.PetDbHelper;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.add_pet);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditorActivity.class);
                startActivity(intent);
            }
        });

        petDbHelper = new PetDbHelper(this);

        displayDatabaseInfo();
    }

    private void insertDummy(){
//        Intent intent = new Intent(getApplicationContext(), EditorActivity.class);
//        startActivity(intent);

        SQLiteDatabase db = petDbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(PetContract.PetEntry.PET_NAME, "Davies");
        contentValues.put(PetContract.PetEntry.PET_BREED, "Tammy");
        contentValues.put(PetContract.PetEntry.PET_GENDER, 2);
        contentValues.put(PetContract.PetEntry.PET_WEIGHT, 10);

        db.insert(PetContract.PetEntry.DB_TABLE, null, contentValues);
            displayDatabaseInfo();

    }
    PetDbHelper petDbHelper;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_insert_dummy_data:
                insertDummy();
                return true;
            case R.id.action_delete_all_entries:

        }

        return super.onOptionsItemSelected(item);
    }

TextView textView;
    private void displayDatabaseInfo(){
       petDbHelper  = new PetDbHelper(getApplicationContext());

        SQLiteDatabase sqlDb = petDbHelper.getReadableDatabase();

//        Cursor cursor = sqlDb.rawQuery("SELECT * FROM " + PetContract.PetEntry.DB_TABLE, null);
        String[] projection = {PetContract.PetEntry._ID, PetContract.PetEntry.PET_NAME,
                PetContract.PetEntry.PET_BREED, PetContract.PetEntry.PET_GENDER,
                PetContract.PetEntry.PET_WEIGHT};

        String selection = PetContract.PetEntry._ID + "=?";
        String[] selectionArgs = {PetContract.PetEntry.PET_BREED};
        Cursor cursor = sqlDb.query(PetContract.PetEntry.DB_TABLE,projection,
//                selection, selectionArgs,
                null, null,
                null, null, null);

        try{
            textView = (TextView) findViewById(R.id.text_view_pet);

            int id_count = cursor.getColumnIndex(PetContract.PetEntry._ID);
            int name_count = cursor.getColumnIndex(PetContract.PetEntry.PET_NAME);
            int breed_count = cursor.getColumnIndex(PetContract.PetEntry.PET_BREED);
            int gender_count = cursor.getColumnIndex(PetContract.PetEntry.PET_GENDER);
            int weight_count = cursor.getColumnIndex(PetContract.PetEntry.PET_WEIGHT);

            textView.setText( "The Pets Table countains: "+ cursor.getCount() +  "pets \n \n");
            textView.append(PetContract.PetEntry._ID + " | " + PetContract.PetEntry.PET_NAME + " | "
                    + PetContract.PetEntry.PET_BREED +" | " + PetContract.PetEntry.PET_GENDER
                    + " | " + PetContract.PetEntry.PET_WEIGHT + "\n");

            while (cursor.moveToNext()){

                int id = cursor.getInt(id_count);
                String name = cursor.getString(name_count);
                String breed = cursor.getString(breed_count);
                int gender = cursor.getInt(gender_count);
                int weight = cursor.getInt(weight_count);

                textView.append("\n" + id + " | " + name + " | " + breed +" | " + gender + " | " + weight);
            }
        }finally {
            cursor.close();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
}
