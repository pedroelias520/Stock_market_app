package com.example.stock_market

class Controller (){

    fun ValorCompra(quantidade:Float,preco:Float): Float {
        print("===================================================")
        print(quantidade)
        print(preco)
        print("===================================================")
        return (preco * quantidade)
    }

    fun CalcularTaxas(valorCompra:Float,cotacao:Float): Float {
        val emu = (valorCompra * 0.003125)
        val liquidCust = (valorCompra * 0.0275)
        return ((emu + liquidCust + cotacao).toFloat())
    }

    fun valorNegociacao(tipo:String,valorCompra: Float,taxas:Float): Float{
        if (tipo == "Venda"){
            print("Operação de venda")
            return (valorCompra + taxas)
        }
        else{
            print("Operação de compra")
            return (valorCompra - taxas)
        }

    }
}