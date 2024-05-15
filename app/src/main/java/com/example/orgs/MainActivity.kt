package com.example.orgs

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var nome = findViewById<TextView>(R.id.nome)
        nome.text = "Cesta de frutas"

        var descricao = findViewById<TextView>(R.id.descricao)
        descricao.text = "Laranja, manga e maçã"

        var valor = findViewById<TextView>(R.id.valor)
        valor.text = "19.99"
    }
}
