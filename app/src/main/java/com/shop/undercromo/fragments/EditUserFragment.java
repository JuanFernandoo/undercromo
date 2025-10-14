package com.shop.undercromo.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.shop.undercromo.R;

public class EditUserFragment extends Fragment {

    private EditText editName, editEmail, editPhone, editOldPassword, editNewPassword, editConfirmPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_user_fragment, container, false);

        editName = view.findViewById(R.id.editName);
        editEmail = view.findViewById(R.id.editEmail);
        editPhone = view.findViewById(R.id.editPhone);
        editOldPassword = view.findViewById(R.id.editOldPassword);
        editNewPassword = view.findViewById(R.id.editNewPassword);
        editConfirmPassword = view.findViewById(R.id.editConfirmPassword);

        Button buttonUpdate = view.findViewById(R.id.buttonUpdate);
        ImageButton backButton = view.findViewById(R.id.backButton);

        // Cargar los datos guardados del usuario
        SharedPreferences prefs = requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        editName.setText(prefs.getString("name", ""));
        editEmail.setText(prefs.getString("email", ""));
        editPhone.setText(prefs.getString("phone", ""));

        buttonUpdate.setOnClickListener(v -> updateUserInfo());
        backButton.setOnClickListener(v -> requireActivity().onBackPressed());

        return view;
    }

    private void updateUserInfo() {
        String name = editName.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();
        String oldPass = editOldPassword.getText().toString().trim();
        String newPass = editNewPassword.getText().toString().trim();
        String confirmPass = editConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone)) {
            Toast.makeText(getContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences prefs = requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        String savedPass = prefs.getString("password", "");

        if (!TextUtils.isEmpty(newPass) || !TextUtils.isEmpty(confirmPass)) {
            if (TextUtils.isEmpty(oldPass)) {
                Toast.makeText(getContext(), "Ingresa tu contraseña actual", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!oldPass.equals(savedPass)) {
                Toast.makeText(getContext(), "Contraseña actual incorrecta", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!newPass.equals(confirmPass)) {
                Toast.makeText(getContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("phone", phone);
        if (!TextUtils.isEmpty(newPass)) editor.putString("password", newPass);
        editor.apply();

        Toast.makeText(getContext(), "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
        requireActivity().onBackPressed();
    }
}
