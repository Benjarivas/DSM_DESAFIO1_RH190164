package com.rh190164.desafio1_rh190164

import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Seteamos los botones para ejercicio
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)

        // Boton1 - Promedios
        button1.setOnClickListener {
            val intent = Intent(this, PromedioActivity::class.java)
            startActivity(intent)
        }

        // Boton2 - Retenciones de salario
        button2.setOnClickListener {
            val intent = Intent(this, SalarioActivity::class.java)
            startActivity(intent)
        }

        // Boto3 - Calculadora
        button3.setOnClickListener {
            val intent = Intent(this, CalculadoraActivity::class.java)
            startActivity(intent)
        }
    }
}
