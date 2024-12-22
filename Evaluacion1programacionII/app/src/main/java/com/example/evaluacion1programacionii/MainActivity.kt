package com.example.evaluacion1programacionii

import android.os.Bundle
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.evaluacion1programacionii.modelo.CuentaMesa
import com.example.evaluacion1programacionii.modelo.ItemMenu
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var cant1EditText: EditText
    private lateinit var cant2EditText: EditText
    private lateinit var montoComidaTextView: TextView
    private lateinit var montoPropinaTextView: TextView
    private lateinit var montoTotalTextView: TextView
    private lateinit var switchPropina: Switch
    private lateinit var plato1TotalTextView: TextView
    private lateinit var plato2TotalTextView: TextView

    private val cuentaMesa = CuentaMesa(1)
    private val pastelDeChoclo = ItemMenu("Pastel de Choclo", "12000")
    private val cazuela = ItemMenu("Cazuela", "10000")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        cant1EditText = findViewById(R.id.cant1)
        cant2EditText = findViewById(R.id.cant2)
        montoComidaTextView = findViewById(R.id.montoComida)
        montoPropinaTextView = findViewById(R.id.montoPropina)
        montoTotalTextView = findViewById(R.id.montoTotal)
        switchPropina = findViewById(R.id.switchpropina)
        plato1TotalTextView = findViewById(R.id.plato1Total)
        plato2TotalTextView = findViewById(R.id.plato2Total)


        switchPropina.setOnCheckedChangeListener { _, isChecked ->
            cuentaMesa.aceptaPropina = isChecked
            actualizarMontos()
        }


        cant1EditText.addTextChangedListener { actualizarMontos() }
        cant2EditText.addTextChangedListener { actualizarMontos() }
    }

    private fun actualizarMontos() {

        val cant1 = cant1EditText.text.toString().toIntOrNull() ?: 0
        val cant2 = cant2EditText.text.toString().toIntOrNull() ?: 0


        cuentaMesa.agregarItem(pastelDeChoclo, 0)
        cuentaMesa.agregarItem(cazuela, 0)

        cuentaMesa.items.clear()


        if (cant1 > 0) cuentaMesa.agregarItem(pastelDeChoclo, cant1)
        if (cant2 > 0) cuentaMesa.agregarItem(cazuela, cant2)


        val montoComida = cuentaMesa.calcularTotalSinPropina()
        val montoPropina = cuentaMesa.calcularPropina()
        val montoTotal = cuentaMesa.calcularTotalConPropina()


        plato1TotalTextView.text = "$${formatCurrency(cant1 * pastelDeChoclo.precio.toInt())}"
        plato2TotalTextView.text = "$${formatCurrency(cant2 * cazuela.precio.toInt())}"
        montoComidaTextView.text = "$${formatCurrency(montoComida)}"
        montoPropinaTextView.text = "$${formatCurrency(montoPropina)}"
        montoTotalTextView.text = "$${formatCurrency(montoTotal)}"
    }

    private fun formatCurrency(amount: Int): String {
        return java.text.NumberFormat.getNumberInstance(Locale("es", "CL")).format(amount)
    }
}
