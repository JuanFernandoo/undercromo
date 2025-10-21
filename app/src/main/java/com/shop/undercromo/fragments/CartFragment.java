package com.shop.undercromo.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shop.undercromo.R;
import com.shop.undercromo.adapters.CartAdapter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayout emptyCartLayout;
    private TextView tvTotalAmount, tvDeliveryFee;
    private Button btnCheckout;
    private ArrayList<CartItem> cartItems;
    private CartAdapter cartAdapter;
    private SharedPreferences sharedPreferences;
    private static final String CART_PREFS = "cart_prefs";
    private static final String CART_KEY = "cart_items";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.recycler_cart_items);
        emptyCartLayout = view.findViewById(R.id.empty_cart_layout);
        tvTotalAmount = view.findViewById(R.id.tv_total_amount);
        tvDeliveryFee = view.findViewById(R.id.tv_delivery_fee);
        btnCheckout = view.findViewById(R.id.btn_checkout);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        sharedPreferences = requireContext().getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE);

        loadCartItems();
        setupCheckoutButton();

        return view;
    }

    private void loadCartItems() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(CART_KEY, null);
        Type type = new TypeToken<ArrayList<CartItem>>() {}.getType();
        cartItems = json != null ? gson.fromJson(json, type) : new ArrayList<>();

        if (cartItems.isEmpty()) {
            emptyCartLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyCartLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            cartAdapter = new CartAdapter(cartItems, getContext());
            recyclerView.setAdapter(cartAdapter);
            updateTotal();
        }
    }
    private void updateTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            try {
                total += Double.parseDouble(item.getPrice()) * item.getQuantity();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        double deliveryFee = 10.0;
        double finalTotal = total + deliveryFee;

        tvDeliveryFee.setText("$" + deliveryFee);
        tvTotalAmount.setText("$" + finalTotal);
    }

    private void setupCheckoutButton() {
        btnCheckout.setOnClickListener(v -> {
            if (cartItems.isEmpty()) {
                Toast.makeText(getContext(), "Tu carrito está vacío", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Compra realizada con éxito", Toast.LENGTH_SHORT).show();
                clearCart();
            }
        });
    }

    private void clearCart() {
        cartItems.clear();
        sharedPreferences.edit().remove(CART_KEY).apply();
        emptyCartLayout.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        tvTotalAmount.setText("$0");
    }

    // ✅ Clase interna que representa cada producto en el carrito
    public static class CartItem {
        private String name;
        private String price;
        private int quantity;

        public CartItem(String name, String price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}