package com.example.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityProductFormBinding
import com.example.orgs.extensions.tryToLoadImage
import com.example.orgs.model.Product
import com.example.orgs.ui.dialog.ImageFormDialog
import kotlinx.coroutines.launch

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }
    private val productDao by lazy {
        AppDatabase.instance(this).productDao()
    }

    private var url: String? = null
    private var productId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSaveButton()
        setContentView(binding.root)
        title = "Cadastrar produto"
        binding.productFormImage.setOnClickListener {
            ImageFormDialog(this).show(url) { image ->
                url = image
                binding.productFormImage.tryToLoadImage(url)
            }
        }

        tryToLoadProduct()
    }

    private fun tryToLoadProduct() {
        productId = intent.getIntExtra("productId", 0)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            productDao.searchById(productId)?.let {
                fillFields(it)
            }
        }
    }

    private fun fillFields(loadedProduct: Product) {
        title = "Editar produto"
        binding.productFormName.setText(loadedProduct.name)
        binding.productFormDescription.setText(loadedProduct.description)
        binding.productFormValue.setText(loadedProduct.value.toString())
        url = loadedProduct.image
        binding.productFormImage.tryToLoadImage(url)
    }

    private fun setupSaveButton() {
        val saveButton = binding.productFormSaveButton
        saveButton.setOnClickListener {
            lifecycleScope.launch {
                val newProduct = product()
                productDao.save(newProduct)
                finish()
            }
        }
    }

    private fun product(): Product {
        val nameField = binding.productFormName
        val name = nameField.text.toString()

        val descField = binding.productFormDescription
        val description = descField.text.toString()

        val valueField = binding.productFormValue
        val textValue = valueField.text.toString()

        val value = if (textValue.isBlank()) {
            0.0
        } else {
            textValue.toDouble()
        }

        return Product(
            id = productId,
            name = name,
            description = description,
            value = value,
            image = url
        )
    }

}
