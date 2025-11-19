package com.shop.undercromo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shop.undercromo.R
import com.shop.undercromo.adapters.ProductAdapter
import com.shop.undercromo.data.ProductRepository
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

        val cartIcon: ImageView = view.findViewById(R.id.cart_icon)
        val favoritesIcon: ImageView = view.findViewById(R.id.favorites_icon)

        cartIcon.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CartFragment())
                .addToBackStack(null)
                .commit()
        }

        favoritesIcon.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, WishListFragment())
                .addToBackStack(null)
                .commit()
        }

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        // ✅ Usar la lista global de ProductRepository
        productList = ProductRepository.getInstance().getProducts().toMutableList()

        productAdapter = ProductAdapter(requireContext(), productList)
        recyclerView.adapter = productAdapter

        return view
    }

    override fun onResume() {
        super.onResume()
        // Refrescar la lista si hubo cambios desde admin
        productList.clear()
        productList.addAll(ProductRepository.getInstance().getProducts())
        productAdapter.notifyDataSetChanged()
    }
}
