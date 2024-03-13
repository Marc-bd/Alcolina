package com.projeto.alcolinaproject

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputAlcool: TextInputLayout
    private lateinit var editAlcool: TextInputEditText

    private lateinit var textInputGasoline: TextInputLayout
    private lateinit var editGasoline: TextInputEditText

    private lateinit var buttonCalc: Button
    private lateinit var resultText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initiComponentInterface()

        buttonCalc.setOnClickListener {
            calcBestPrice()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initiComponentInterface(){
        textInputAlcool = findViewById(R.id.text_input_alcool)
        editAlcool = findViewById(R.id.edit_alcool)

        textInputGasoline = findViewById(R.id.text_input_gasoline)
        editGasoline = findViewById(R.id.edit_gasoline)

        buttonCalc = findViewById(R.id.button_calc)
        resultText = findViewById(R.id.text_result)
    }

    private fun calcBestPrice() {

        val alcoolPrice = editAlcool.text.toString()
        val gasolinePrice = editGasoline.text.toString()

        val resultValidation = validateFields(alcoolPrice, gasolinePrice)

        if(resultValidation){

            //to double para transformar o valor string em double;
            val calc = alcoolPrice.toDouble() / gasolinePrice.toDouble()

            if(calc >= 0.7) {
                resultText.text = "Melhor utilizar gasolina"
            } else {
                resultText.text = "Melhor utilizar álcool"

            }
        }

    }

    private fun validateFields(alcoolPrice: String, gasolinePrice: String): Boolean {

        // colocar null para remover o erro quando o usuário digitar os valores;
        textInputAlcool.error = null
        textInputGasoline.error = null

        if(alcoolPrice.isEmpty()) {
            textInputAlcool.error = "Digite o preço do álcool"
            return false

        } else if(gasolinePrice.isEmpty()) {
            textInputGasoline.error = "Digite o preço da gasolina"
            return false
        }
            return true

    }
}