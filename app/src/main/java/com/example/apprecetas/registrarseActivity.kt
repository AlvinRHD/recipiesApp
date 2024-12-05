package com.example.apprecetas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apprecetas.databinding.ActivityRegistrarseBinding
import com.google.firebase.auth.FirebaseAuth

class registrarseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarseBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrarseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()

        binding.registrarseButton.setOnClickListener{
            val email = binding.email.text.toString()
            val pwd = binding.pwd.text.toString()
            val confirmacion = binding.confirmacion.text.toString()

            if (email.isNotEmpty() && pwd.isNotEmpty() && confirmacion.isNotEmpty()){
                if (pwd==confirmacion){
                    firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                }else{
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()

                }
            }else{
                Toast.makeText(this, "Los campos no pueden estar vacios", Toast.LENGTH_SHORT).show()
            }
        }
        binding.redirigir.setOnClickListener{
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity((loginIntent))
        }
    }
}