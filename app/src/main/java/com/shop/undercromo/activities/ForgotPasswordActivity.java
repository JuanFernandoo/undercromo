package com.shop.undercromo.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.shop.undercromo.R;

import java.util.Random;

public class ForgotPasswordActivity extends AppCompatActivity{
    private EditText forgotPasswordEmail;
    private Button buttonForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        forgotPasswordEmail = findViewById(R.id.forgotPasswordEmail);
        buttonForgotPassword = findViewById(R.id.buttonForgotPassword);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackPressed());

        buttonForgotPassword.setOnClickListener(v -> {
           String email = forgotPasswordEmail.getText().toString().trim();
           if(email.isEmpty()){
               forgotPasswordEmail.setError("Ingrese su correo electrónico");
               forgotPasswordEmail.requestFocus();
               return;
           }

            SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
            String savedEmail = sharedPreferences.getString("email", null);

            if (savedEmail != null && savedEmail.equals(email)){
                int otp = new Random().nextInt(9000) + 1000;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("otp", otp);
                editor.apply();

                Toast.makeText(this, "Código OTP: " + otp, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, OTPActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }else{
                forgotPasswordEmail.setError("Correo electrónico no registrado");
                forgotPasswordEmail.requestFocus();
            }
        });


    }


}
