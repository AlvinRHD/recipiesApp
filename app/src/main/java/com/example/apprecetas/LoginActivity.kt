package com.example.apprecetas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apprecetas.databinding.ActivityLoginBinding
import com.example.apprecetas.databinding.ActivityRegistrarseBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()


        binding.inicioButton.setOnClickListener {
            val email = binding.loginEmail.text.toString()
            val pwd = binding.loginPwd.text.toString()

            if (email.isNotEmpty() && pwd.isNotEmpty()){

                firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener {
                    if (it.isSuccessful){
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this, "Ingrese las credenciales", Toast.LENGTH_SHORT).show()
            }
        }

        binding.logRedirigir.setOnClickListener{
            val inscribirse = Intent(this, registrarseActivity::class.java)
            startActivity(inscribirse)
        }
    }
}