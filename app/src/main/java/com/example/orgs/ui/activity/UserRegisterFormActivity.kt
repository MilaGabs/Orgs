package com.example.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityUserRegisterFormBinding
import com.example.orgs.model.User
import kotlinx.coroutines.launch

class UserRegisterFormActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityUserRegisterFormBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        AppDatabase.instance(this).userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
    }

    private fun configuraBotaoCadastrar() {
        binding.activityUserRegisterFormRegisterButton.setOnClickListener {
            val novoUsuario = criaUsuario()
            Log.i("CadastroUsuario", "onCreate: $novoUsuario")
            lifecycleScope.launch {
                try {
                    dao.save(novoUsuario)
                    finish()
                } catch (e: Exception) {
                    Log.e("CadastroUsuario", "onCreate: ${e.message}", e)
                    Toast.makeText(
                        this@UserRegisterFormActivity,
                        "Erro ao cadastrar usuário",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun criaUsuario(): User {
        val user = binding. activityUserRegisterFormUser.text.toString()
        val name = binding.activityUserRegisterFormName.text.toString()
        val password = binding.activityUserRegisterFormPassword.text.toString()
        return User(user, name, password)
    }
}