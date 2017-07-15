package com.example.samson.pets.data;

import android.provider.BaseColumns;

/**
 * Created by SAMSON on 7/12/2017.
 */

public final class PetContract {




   public static  final class PetEntry implements BaseColumns{

        public static final String DB_TABLE = "PETS_TABLE";

        public static final String _ID = BaseColumns._ID;
        public static final String PET_NAME = "NAME";
        public static final String PET_BREED = "BREED";
        public static final String PET_WEIGHT = "WEIGHT";
        public static final String PET_GENDER = "GENDER";
        /*
        * constants for the pet"s gender*/
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
       public static final int GENDER_UNKNOWN = 0;

       /*
       * */

       public static final String CONTENT_AUTHORITY = "com.example.android.pets";
       public static final String PATH_PET = "pets";




       public static final String DELETE_DATA = "DELETE FROM" + DB_TABLE + "WHERE" + _ID + "="  ;

       public static final String INSERT_NEW_DATA = "INSERT INTO" + DB_TABLE + "("
               + PET_NAME +"," + PET_BREED + ","
               + PET_GENDER + "," +
               PET_WEIGHT +  ") VALUES (" + PET_NAME +"," + PET_BREED + ","
               + PET_GENDER + "," +
               PET_WEIGHT +  ")";

    }
}
