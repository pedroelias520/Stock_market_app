package com.example.stock_market

import android.widget.Toast
import java.math.RoundingMode
import java.text.DecimalFormat

class Controller (){

    fun ValorCompra(quantidade:Int,preco:Float): Double {
        print("===================================================")
        print(quantidade)
        print(preco)
        var resultado = (preco * quantidade)
        val df = DecimalFormat("##.#")
        df.roundingMode = RoundingMode.CEILING
        return df.format(resultado).toDouble()
    }

    fun CalcularTaxas(valorCompra:Double,cotacao:Float): Double {
        val emu = (valorCompra * 0.003125)
        val liquidCust = (valorCompra * 0.0275)
        return ((emu + liquidCust + cotacao))
    }

    fun valorNegociacao(tipo:String,valorCompra: Double,taxas:Double): Double{
        if (tipo == "Compra"){
            print("Operação de compra")
            return (valorCompra + taxas)
        }
        else{
            print("Operação de venda")
            return (valorCompra - taxas)
        }

    }
}