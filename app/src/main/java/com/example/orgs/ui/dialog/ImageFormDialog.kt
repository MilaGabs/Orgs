package com.example.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.orgs.databinding.ImageFormBinding
import com.example.orgs.extensions.tryToLoadImage

class ImageFormDialog(private val context: Context) {
    fun show(defualtUrl: String? = null, whenImageLoaded: (image: String) -> Unit) {
        var url: String = ""
        ImageFormBinding.inflate(LayoutInflater.from(context)).apply {
            imageFormLoadButton.setOnClickListener {
                url = imageFormUrl.text.toString()
                imageFormImageview.tryToLoadImage(url)
            }

            defualtUrl?.let {
                imageFormImageview.tryToLoadImage(it)
                imageFormUrl.setText(it)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") {_ , _ ->
                    whenImageLoaded(url)
                }
                .setNegativeButton("Cancelar") {_ , _ -> }
                .show()
        }
    }
}
