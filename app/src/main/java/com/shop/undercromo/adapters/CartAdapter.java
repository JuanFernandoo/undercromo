package com.shop.undercromo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shop.undercromo.R;
import com.shop.undercromo.fragments.CartFragment.CartItem;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private ArrayList<CartItem> cartItems;
    private Context context;

    public CartAdapter(ArrayList<CartItem> cartItems, Context context) {
        this.cartItems = cartItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_product, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.textName.setText(item.getName());
        holder.textPrice.setText("$" + item.getPrice());
        holder.textQuantity.setText("Cantidad: " + item.getQuantity());
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textPrice, textQuantity;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.cart_product_name);
            textPrice = itemView.findViewById(R.id.cart_product_price);
            textQuantity = itemView.findViewById(R.id.cart_product_quantity);
        }
    }
}