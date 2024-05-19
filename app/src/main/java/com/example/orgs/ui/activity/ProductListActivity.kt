package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.dao.ProductDao
import com.example.orgs.ui.recyclerview.adapter.ProductsListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductListActivity : AppCompatActivity(R.layout.activity_product_list) {
    private val dao = ProductDao()
    private val adapter = ProductsListAdapter(context = this, products = dao.getAll())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setupRecyclerView()
        setupFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.getAll())
    }

    private fun setupFab() {
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            navigateToProductForm()
        }
    }

    private fun navigateToProductForm() {
        val intent = Intent(this, ProductFormActivity::class.java)
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        // utilizamos a linha a seguir para que os itens sejam exibidos no list view, sem isso nada acontece
        // podemos configura-lo tanto aqui quanto no arquivo de layout
        //        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
