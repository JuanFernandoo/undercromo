package com.shop.undercromo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shop.undercromo.R
import com.shop.undercromo.adapters.ProductAdapter
import com.shop.undercromo.models.Product

class ProductsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productList: MutableList<Product>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        // Lista de productos estática
        productList = mutableListOf(
            Product("The Path Birds T-shirt Black", "$100", R.drawable.product_placeholder),
            Product("The Path Gold Hoodie Rose Fire", "$250", R.drawable.product2),
            Product("Hekios Dazzled Boxy Fit T-shirt Faded Black", "$150", R.drawable.product3),
            Product("Thunder Rider Pegasus Zip Up Jacket Rusty Black", "$300", R.drawable.product4),
            Product("Gray T-Shirt", "$80", R.drawable.product5),
            Product("UnderGold Sweatshirt", "$120", R.drawable.product6)
        )

        productAdapter = ProductAdapter(productList)
        recyclerView.adapter = productAdapter

        return view
    }
}
