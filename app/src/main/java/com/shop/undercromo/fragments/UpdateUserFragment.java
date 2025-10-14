package com.shop.undercromo.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shop.undercromo.R;

public class UpdateUserFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private EditText etName, etEmail, etPhone;
    private Button btnUpdate;
    private TextView upNameUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.edit_user_information, container, false);

        sharedPreferences = requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);

        etName = view.findViewById(R.id.editName);
        etEmail = view.findViewById(R.id.editEmail);
        etPhone = view.findViewById(R.id.editPhone);
        btnUpdate = view.findViewById(R.id.buttonUpdate);
        upNameUser = view.findViewById(R.id.userName);

        etName.setText(sharedPreferences.getString("name", ""));
        etEmail.setText(sharedPreferences.getString("email", ""));
        etPhone.setText(sharedPreferences.getString("phone", ""));

        upNameUser.setText(etName.getText().toString());

        btnUpdate.setOnClickListener(v -> {
            String newName = etName.getText().toString();
            String newEmail = etEmail.getText().toString();
            String newPhone = etPhone.getText().toString();

            if(validateFields(newName, newEmail, newPhone)){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", newName);
                editor.putString("email", newEmail);
                editor.putString("phone", newPhone);
                editor.apply();

                upNameUser.setText(newName);

                Toast.makeText(requireContext(), "Información actualizada", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private boolean validateFields(String name, String email, String phone){
        if(name.isEmpty() || email.isEmpty() || phone.isEmpty()){
            Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



}
