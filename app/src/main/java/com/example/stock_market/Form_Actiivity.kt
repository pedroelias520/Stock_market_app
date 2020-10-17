package com.example.stock_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.*
import com.example.stock_market.Database.DBhelper
import com.example.stock_market.Model.MaskEditUtil
import com.example.stock_market.Model.Operation_model
import kotlinx.android.synthetic.main.row.*

class Form_Actiivity : AppCompatActivity() {

    private fun hideSystemUI() {
        // Ativa o modo imersivo de tela
        // Para o modo "recuar", remova SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Esconde barra de status e barra de navegação
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN

                )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_activity)
        hideSystemUI()

        var nome_plain: EditText = findViewById(R.id.nome_plain)
        var preco_plain: EditText = findViewById(R.id.preco_plain)
        var cotacao_plain: EditText = findViewById(R.id.cotacao_plain)
        var quantidade_plain: EditText = findViewById(R.id.quantidade_plain)
        preco_plain.addTextChangedListener(MaskEditUtil.mask(preco_plain, "##.##"))
        cotacao_plain.addTextChangedListener(MaskEditUtil.mask(cotacao_plain, "##.##"))

        var criar_button: Button = findViewById(R.id.criar_button)
        var radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        var radio_venda: RadioButton = findViewById(R.id.venda_radio)
        var radio_compra: RadioButton = findViewById(R.id.compra_radio)
        var back_button: Button = findViewById(R.id.back_button)
        var tipo_operacao: String = ""

        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { radioGroup, CheckId ->
                val radio: RadioButton = findViewById(CheckId)
                tipo_operacao = radio.text.toString()
            }
        )
        back_button.setOnClickListener() {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        criar_button.setOnClickListener() {
            val quantidade = quantidade_plain.text.toString()
            val preco = preco_plain.text.toString()
            val cotacao = cotacao_plain.text.toString()
            val name = nome_plain.text.toString()

            val valorCompra = Controller().ValorCompra(quantidade = quantidade.toInt(), preco = preco.toFloat())
            val taxas = Controller().CalcularTaxas(valorCompra, cotacao = cotacao.toFloat())
            val ValorNegociacao = Controller().valorNegociacao(tipo_operacao, valorCompra, taxas)

            if (radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(applicationContext, "Selecione o tipo de venda", Toast.LENGTH_LONG).show()
            } else {
                val operacao = Operation_model(
                    id = 0,
                    name = name,
                    type = tipo_operacao,
                    qnt = quantidade.toInt(),
                    price = preco.toFloat(),
                    corretage_price = cotacao.toFloat(),
                    taxas = taxas,
                    valorCompra = valorCompra,
                    valorNegociacao = ValorNegociacao
                )
                DBhelper(context = applicationContext).addReminder(operacao)
            }


        }

    }
}