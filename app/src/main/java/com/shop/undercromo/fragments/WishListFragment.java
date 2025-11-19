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
import com.shop.undercromo.adapters.WishListAdapter;
import com.shop.undercromo.models.Product;

import java.util.ArrayList;
import java.util.List;

public class WishListFragment extends Fragment {

    private RecyclerView recyclerView;
    private WishListAdapter wishListAdapter;
    private List<Product> wishList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);

        recyclerView = view.findViewById(R.id.recycler_wishlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        wishList = new ArrayList<>();

        // PRODUCTOS DE EJEMPLO (compatible con tu Product actual)
        wishList.add(new Product(
                "1",
                "The Path Birds T-shirt Black",
                "$100",
                R.drawable.product_placeholder,
                "Camisa negra edición especial",
                15
        ));

        wishList.add(new Product(
                "2",
                "The Path Gold Hoodie Rose Fire",
                "$250",
                R.drawable.product2,
                "Hoodie premium color Rose Fire",
                10
        ));

        wishList.add(new Product(
                "3",
                "Hekios Dazzled Boxy Fit T-shirt Faded Black",
                "$150",
                R.drawable.product3,
                "Camiseta Boxy Fit tono Faded Black",
                8
        ));

        wishList.add(new Product(
                "4",
                "Thunder Rider Pegasus Zip Up Jacket Rusty Black",
                "$300",
                R.drawable.product4,
                "Chaqueta zip-up edición Pegasus",
                5
        ));

        wishList.add(new Product(
                "5",
                "Gray T-Shirt",
                "$80",
                R.drawable.product5,
                "Camiseta gris básica premium",
                20
        ));

        wishList.add(new Product(
                "6",
                "UnderGold Sweatshirt",
                "$120",
                R.drawable.product6,
                "Sudadera UnderGold edición limitada",
                12
        ));

        wishListAdapter = new WishListAdapter(getContext(), wishList);
        recyclerView.setAdapter(wishListAdapter);

        return view;
    }
}
