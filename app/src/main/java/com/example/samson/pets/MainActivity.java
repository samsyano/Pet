package com.example.samson.pets;

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

        displayDatabaseInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_insert_dummy_data:
                Intent intent = new Intent(getApplicationContext(), EditorActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_delete_all_entries:

        }

        return super.onOptionsItemSelected(item);
    }

TextView textView;
    private void displayDatabaseInfo(){
        PetDbHelper petDbHelper = new PetDbHelper(getApplicationContext());

        SQLiteDatabase sqlDb = petDbHelper.getReadableDatabase();

        Cursor cursor = sqlDb.rawQuery("SELECT * FROM " + PetContract.PetEntry.DB_TABLE, null);

        try{
            textView = (TextView) findViewById(R.id.text_view_pet);

            textView.setText("Number os rows in pet db table: "+ cursor.getCount());
        }finally {
            cursor.close();
        }
    }
}
