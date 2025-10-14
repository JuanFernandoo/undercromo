package com.shop.undercromo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shop.undercromo.R;
import com.shop.undercromo.models.Product;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList; // Lista de productos

    // Constructor del adaptador
    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño de cada ítem (item_product.xml)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        // Conectar los datos del producto con las vistas
        Product product = productList.get(position);
        holder.productImage.setImageResource(product.getImageResId());
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());
    }

    @Override
    public int getItemCount() {
        return productList.size(); // Número de productos
    }

    // ViewHolder: Maneja las vistas de cada ítem
    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
        }
    }
}