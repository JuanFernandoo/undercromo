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

public class ProductAdminAdapter extends RecyclerView.Adapter<ProductAdminAdapter.ProductAdminViewHolder> {

    private List<Product> productList;
    private Context context;

    public ProductAdminAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_admin, parent, false);
        return new ProductAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdminViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productImage.setImageResource(product.getImageResId());
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());

        holder.iconEdit.setOnClickListener(v -> editProduct(product));
        holder.iconRemove.setOnClickListener(v -> removeProduct(product));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    private void editProduct(Product product) {
        Toast.makeText(context, "Editar: " + product.getName(), Toast.LENGTH_SHORT).show();
        // Aquí puedes abrir una nueva Activity o un diálogo para editar el producto
    }

    private void removeProduct(Product product) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ProductsPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = sharedPreferences.getString("productItems", "[]");
        Type type = new TypeToken<ArrayList<Product>>() {}.getType();
        ArrayList<Product> products = gson.fromJson(json, type);

        products.removeIf(p -> p.getName().equals(product.getName()));

        String updatedJson = gson.toJson(products);
        editor.putString("productItems", updatedJson);
        editor.apply();

        Toast.makeText(context, product.getName() + " eliminado", Toast.LENGTH_SHORT).show();
    }

    static class ProductAdminViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage, iconEdit, iconRemove;
        TextView productName, productPrice;

        public ProductAdminViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            iconEdit = itemView.findViewById(R.id.icon_edit);
            iconRemove = itemView.findViewById(R.id.icon_remove);
        }
    }
}
