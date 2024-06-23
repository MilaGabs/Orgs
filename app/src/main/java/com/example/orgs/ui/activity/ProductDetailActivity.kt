package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityProductDetailBinding
import com.example.orgs.extensions.tryToLoadImage
import com.example.orgs.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.NumberFormat
import java.util.Locale

class ProductDetailActivity : AppCompatActivity(R.layout.activity_product_detail) {
    private var product: Product? = null
    private var productId = 0
    private val binding by lazy {
        ActivityProductDetailBinding.inflate(layoutInflater)
    }
    private val productDao by lazy {
        AppDatabase.instance(this).productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        tryToLoadProduct()
    }

    override fun onResume() {
        super.onResume()
        searchForProduct()
    }

    private fun searchForProduct() {
        lifecycleScope.launch {
            product = productDao.searchById(productId)
            product?.let {
                loadProduct()
            } ?: finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_edit_product_details -> {
                product?.let {
                    Intent(this, ProductFormActivity::class.java).apply {
                        putExtra("productId", it.id)
                        startActivity(this)
                    }
                }
            }

            R.id.menu_delete_product_details -> {
                lifecycleScope.launch {
                    product?.let { productDao.delete(it) }
                    finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun tryToLoadProduct() {
        productId = intent.getIntExtra("productId", 0)
    }

    private fun loadProduct() {
        binding.productDetailName.text = product?.name ?: ""
        binding.productDetailDescription.text = product?.description ?: ""
        binding.productDetailValue.text = formatToBrCurrency(product?.value ?: 0.0)
        binding.productDetailImageview.tryToLoadImage(product?.image)
    }

    private fun formatToBrCurrency(value: Double): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        return formatter.format(value)
    }
}
