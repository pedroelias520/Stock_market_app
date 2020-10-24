package com.example.stock_market

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.stock_market.Adapter.item_added

class Confirm_Screen : AppCompatActivity(){
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

    //id = 0,
    //name = name,
    //type = tipo_operacao,
    //qnt = quantidade.toInt(),
    //price = preco.toFloat(),
    //corretage_price = cotacao.toFloat(),
    //taxas = taxas,
    //valorCompra = valorCompra,
    //valorNegociacao = ValorNegociacao

    override fun onCreate(savedInstanceState: Bundle?) {
        hideSystemUI()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.confirm_screen)

        var button:Button = findViewById(R.id.button)
        var nome:TextView = findViewById(R.id.nome_text)
        var custo_unitario:TextView = findViewById(R.id.cust_text)
        var quantidade: TextView = findViewById(R.id.qtn_text)
        var cust_corretagem: TextView = findViewById(R.id.cust_corretagem_text)
        var cust_liquid:TextView = findViewById(R.id.cust_liquidacao_text)
        var cust_negoc:TextView = findViewById(R.id.cust_negociation_text)

        button.setOnClickListener(){
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        nome.text = item_added.name
        custo_unitario.text = "${item_added.price.toString()}R$ de valor"
        cust_corretagem.text = "${item_added.corretage_price.toString()}R$ de corretagem"
        quantidade.text = "${item_added.qnt.toString()} unidade(s)"
        cust_liquid.text = "${item_added.taxas.toString()}R$ de taxas"
        cust_negoc.text = "${item_added.valorNegociacao.toString()}R$ de negociacao"


    }
}