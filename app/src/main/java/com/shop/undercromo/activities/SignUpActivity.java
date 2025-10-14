package com.shop.undercromo.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shop.undercromo.R;

public class SignUpActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private EditText etName, etPhone, etEmail, etPassword, etConfirmPassword;
    private CheckBox checkBoxTYC;
    private Button btnSignUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup);
        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);

                etName = findViewById(R.id.signUpName);
                etPhone = findViewById(R.id.signUpPhone);
                etEmail = findViewById(R.id.signUpEmail);
                etPassword = findViewById(R.id.signUpPassword);
                etConfirmPassword = findViewById(R.id.signUpConfirmPassword);
                checkBoxTYC = findViewById(R.id.checkBoxTYC);
                btnSignUp = findViewById(R.id.buttonSignUp);
                setupOnClickListeners();

        TextView logInText = findViewById(R.id.loginRedirect);
        logInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupOnClickListeners(){
        if (btnSignUp != null){
            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = etName.getText().toString().trim();
                    String phone = etPhone.getText().toString().trim();
                    String email = etEmail.getText().toString().trim();
                    String password = etPassword.getText().toString().trim();
                    String confirmPassword = etConfirmPassword.getText().toString().trim();
                    boolean tycChecked = checkBoxTYC.isChecked();

                    if(validateFields(name, phone, email, password, confirmPassword, tycChecked)){
                        keepData(name, phone, email, password, confirmPassword);

                        Toast.makeText(SignUpActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }

    private boolean validateFields(String name, String phone, String email, String password, String confirmPassword, boolean tycChecked){
        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || !tycChecked){
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(confirmPassword)){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void keepData (String name, String phone, String email, String password, String confirmPassword){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("phone", phone);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("confirmPassword", confirmPassword);
        editor.apply();
    }
}
