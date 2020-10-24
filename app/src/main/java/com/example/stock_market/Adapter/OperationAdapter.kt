package com.example.stock_market.Adapter

import com.example.stock_market.Model.Operation_model
import com.example.stock_market.R
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.stock_market.Database.DBhelper

class MyAdapter(var mCtx:Context,var resources:Int,var items:List<Operation_model>):ArrayAdapter<Operation_model>(mCtx,resources,items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(resources,null)

        val NomeView:TextView = view.findViewById(R.id.qnt_text)
        val PrecoView:TextView = view.findViewById(R.id.preco_text)
        val TipoView:TextView = view.findViewById(R.id.cotacao_text)
        val CotacaoView:TextView = view.findViewById(R.id.Important)
        val ValorCompra:TextView = view.findViewById(R.id.valor_compra)

        val mItem:Operation_model = items[position]
        NomeView.text = mItem.name
        PrecoView.text = mItem.price.toString()
        TipoView.text = mItem.type
        ValorCompra.text = mItem.valorCompra.toString()

        when(mItem.type){
            "Compra" -> {
                TipoView.text = "Compra"
                TipoView.setTextColor(Color.GREEN)
                CotacaoView.text = "${mItem.qnt.toString()} unidades compradas"
                CotacaoView.setTextColor(Color.GREEN)
            }

            "Venda" -> {
                TipoView.text = "Venda"
                TipoView.setTextColor(Color.RED)
                CotacaoView.text = "${mItem.qnt.toString()} unidades vendidas"
                CotacaoView.setTextColor(Color.RED)
            }
        }
        return view
    }
}
var item_added: Operation_model = Operation_model(id = 0,
    name = "Name",
    type = "Type",
    qnt = 0,
    price = (0.0),
    corretage_price = (0.0),
    taxas = (0.0),
    valorCompra = (0.0),
    valorNegociacao = (0.0)
)
