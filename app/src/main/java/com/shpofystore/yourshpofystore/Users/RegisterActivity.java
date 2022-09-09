package com.shpofystore.yourshpofystore.Users;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shpofystore.yourshpofystore.Database.Database;
import com.shpofystore.yourshpofystore.Model.Users;
import com.shpofystore.yourshpofystore.R;

public class RegisterActivity extends AppCompatActivity {
EditText fName,lName,cnic,email,pass,retypePass;
Button btnReg;
Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fName= findViewById(R.id.regFirstName);
        lName= findViewById(R.id.regLastName);
        cnic= findViewById(R.id.regCnic);
        email= findViewById(R.id.regEmail);
        pass= findViewById(R.id.regPassword);
        retypePass= findViewById(R.id.regConfirmPass);
        btnReg= findViewById(R.id.btnReg);
        database= Database.getInstance(this);




        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FirstName= fName.getText().toString().trim();
                String LastName= lName.getText().toString().trim();
                String CNIC= cnic.getText().toString().trim();
                String Email= email.getText().toString().trim();
                String Pass= pass.getText().toString().trim().toLowerCase();
                String Retype= retypePass.getText().toString().trim().toLowerCase();
                if (TextUtils.isEmpty(FirstName)){
                    fName.setError("Required");
                }else if (TextUtils.isEmpty(LastName)){
                    lName.setError("Required");
                } else if (TextUtils.isEmpty(CNIC)){
                    cnic.setError("Required");
                }else if (TextUtils.isEmpty(Email)){
                    email.setError("Required");
                } else if (TextUtils.isEmpty(Pass)){
                    pass.setError("Required");
                }else if (TextUtils.isEmpty(Retype)){
                    retypePass.setError("Required");
                } else  if (!Pass.equals(Retype)){
                 retypePass.setError("Password Not Match");
                }else {
                    if (database.checkDublicateEmailAddress(Email)) {

                        Users users= new Users(FirstName,LastName,CNIC,Email,Pass);
                        if (database.RegisterUser(users)){
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "User Not Registered", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "User Account Already Registered", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}