package com.shop.undercromo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.shop.undercromo.R;
import com.shop.undercromo.data.ProductRepository;
import com.shop.undercromo.models.Product;

public class ProductEditFragment extends Fragment {

    private Product product;
    private int position;

    public static ProductEditFragment newInstance(Product product, int position) {
        ProductEditFragment fragment = new ProductEditFragment();
        Bundle args = new Bundle();
        args.putSerializable("product", product);
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        product = (Product) getArguments().getSerializable("product");
        position = getArguments().getInt("position");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_edit, container, false);

        ImageView image = view.findViewById(R.id.productImagePreview);
        EditText name = view.findViewById(R.id.editProductName);
        EditText price = view.findViewById(R.id.editProductPrice);
        EditText desc = view.findViewById(R.id.editProductDescription);
        EditText stock = view.findViewById(R.id.editProductStock);
        Button saveButton = view.findViewById(R.id.saveProductButton);

        Glide.with(this).load(product.getImageRes()).into(image);

        name.setText(product.getName());
        price.setText(product.getPrice());
        desc.setText(product.getDescription());
        stock.setText(String.valueOf(product.getStock()));

        saveButton.setOnClickListener(v -> {
            product.setName(name.getText().toString());
            product.setPrice(price.getText().toString());
            product.setDescription(desc.getText().toString());
            product.setStock(Integer.parseInt(stock.getText().toString()));

            // Guardar cambios en el repositorio
            ProductRepository.getInstance().updateProduct(position, product);

            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return view;
    }
}
