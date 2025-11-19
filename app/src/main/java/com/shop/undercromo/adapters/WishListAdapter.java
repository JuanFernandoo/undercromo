package com.shop.undercromo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shop.undercromo.R;
import com.shop.undercromo.models.Product;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    private Context context;
    private List<Product> wishList;

    public WishListAdapter(Context context, List<Product> wishList) {
        this.context = context;
        this.wishList = wishList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice, btnAddCart;
        ImageButton btnRemove;

        public ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            btnAddCart = itemView.findViewById(R.id.btn_add_cart);
            btnRemove = itemView.findViewById(R.id.btn_remove);
        }
    }

    @NonNull
    @Override
    public WishListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wishlist_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishListAdapter.ViewHolder holder, int position) {
        Product product = wishList.get(position);

        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());
        holder.productImage.setImageResource(product.getImageRes());

        // Desactivamos las acciones por ahora
        holder.btnAddCart.setEnabled(false);
        holder.btnRemove.setEnabled(false);
        holder.btnAddCart.setAlpha(0.5f);
        holder.btnRemove.setAlpha(0.5f);
    }

    @Override
    public int getItemCount() {
        return wishList.size();
    }
}
