package com.example.stock_market.Adapter

import com.example.stock_market.Model.Operation_model
import com.example.stock_market.R
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyAdapter(var mCtx:Context,var resources:Int,var items:List<Operation_model>):ArrayAdapter<Operation_model>(mCtx,resources,items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(resources,null)

        val NomeView:TextView = view.findViewById(R.id.qnt_text)
        val PrecoView:TextView = view.findViewById(R.id.preco_text)
        val TipoView:TextView = view.findViewById(R.id.cotacao_text)
        val CotacaoView:TextView = view.findViewById(R.id.Important)

        val mItem:Operation_model = items[position]
        NomeView.text = mItem.name
        PrecoView.text = mItem.price.toString()
        TipoView.text = mItem.type
        CotacaoView.text = mItem.qnt.toString()

        when(mItem.type){
            "Compra" -> {
                TipoView.text = "Compra"
                TipoView.setTextColor(Color.GREEN)
            }

            "Venda" -> {
                TipoView.text = "Venda"
                TipoView.setTextColor(Color.RED)
            }
        }

        return view
    }
}

