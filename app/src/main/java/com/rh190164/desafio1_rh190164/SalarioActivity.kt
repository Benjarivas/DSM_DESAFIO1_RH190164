package com.rh190164.desafio1_rh190164

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SalarioActivity : AppCompatActivity() {
    private lateinit var nombreEditText: EditText
    private lateinit var salarioBaseEditText: EditText
    private lateinit var calcularButton: Button
    private lateinit var resultadoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salario)

        nombreEditText = findViewById(R.id.editTextNombreEmpleado)
        salarioBaseEditText = findViewById(R.id.editTextSalarioBase)
        calcularButton = findViewById(R.id.buttonCalcularSalario)
        resultadoTextView = findViewById(R.id.textViewResultadoSalario)

        calcularButton.setOnClickListener {
            calcularSalario()
        }
    }

    private fun calcularSalario() {
        try {
            val nombre = nombreEditText.text.toString()
            val salarioBase = salarioBaseEditText.text.toString().toDouble()

            if (nombre.isEmpty()) {
                Toast.makeText(this, "Por favor ingrese el nombre del empleado", Toast.LENGTH_SHORT).show()
                return
            }

            if (salarioBase <= 0) {
                Toast.makeText(this, "El salario base debe ser mayor que cero", Toast.LENGTH_SHORT).show()
                return
            }

            // Deduccion del AFP
            val afp = salarioBase * 0.0725

            // Deduccion del ISSS
            val isss = (salarioBase * 0.03).coerceAtMost(30.0) // La de duduccion del ISSS no puede superar los $30

            // calculo de renta
            val renta = calcularRenta(salarioBase)

            // Calamos salario neto
            val salarioNeto = salarioBase - renta - afp - isss

            // Le damos un formato a nuestro textview para que muestre los resultados detallados
            resultadoTextView.text = """
                Empleado: $nombre
                Salario Base: $${String.format("%.2f", salarioBase)}
                
                Descuentos:
                - Renta: $${String.format("%.2f", renta)}
                - AFP (7.25%): $${String.format("%.2f", afp)}
                - ISSS (3%): $${String.format("%.2f", isss)}
                
                Salario Neto: $${String.format("%.2f", salarioNeto)}
            """.trimIndent()

        } catch (e: Exception) {
            Toast.makeText(this, "Por favor ingrese un valor válido para el salario base", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calcularRenta(salarioBase: Double): Double {
        return when {
            salarioBase < 472.00 -> 0.0 // Tramo 1 - No se retiene
            salarioBase < 895.24 -> (salarioBase - (salarioBase * 0.0725) - (salarioBase * 0.03) - 472.00) * 0.1 + 17.67 // Tramo 2 - 10% + cuota fija
            salarioBase < 2038.10 -> (salarioBase - (salarioBase * 0.0725) - (salarioBase * 0.03).coerceAtMost(30.0) - 895.24) * 0.2 + 60.00 // Tramo 3 - 20% + cuota fija
            else -> (salarioBase - (salarioBase * 0.0725) - (salarioBase * 0.03).coerceAtMost(30.0) - 2038.10) * 0.3 + 288.57 // Tramo 4 - 30% + cuota fija
        }
    }
}
