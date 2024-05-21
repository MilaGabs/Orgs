package com.example.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.databinding.ItemProductBinding
import com.example.orgs.model.Product
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class ProductsListAdapter(
    products: List<Product>,
    private var context: Context
) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    class ViewHolder(binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        private val name = binding.productItemName
        private val description = binding.productItemDescription
        private val value = binding.productItemValue

        fun bind(product: Product) {
            name.text = product.name
            description.text = product.description
            value.text = formatToBrCurrency(product.value)
        }

        private fun formatToBrCurrency(value: BigDecimal): String {
            val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            return formatter.format(value)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // referencia do android para criar uma "view exclusiva"
        val binding = ItemProductBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    fun update(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }
}
