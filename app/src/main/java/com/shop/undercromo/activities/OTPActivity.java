package com.shop.undercromo.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_your_email);

        code1 = findViewById(R.id.code1);
        code2 = findViewById(R.id.code2);
        code3 = findViewById(R.id.code3);
        code4 = findViewById(R.id.code4);
        buttonVerify = findViewById(R.id.buttonVerify);
        resendCode = findViewById(R.id.resendCode);

        setupOtpInputs();

        buttonVerify.setOnClickListener(v -> verifyCode());
        resendCode.setOnClickListener(v -> resendOtp());

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackPressed());
    }

    // ---------------------------------------------------------
    //  CONFIGURACIÓN DE LOS INPUTS PARA QUE SE MUEVAN AUTOMÁTICO
    // ---------------------------------------------------------
    private void setupOtpInputs() {
        code1.addTextChangedListener(new OTPTextWatcher(code1, code2, null));
        code2.addTextChangedListener(new OTPTextWatcher(code2, code3, code1));
        code3.addTextChangedListener(new OTPTextWatcher(code3, code4, code2));
        code4.addTextChangedListener(new OTPTextWatcher(code4, null, code3));
    }

    private class OTPTextWatcher implements TextWatcher {
        private EditText current, next, previous;

        public OTPTextWatcher(EditText current, EditText next, EditText previous) {
            this.current = current;
            this.next = next;
            this.previous = previous;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 1 && next != null) {
                next.requestFocus();
            } else if (s.length() == 0 && previous != null) {
                previous.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}
    }

    // ---------------------------------------------------------
    //  VERIFICAR OTP
    // ---------------------------------------------------------
    private void verifyCode() {
        String enteredCode = code1.getText().toString()
                + code2.getText().toString()
                + code3.getText().toString()
                + code4.getText().toString();

        if (enteredCode.length() != 4) {
            Toast.makeText(this, "Ingrese el código de 4 dígitos", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        int savedOtp = sharedPreferences.getInt("otp", -1);

        if (savedOtp == Integer.parseInt(enteredCode)) {
            Toast.makeText(this, "Código correcto", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(OTPActivity.this, NewPasswordActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Código incorrecto", Toast.LENGTH_SHORT).show();
        }
    }

    // ---------------------------------------------------------
    //  REENVIAR NUEVO OTP
    // ---------------------------------------------------------
    private void resendOtp() {
        int newOtp = (int) (Math.random() * 9000) + 1000;

        SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("otp", newOtp);
        editor.apply();

        Toast.makeText(this, "Nuevo Código OTP: " + newOtp, Toast.LENGTH_LONG).show();
    }
}
