package com.shop.undercromo.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shop.undercromo.R;

public class OTPActivity extends AppCompatActivity {

    private EditText code1, code2, code3, code4;
    private Button buttonVerify;
    private TextView resendCode;

    @Override
    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_your_email);

        code1 = findViewById(R.id.code1);
        code2 = findViewById(R.id.code2);
        code3 = findViewById(R.id.code3);
        code4 = findViewById(R.id.code4);
        buttonVerify = findViewById(R.id.buttonVerify);
        resendCode = findViewById(R.id.resendCode);

        buttonVerify.setOnClickListener(v -> verifyCode());
        resendCode.setOnClickListener(v -> resendOtp());

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackPressed());
    }

    private void verifyCode() {
        String enteredCode = code1.getText().toString() + code2.getText().toString() + code3.getText().toString() + code4.getText().toString();

        if (enteredCode.length() != 4){
            Toast.makeText(this, "Ingrese el codigo de 4 digitos", Toast.LENGTH_SHORT).show();
        return;
        }
        SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        int savedOtp = sharedPreferences.getInt("otp", -1);

        if(savedOtp == Integer.parseInt(enteredCode)){
            Toast.makeText(this, "Codigo correcto", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(OTPActivity.this, NewPasswordActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Codigo incorrecto", Toast.LENGTH_SHORT).show();
        }
    }
    private void resendOtp(){
        int newOtp = (int)(Math.random() * 9000) + 1000;

        SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("otp", newOtp);
        editor.apply();

        Toast.makeText(this, "Nuevo Código OTP: " + newOtp, Toast.LENGTH_LONG).show();
    }
}
