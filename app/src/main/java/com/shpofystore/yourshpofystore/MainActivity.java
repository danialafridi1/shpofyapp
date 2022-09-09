package com.shpofystore.yourshpofystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shpofystore.yourshpofystore.Users.LoginActivity;
import com.shpofystore.yourshpofystore.Users.RegisterActivity;

public class MainActivity extends AppCompatActivity {
Button btnLogin,btnReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin=findViewById(R.id.userSignin);
        btnReg= findViewById(R.id.userSignUp);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
btnReg.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }
});



    }
}