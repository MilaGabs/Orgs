package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityProductListBinding
import com.example.orgs.ui.recyclerview.adapter.ProductsListAdapter

class ProductListActivity : AppCompatActivity(R.layout.activity_product_list) {
    private val adapter = ProductsListAdapter(context = this)

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
        val db = AppDatabase.instance(this)
        val productDao = db.productDao()
        adapter.update(productDao.searchAll())
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
                putExtra("productId", it.id)
            }
            startActivity(intent)
        }
    }
}
