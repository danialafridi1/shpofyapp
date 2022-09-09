package com.shpofystore.yourshpofystore.Users;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shpofystore.yourshpofystore.Database.Database;
import com.shpofystore.yourshpofystore.Model.Users;
import com.shpofystore.yourshpofystore.R;

public class MainActivity extends AppCompatActivity {
Button btnLogut;
TextView name,cnic,email,pass,lastname;
SessionController sessionController;
Users users;
String Email;
Database database;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_main);
        btnLogut= findViewById(R.id.logout);
        name=findViewById(R.id.tvName);
        lastname=findViewById(R.id.tvLastName);
        cnic=findViewById(R.id.tvCNINC);
        email=findViewById(R.id.tvEmail);
        pass=findViewById(R.id.tvPass);
        database=Database.getInstance(this);
        sessionController= new SessionController(this);
        if (sessionController.getLogin()){
            Email= sessionController.getEmail();
            users= database.getUserProfile(Email);
            name.setText(users.getFirstName());
            lastname.setText(users.getLastName());
            cnic.setText(users.getCNIC());
            email.setText(sessionController.getEmail());
            pass.setText(users.getPassword());
        }
else {
    sessionController.logout();
        }


        btnLogut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionController.logout();
            }
        });
    }
}