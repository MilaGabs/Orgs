package com.example.orgs.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.model.Product
import com.example.orgs.ui.recyclerview.adapter.ProductsListAdapter
import java.math.BigDecimal

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ProductsListAdapter(
            context = this,
            products = listOf<Product>(
                Product(
                    name = "Test",
                    description = "Description test",
                    value = BigDecimal("19.99")
                ),
                Product(
                    name = "Test 1",
                    description = "Description test 1",
                    value = BigDecimal("20.50")
                )
            )
        )
// utilizamos a linha a seguir para que os itens sejam exibidos no list view, sem isso nada acontece
// podemos configura-lo tanto aqui quanto no arquivo de layout
//        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
