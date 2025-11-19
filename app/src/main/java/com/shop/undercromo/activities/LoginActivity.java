package com.shop.undercromo.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.shop.undercromo.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin, btnGoogle;

    private GoogleSignInClient googleSignInClient;
    private static final int RC_GOOGLE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etEmail = findViewById(R.id.loginEmail);
        etPassword = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        btnGoogle = findViewById(R.id.buttonGoogle);

        btnLogin.setOnClickListener(view -> sharedPreferencesLogin(etEmail, etPassword));
        btnGoogle.setOnClickListener(view -> signInGoogle());

        TextView forgotPassword = findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)));

        TextView signUptext = findViewById(R.id.loginRedirect);
        signUptext.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, SignUpActivity.class)));

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void sharedPreferencesLogin(EditText etEmail, EditText etPassword){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Por favor, ingrese todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        String savedEmail = sharedPreferences.getString("email", "");
        String savedPassword = sharedPreferences.getString("password", "");

        boolean isAdmin = email.equals("admin@admin.com") && password.equals("admin123");

        if((email.equals(savedEmail) && password.equals(savedPassword)) || isAdmin){
            loginSuccess(isAdmin);
        }else{
            Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
        }
    }

    private void signInGoogle() {
        startActivityForResult(googleSignInClient.getSignInIntent(), RC_GOOGLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_GOOGLE){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if(account != null){
                    String name = account.getDisplayName();
                    Toast.makeText(this, "Bienvenido " + name, Toast.LENGTH_SHORT).show();
                    loginSuccess(false); // Google login no es admin
                }
            } catch (ApiException e){
                Toast.makeText(this, "Error al iniciar con Google", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loginSuccess(boolean isAdmin){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("isAdmin", isAdmin);
        startActivity(intent);
        finish();
    }
}
