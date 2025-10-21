package com.shop.undercromo.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.shop.undercromo.R;
import com.shop.undercromo.models.Product;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private Context context;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productImage.setImageResource(product.getImageResId());
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());
        holder.iconCart.setOnClickListener(v -> addToCart(product));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    private void addToCart(Product product) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("CartPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = sharedPreferences.getString("cartItems", "[]");
        Type type = new TypeToken<ArrayList<Product>>() {}.getType();
        ArrayList<Product> cartList = gson.fromJson(json, type);

        cartList.add(product);

        String updatedJson = gson.toJson(cartList);
        editor.putString("cartItems", updatedJson);
        editor.apply();

        Toast.makeText(context, product.getName() + " añadido al carrito", Toast.LENGTH_SHORT).show();
    }
    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage, iconCart, iconFavorite;
        TextView productName, productPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            iconCart = itemView.findViewById(R.id.icon_cart);
            iconFavorite = itemView.findViewById(R.id.icon_favorite);
        }
    }
}