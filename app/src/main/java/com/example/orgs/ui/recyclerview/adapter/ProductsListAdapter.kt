package com.example.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.model.Product

class ProductsListAdapter(
    products: List<Product>,
    private var context: Context
) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(product: Product) {
            val nome = itemView.findViewById<TextView>(R.id.product_item_name)
            nome.text = product.name
            val descricao = itemView.findViewById<TextView>(R.id.product_item_description)
            descricao.text = product.description
            val valor = itemView.findViewById<TextView>(R.id.product_item_value)
            valor.text = product.value.toPlainString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // referencia do android para criar uma "view exclusiva"
        val inflate = LayoutInflater.from(context)
        val view = inflate.inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
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
