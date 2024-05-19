package com.example.orgs.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.orgs.R
import com.example.orgs.dao.ProductDao
import com.example.orgs.model.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSaveButton()
    }

    private fun setupSaveButton() {
        val saveButton = findViewById<Button>(R.id.product_form_save_button)
        saveButton.setOnClickListener {
            val newProduct = product()
            val dao = ProductDao()
            dao.add(newProduct)
            finish()
        }
    }

    private fun product(): Product {
        val nameField = findViewById<EditText>(R.id.product_form_name)
        val name = nameField.text.toString()

        val descField = findViewById<EditText>(R.id.product_form_description)
        val description = descField.text.toString()

        val valueField = findViewById<EditText>(R.id.product_form_value)
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
