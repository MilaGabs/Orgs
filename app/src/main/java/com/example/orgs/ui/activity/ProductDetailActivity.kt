package com.example.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.databinding.ActivityProductDetailBinding
import com.example.orgs.extensions.tryToLoadImage
import com.example.orgs.ui.dialog.ImageFormDialog

class ProductDetailActivity: AppCompatActivity(R.layout.activity_product_detail) {

    private val binding by lazy {
        ActivityProductDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    private fun tryToLoadProduct() {
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            preencheCampos(produtoCarregado)
        } ?: finish()
    }
}
