package com.example.stock_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.example.stock_market.Adapter.MyAdapter
import com.example.stock_market.Database.DBhelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_main)
        hideSystemUI()

        val dBhelper: DBhelper = DBhelper(this)
        var list = dBhelper.getAllOperations()
        var listView:ListView = list_itens
        listView.adapter = MyAdapter(this, R.layout.row,list)

        var button:Button = findViewById(R.id.button_add_card)
        var refresh:Button = findViewById(R.id.button_update_card)

        refresh.setOnClickListener(){
            var list = dBhelper.getAllOperations()
            var listView:ListView = findViewById(R.id.list_itens)
            listView.adapter = MyAdapter(this, R.layout.row,list)
        }
        button.setOnClickListener(){
            intent = Intent(this, Form_Actiivity ::class.java)
            startActivity(intent)
        }



    }


}
