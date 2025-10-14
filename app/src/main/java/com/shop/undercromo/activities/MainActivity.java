package com.shop.undercromo.activities;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.shop.undercromo.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);

        // Configurar el botón de menú (hamburguesa)
        ImageButton menuButton = findViewById(R.id.menu_button);
        menuButton.setOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(navView)) {
                drawerLayout.closeDrawer(navView); // Cerrar el menú si ya está abierto
            } else {
                drawerLayout.openDrawer(navView); // Abrir el menú si está cerrado
            }
        });
    }

    // Manejar el botón "Atrás" para cerrar el menú si está abierto
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navView)) {
            drawerLayout.closeDrawer(navView);
        } else {
            super.onBackPressed();
        }
    }
}