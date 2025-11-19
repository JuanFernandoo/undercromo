package com.shop.undercromo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shop.undercromo.R;
import com.shop.undercromo.adapters.ProductAdminAdapter;
import com.shop.undercromo.data.ProductRepository;
import com.shop.undercromo.models.Product;

import java.util.List;

public class ProductsAdminFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdminAdapter adapter;
    private List<Product> productList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_products_admin, container, false);

        recyclerView = view.findViewById(R.id.recycler_admin_products);

        // Obtener lista del repositorio
        productList = ProductRepository.getInstance().getProducts();

        adapter = new ProductAdminAdapter(getContext(), productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Click listener
        adapter.setOnItemClickListener(new ProductAdminAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product, int position) {
                // click en item completo si quieres
            }

            @Override
            public void onEditClick(Product product, int position) {
                // abrir fragment de edición
                ProductEditFragment editFragment = ProductEditFragment.newInstance(product, position);
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, editFragment)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onRemoveClick(Product product, int position) {
                ProductRepository.getInstance().removeProduct(position);
                adapter.notifyItemRemoved(position);
            }
        });

        return view;
    }
}
