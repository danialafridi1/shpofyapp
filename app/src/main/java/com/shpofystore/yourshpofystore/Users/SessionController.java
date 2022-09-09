package com.shpofystore.yourshpofystore.Users;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.shpofystore.yourshpofystore.MainActivity;


public class SessionController {

    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int Private_mode = 0;

    private static final String PREF_NAME = "USERS";




    public SessionController(Context context) {
        this._context= context;
        pref =context.getSharedPreferences(PREF_NAME,0);
        editor =pref.edit();
        editor.apply();
    }

    public  void setLogin(boolean login){
        editor.putBoolean("KEY_LOGIN",login);
        editor.commit();
    }
    public  boolean getLogin(){
        return pref.getBoolean("KEY_LOGIN",false);
    }
    public  void setEmail (String  email){
        editor.putString("Email",email);
        editor.commit();
    }
    public  String getEmail (){
         return pref.getString("Email","");
    }
    public  void logout(){
        setLogin(false);
        setEmail(null);
        _context.startActivity(new Intent(_context, MainActivity.class
        ));
        Toast.makeText(_context, "Logout Successfully", Toast.LENGTH_SHORT).show();
        ((Activity)_context).finish();
    }
}