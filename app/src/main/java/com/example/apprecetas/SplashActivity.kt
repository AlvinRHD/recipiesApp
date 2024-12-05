package com.example.apprecetas

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.apprecetas.adapters.OnboardingAdapter
import com.example.apprecetas.models.OnboardingItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SplashActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var btnGetStarted: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        // Inicializar vistas
        val swipeHintText = findViewById<TextView>(R.id.tvSwipeHint)
        val bgImage = findViewById<ImageView>(R.id.bgImage)
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        btnGetStarted = findViewById(R.id.btnGetStarted)
        progressBar = findViewById(R.id.progressBar)

        // Lista de elementos del onboarding
        val onboardingItems = listOf(
            OnboardingItem(0, "Recetas", "Descubre recetas deliciosas"),
            OnboardingItem(0, "Favoritos y Comparte", "Guarda tus recetas favoritas y compártelas con amigos"),
            OnboardingItem(0, "VEZ EL BOTON?", "DALE CLICK E INICIA SESIÓN")  // Página final


        )

        // Configuración del adapter

        val adapter = OnboardingAdapter(onboardingItems)
        viewPager.adapter = adapter

        // Configuración de TabLayout con ViewPager2
        // Configuración de TabLayout con ViewPager2 e íconos personalizados
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val tabIcons = listOf(
                R.drawable.ic_recetas,
                R.drawable.ic_favoritos,
                R.drawable.ic_compartir
            )
            tab.setIcon(tabIcons[position]) // Asignar íconos a las pestañas
        }.attach()




        // Hacer visible la imagen de fondo al principio
        bgImage.visibility = View.VISIBLE
        bgImage.alpha = 0f // Inicia en un valor de alfa 0 para que no se vea, y se hará visible con la animación

        /// Mostrar la imagen de fondo y el botón en la última página


        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val isLastPage = position == onboardingItems.size - 1

                // Cambiar la imagen de fondo
                val backgroundRes = when (position) {
                    0 -> R.drawable.bg_recetas // Fondo para la primera página
                    1 -> R.drawable.bg_favoritos_compartir // Fondo para la segunda página
                    else -> R.drawable.bg_splash // Fondo para la última página
                }
                bgImage.setImageResource(backgroundRes)
                bgImage.animate().alpha(1f).setDuration(500).start()

                // Mostrar u ocultar botón
                if (isLastPage) {
                    btnGetStarted.visibility = View.VISIBLE
                    btnGetStarted.animate().alpha(1f).setDuration(500).start()

                    // Ocultar el texto de "Desliza hacia la derecha" en la última página
                    swipeHintText.visibility = View.GONE
                } else {
                    swipeHintText.visibility = View.VISIBLE
                }
            }
        })


        // Acción del botón "Empezar"
        btnGetStarted.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            btnGetStarted.isEnabled = false

            // Simular carga
            Handler(Looper.getMainLooper()).postDelayed({
                progressBar.visibility = View.GONE
                btnGetStarted.isEnabled = true

                // Ir al LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 2000) // 2 segundos de espera
        }
    }
}