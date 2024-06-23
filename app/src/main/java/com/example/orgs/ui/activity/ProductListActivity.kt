package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.database.dao.ProductDao
import com.example.orgs.databinding.ActivityProductListBinding
import com.example.orgs.model.Product
import com.example.orgs.ui.recyclerview.adapter.ProductsListAdapter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ProductListActivity : AppCompatActivity(R.layout.activity_product_list) {
    private val adapter = ProductsListAdapter(context = this)

    private val binding by lazy {
        ActivityProductListBinding.inflate(layoutInflater)
    }
    private val dao by lazy {
        val db = AppDatabase.instance(this)
        db.productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setupRecyclerView()
        setupFab()
        setContentView(binding.root)

        lifecycleScope.launch() {
            // Tira a cosourtine do contexto principal e manda a execução para outra thread (fazemos isso para não quebrar a execução da thread main)
            dao.searchAll().collect {
                adapter.update(it)
            }
        }
    }

//    override fun onResume() {
//        super.onResume()
        // Cria uma coroutine dentro da thread principal
//        val scope = MainScope()
        // Para capturar exceptions dentro das coroutines nos utilizamos este handler
//        val exceptionHandler = CoroutineExceptionHandler { coroutineContext: CoroutineContext, throwable: Throwable ->
//            Toast.makeText(this, "Ocorreu um pequeno problema", Toast.LENGTH_SHORT).show()
//        }
        // Para utilizado dentro do nosso contexto usamos scope.launch(exceptionHandler)
//        lifecycleScope.launch() {
            // Tira a cosourtine do contexto principal e manda a execução para outra thread (fazemos isso para não quebrar a execução da thread main)
//            dao.searchAll().collect {
//                adapter.update(it)
//            }
//        }
//    }

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
