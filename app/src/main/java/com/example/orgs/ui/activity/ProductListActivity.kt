package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.dao.ProductDao
import com.example.orgs.databinding.ActivityProductListBinding
import com.example.orgs.model.Product
import com.example.orgs.ui.recyclerview.adapter.ProductsListAdapter

class ProductListActivity : AppCompatActivity(R.layout.activity_product_list) {
    private val dao = ProductDao()
    private val adapter = ProductsListAdapter(context = this, products = dao.getAll())

    private val binding by lazy {
        ActivityProductListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setupRecyclerView()
        setupFab()
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.getAll())
    }

    private fun setupFab() {
        val fab = binding.floatingActionButton
        fab.setOnClickListener {
            navigateToProductForm()
        }
    }

    private fun navigateToProductForm() {
        val intent = Intent(this, ProductFormActivity::class.java)
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        // utilizamos a linha a seguir para que os itens sejam exibidos no list view, sem isso nada acontece
        // podemos configura-lo tanto aqui quanto no arquivo de layout
        //        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.whenItemClicked = {
            val intent = Intent(
                this,
                ProductDetailActivity::class.java
            ).apply {
                putExtra<Product>("product", it)
            }
            startActivity(intent)
        }
    }
}
