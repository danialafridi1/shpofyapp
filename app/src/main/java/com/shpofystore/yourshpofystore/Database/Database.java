package com.shpofystore.yourshpofystore.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.shpofystore.yourshpofystore.Model.Users;

public class Database extends SQLiteOpenHelper
{
    public static final String DBNAME="SHP";
    public static final int dbVersion=1;
    public static Database instance;

    public Database(@Nullable Context context) {
        super(context, DBNAME, null, dbVersion);
    }
    public static Database getInstance(Context context){
        if (instance == null){
            instance= new Database(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(Users.CREATE_TABLE);


        } catch (Exception e){
            e.fillInStackTrace();


    }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +Users.TABLE);

            onCreate(sqLiteDatabase);
            Log.d("OnCreateDB","Upgraded=1");

        } else {
            Log.d("OnCreateDB","Upgraded=0");

        }
    }


    public boolean checkDublicateEmailAddress(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery(Users.CHECKACC_BY_EMAIL,new String[]{email});
        return cursor.getCount() <= 0;



    }

    public boolean RegisterUser(Users users) {
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        try {
            contentValues.put(Users.KEY_FIRSTNAME,users.getFirstName());
            contentValues.put(Users.KEY_LASTNAME,users.getLastName());
            contentValues.put(Users.KEY_CNIC,users.getCNIC());
            contentValues.put(Users.KEY_EMAIL,users.getEmail());
            contentValues.put(Users.KEY_PASSWORD,users.getPassword());
            long query = sqLiteDatabase.insertOrThrow(Users.TABLE,null,contentValues);
            return  query != -1;
        }catch (Exception e){
            e.fillInStackTrace();
            return false;
        }
    }

    public boolean loginUser(Users users) {
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor=sqLiteDatabase.rawQuery(Users.LoginByEmailPass,new String[]{users.getEmail(), users.getPassword()});
        return cursor.getCount() > 0;
    }

    @SuppressLint("Range")
    public Users getUserProfile(String email) {
        SQLiteDatabase sqLiteDatabase= getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery(Users.GetProfileData,new String[]{email});
        String fName,lName,cninc,Email,Password;
        int id;
        if (cursor.moveToFirst()){
            fName= cursor.getString(cursor.getColumnIndex(Users.KEY_FIRSTNAME));
            lName= cursor.getString(cursor.getColumnIndex(Users.KEY_LASTNAME));
            cninc= cursor.getString(cursor.getColumnIndex(Users.KEY_CNIC));
            Email=  cursor.getString(cursor.getColumnIndex(Users.KEY_EMAIL));
            Password=  cursor.getString(cursor.getColumnIndex(Users.KEY_PASSWORD));
            id= cursor.getInt(cursor.getColumnIndex(Users.KEY_USER_ID));
            return new Users(id,fName,lName,cninc, Email,Password);
        } else {
            return  null;
        }
    }
}
