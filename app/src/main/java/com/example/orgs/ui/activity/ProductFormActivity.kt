package com.example.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.orgs.R
import com.example.orgs.dao.ProductDao
import com.example.orgs.databinding.ActivityProductFormBinding
import com.example.orgs.model.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSaveButton()
        setContentView(binding.root)
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
            value = value
        )
    }

}
