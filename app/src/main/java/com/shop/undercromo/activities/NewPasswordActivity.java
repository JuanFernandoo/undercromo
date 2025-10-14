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

public class NewPasswordActivity extends AppCompatActivity {
    private EditText createPassword, confirmPassword;
    private Button buttonForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_password);
        createPassword = findViewById(R.id.createPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        buttonForgotPassword = findViewById(R.id.buttonForgotPassword);

        buttonForgotPassword.setOnClickListener(v -> saveNewPassword());

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackPressed());
    }
    private void saveNewPassword(){
        String newPassword = createPassword.getText().toString();
        String confirmNewPassword = confirmPassword.getText().toString();

        if(newPassword.isEmpty() || confirmNewPassword.isEmpty()){
            Toast.makeText(this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!newPassword.equals(confirmNewPassword)){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("password", newPassword);
        editor.apply();

        Toast.makeText(this, "Contraseña actualizada", Toast.LENGTH_SHORT).show();
        finish();

        Intent intent = new Intent(NewPasswordActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
