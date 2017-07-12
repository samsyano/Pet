package com.example.samson.pets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.samson.pets.data.PetContract;

public class EditorActivity extends AppCompatActivity {

    EditText nameText;
    EditText breedText;
    EditText weightText;

    Spinner genderSpinner;

    private int mGender = 0;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        nameText = (EditText) findViewById(R.id.edit_pet_name);
        breedText = (EditText) findViewById(R.id.edit_pet_breed);
        weightText = (EditText) findViewById(R.id.edit_pet_weight);

        genderSpinner = (Spinner) findViewById(R.id.spinner_gender);

        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_gender_options, R.layout.support_simple_spinner_dropdown_item);
        genderSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderSpinnerAdapter);

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);

                if(!TextUtils.isEmpty(selection)){
                    if(selection.equals(getString(R.string.gender_male))){
                        mGender = PetContract.PetEntry.GENDER_MALE;
                    }else if(selection.equals(getString(R.string.gender_female))){
                        mGender = PetContract.PetEntry.GENDER_FEMALE;
                    }else{
                        mGender = PetContract.PetEntry.GENDER_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = 0;
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:

                return true;
            case R.id.action_delete:

                return true;
            case R.id.home:
                Intent intent = getParentActivityIntent();
                if(NavUtils.shouldUpRecreateTask(this, intent)){
                    TaskStackBuilder.create(getApplicationContext())
                            .addNextIntentWithParentStack(intent)
                            .startActivities();
                }else{
                    NavUtils.navigateUpTo(this, intent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
