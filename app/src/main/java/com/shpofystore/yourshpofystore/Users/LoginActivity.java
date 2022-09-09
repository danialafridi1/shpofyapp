package com.shpofystore.yourshpofystore.Users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.shpofystore.yourshpofystore.Database.Database;
import com.shpofystore.yourshpofystore.MainActivity;
import com.shpofystore.yourshpofystore.Model.Users;
import com.shpofystore.yourshpofystore.R;
import com.shpofystore.yourshpofystore.capcha.Captcha;
import com.shpofystore.yourshpofystore.capcha.MathCaptcha;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
EditText email,pass;
Button btnReg,btnLogin,btnBack,btnCaptcha;
Database database;
boolean token;
SessionController sessionController;
    Captcha c;
    @Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_login);
email= findViewById(R.id.loginEmail);
pass= findViewById(R.id.loginPass);
btnBack= findViewById(R.id.btnBack);
btnLogin=findViewById(R.id.btnLogin);
btnReg= findViewById(R.id.btnRegister);
    btnCaptcha=findViewById(R.id.btnCap);
database=Database.getInstance(this);
sessionController= new SessionController(this);
    btnCaptcha.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       doRecaptcha();
    }
});


btnLogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String Email = email.getText().toString().trim();
        String Pass = pass.getText().toString().trim().toLowerCase();

        if (TextUtils.isEmpty(Email)){
            email.setError("Required");
        } else if (TextUtils.isEmpty(Pass)){
            pass.setError("Required");
        }

        else if (!token){
            Toast.makeText(LoginActivity.this, "Please Solve This Captcha", Toast.LENGTH_SHORT).show();
           doRecaptcha();
        }else {

            Users users= new Users(Email,Pass);
            if (database.loginUser(users)){
                sessionController.setLogin(true);
                sessionController.setEmail(Email);
                Toast.makeText(LoginActivity.this, "User LoggedIn", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, com.shpofystore.yourshpofystore.Users.MainActivity.class));
            }
            else {
                Toast.makeText(LoginActivity.this, "Wrong Email/Password", Toast.LENGTH_SHORT).show();
            }
        }
    }
});





btnReg.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
}
});
btnBack.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
startActivity(new Intent(LoginActivity.this, MainActivity.class));
}
});





}
private void doRecaptcha(){
    final Dialog dialog = new Dialog(LoginActivity.this);
    dialog.setContentView(R.layout.capchaview);
dialog.setCancelable(false);
    ImageView img= dialog.findViewById(R.id.capId);
    EditText code = dialog.findViewById(R.id.capNumber);
    Button btnVerify=dialog.findViewById(R.id.btnVerifyCode);
  c= new MathCaptcha(300, 100, MathCaptcha.MathOptions.PLUS_MINUS_MULTIPLY);
    img.setImageBitmap(c.image);

    img.setLayoutParams(new LinearLayout.LayoutParams(c.width *2, c.height *2));

    btnVerify.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String EnterCode = code.getText().toString();
            if (TextUtils.isEmpty(EnterCode)){
                code.setError("Required");
            } else {
                if (c.checkAnswer(EnterCode)) {
                    Toast.makeText(LoginActivity.this, "Captcha Code Validated", Toast.LENGTH_SHORT).show();

                    token= c.checkAnswer(EnterCode);
                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                    doRecaptcha();
                    Toast.makeText(LoginActivity.this, "Captcha Code Invalid Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        }
    });
    dialog.show();
}


}
