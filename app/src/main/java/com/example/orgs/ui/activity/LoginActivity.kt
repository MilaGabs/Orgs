package com.example.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityLoginBinding
import com.example.orgs.extensions.goTo
import com.example.orgs.preferences.dataStore
import com.example.orgs.preferences.loggedUserKey
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val userDao by lazy {
        AppDatabase.instance(this).userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
        configuraBotaoEntrar()
    }

    private fun configuraBotaoEntrar() {
        binding.activityLoginBotaoEntrar.setOnClickListener {
            val user = binding.activityLoginUsuario.text.toString()
            val password = binding.activityLoginSenha.text.toString()
            Log.i("LoginActivity", "onCreate: $user - $password")
            lifecycleScope.launch {
                userDao.authenticateUser(user, password)?.let { user ->
                    dataStore.edit { preferences ->
                        preferences[loggedUserKey] = user.id
                    }

                    goTo(ProductListActivity::class.java)
                } ?: Toast.makeText(
                    this@LoginActivity,
                    "Usuário ou senha incorretos",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun configuraBotaoCadastrar() {
        binding.activityLoginBotaoCadastrar.setOnClickListener {
            goTo(UserRegisterFormActivity::class.java)
        }
    }

}