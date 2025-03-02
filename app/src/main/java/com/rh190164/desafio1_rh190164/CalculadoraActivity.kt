package com.rh190164.desafio1_rh190164

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt

class CalculadoraActivity : AppCompatActivity() {
    private lateinit var operando1EditText: EditText
    private lateinit var operando2EditText: EditText
    private lateinit var operacionRadioGroup: RadioGroup
    private lateinit var calcularButton: Button
    private lateinit var resultadoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        operando1EditText = findViewById(R.id.editTextOperando1)
        operando2EditText = findViewById(R.id.editTextOperando2)
        operacionRadioGroup = findViewById(R.id.radioGroupOperacion)
        calcularButton = findViewById(R.id.buttonCalcularOperacion)
        resultadoTextView = findViewById(R.id.textViewResultadoOperacion)

        // checkeamos el estado del radiobutton para habilitar o deshabilitar el segundo operando
        operacionRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            // si el radiobutton seleccionado es la raíz cuadrada, deshabilitar el segundo operando
            if (selectedRadioButton.text.toString() == "Raíz cuadrada") {
                operando2EditText.isEnabled = false
                operando2EditText.text.clear()
            } else {
                operando2EditText.isEnabled = true
            }
        }

        calcularButton.setOnClickListener {
            calcularOperacion()
        }
    }

    private fun calcularOperacion() {
        try {
            val operando1 = operando1EditText.text.toString().toDouble()
            // Operamos 'Operando 2' solo si esta habilitado xd
            val operando2 = if (operando2EditText.isEnabled) {
                operando2EditText.text.toString().toDouble()
            } else {
                0.0
            }

            val selectedOperationId = operacionRadioGroup.checkedRadioButtonId

            if (selectedOperationId == -1) {
                Toast.makeText(this, "Por favor seleccione una operación", Toast.LENGTH_SHORT).show()
                return
            }

            val selectedOperation = findViewById<RadioButton>(selectedOperationId)
            val resultado: Double
            val operacionTexto: String

            when (selectedOperation.text.toString()) {
                "Suma" -> {
                    resultado = operando1 + operando2
                    operacionTexto = "$operando1 + $operando2"
                }
                "Resta" -> {
                    resultado = operando1 - operando2
                    operacionTexto = "$operando1 - $operando2"
                }
                "Multiplicación" -> {
                    resultado = operando1 * operando2
                    operacionTexto = "$operando1 × $operando2"
                }
                "División" -> {
                    if (operando2 == 0.0) {
                        Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show()
                        return
                    }
                    resultado = operando1 / operando2
                    operacionTexto = "$operando1 ÷ $operando2"
                }
                "Exponente" -> {
                    resultado = operando1.pow(operando2)
                    operacionTexto = "$operando1^$operando2"
                }
                "Raíz cuadrada" -> {
                    if (operando1 < 0) {
                        Toast.makeText(this, "No se puede calcular la raíz cuadrada de un número negativo", Toast.LENGTH_SHORT).show()
                        return
                    }
                    resultado = sqrt(operando1)
                    operacionTexto = "√$operando1"
                }
                else -> {
                    Toast.makeText(this, "Operación no reconocida", Toast.LENGTH_SHORT).show()
                    return
                }
            }

            resultadoTextView.text = "Operación: $operacionTexto\nResultado: $resultado"

        } catch (e: Exception) {
            Toast.makeText(this, "Por favor ingrese valores válidos para los operandos", Toast.LENGTH_SHORT).show()
        }
    }
}