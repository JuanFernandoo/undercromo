package com.shop.undercromo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shop.undercromo.R;
import com.shop.undercromo.models.Product;

import java.util.List;

public class ProductAdminAdapter extends RecyclerView.Adapter<ProductAdminAdapter.ViewHolder> {

    private Context context;
    private List<Product> products;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Product product, int position);
        void onEditClick(Product product, int position);
        void onRemoveClick(Product product, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ProductAdminAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_admin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = products.get(position);

        holder.name.setText(product.getName());
        holder.price.setText(product.getPrice());

        Glide.with(context)
                .load(product.getImageRes())
                .placeholder(R.drawable.product_placeholder)
                .into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(product, holder.getAdapterPosition());
        });

        holder.editIcon.setOnClickListener(v -> {
            if (listener != null) listener.onEditClick(product, holder.getAdapterPosition());
        });

        holder.removeIcon.setOnClickListener(v -> {
            if (listener != null) listener.onRemoveClick(product, holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image, editIcon, removeIcon;
        TextView name, price;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            editIcon = itemView.findViewById(R.id.icon_edit);
            removeIcon = itemView.findViewById(R.id.icon_remove);
        }
    }
}
