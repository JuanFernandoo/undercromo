package com.shop.undercromo.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.shop.undercromo.R;

public class UserFragment extends Fragment {

    private TextView userName, userEmail, userPhone;
    private ImageView profileImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        userName = view.findViewById(R.id.tvName);
        userEmail = view.findViewById(R.id.tvEmail);
        userPhone = view.findViewById(R.id.tvPhone);
        profileImage = view.findViewById(R.id.profileImage);

        // Usa el mismo nombre del archivo SharedPreferences que en SignUpActivity
        SharedPreferences prefs = requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);

        String name = prefs.getString("name", "Nombre no disponible");
        String email = prefs.getString("email", "Correo no disponible");
        String phone = prefs.getString("phone", "Teléfono no disponible");

        userName.setText(name);
        userEmail.setText(email);
        userPhone.setText(phone);

        Button btnEdit = view.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(v -> {
            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();
            transaction.replace(R.id.fragment_container, new EditUserFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        return view;
    }
}
