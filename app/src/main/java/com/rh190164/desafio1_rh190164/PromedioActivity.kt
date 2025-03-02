package com.rh190164.desafio1_rh190164

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PromedioActivity : AppCompatActivity() {
    private lateinit var nombreEditText: EditText
    private lateinit var nota1EditText: EditText
    private lateinit var nota2EditText: EditText
    private lateinit var nota3EditText: EditText
    private lateinit var nota4EditText: EditText
    private lateinit var nota5EditText: EditText
    private lateinit var calcularButton: Button
    private lateinit var resultadoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promedio)

        nombreEditText = findViewById(R.id.editTextNombre)
        nota1EditText = findViewById(R.id.editTextNota1)
        nota2EditText = findViewById(R.id.editTextNota2)
        nota3EditText = findViewById(R.id.editTextNota3)
        nota4EditText = findViewById(R.id.editTextNota4)
        nota5EditText = findViewById(R.id.editTextNota5)
        calcularButton = findViewById(R.id.buttonCalcular)
        resultadoTextView = findViewById(R.id.textViewResultado)

        calcularButton.setOnClickListener {
            calcularPromedio()
        }
    }

    private fun calcularPromedio() {
        try {
            val nombre = nombreEditText.text.toString()

            val nota1 = nota1EditText.text.toString().toFloat()
            val nota2 = nota2EditText.text.toString().toFloat()
            val nota3 = nota3EditText.text.toString().toFloat()
            val nota4 = nota4EditText.text.toString().toFloat()
            val nota5 = nota5EditText.text.toString().toFloat()

            if (nombre.isEmpty()) {
                Toast.makeText(this, "Por favor ingrese el nombre del estudiante", Toast.LENGTH_SHORT).show()
                return
            }

            // Se valida que toda nota proporcionada sea =mas de 0 y =menos de 10
            val notas = listOf(nota1, nota2, nota3, nota4, nota5)
            for (nota in notas) {
                if (nota < 0 || nota > 10) {
                    Toast.makeText(this, "Las notas deben estar entre 0 y 10", Toast.LENGTH_SHORT).show()
                    return
                }
            }

            // Sacamos promedio
            val promedio = (nota1 * 0.15f) + (nota2 * 0.15f) + (nota3 * 0.20f) + (nota4 * 0.25f) + (nota5 * 0.25f)

            // Como la UDB pide 6 para aprobar, con seis se deja xd
            val resultado = if (promedio >= 6) "APROBÓ" else "REPROBÓ"

            // Mostramos el resultado en un text view
            resultadoTextView.text = "Estudiante: $nombre\nPromedio: %.2f\nResultado: $resultado".format(promedio)

        } catch (e: Exception) {
            Toast.makeText(this, "Por favor ingrese valores válidos para todas las notas", Toast.LENGTH_SHORT).show()
        }
    }
}
