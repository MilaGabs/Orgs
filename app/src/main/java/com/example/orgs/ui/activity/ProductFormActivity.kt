package com.example.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.orgs.R
import com.example.orgs.dao.ProductDao
import com.example.orgs.databinding.ActivityProductFormBinding
import com.example.orgs.databinding.ImageFormBinding
import com.example.orgs.extensions.tryToLoadImage
import com.example.orgs.model.Product
import com.example.orgs.ui.dialog.ImageFormDialog
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    private var url: String? = null

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
    }

    private fun setupSaveButton() {
        val saveButton = binding.productFormSaveButton
        saveButton.setOnClickListener {
            val newProduct = product()
            val dao = ProductDao()
            dao.add(newProduct)
            finish()
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
            BigDecimal.ZERO
        } else {
            BigDecimal(textValue)
        }

        return Product(
            name = name,
            description = description,
            value = value,
            image = url
        )
    }

}
