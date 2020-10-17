package com.example.stock_market.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Path
import com.example.stock_market.Model.Operation_model
import java.lang.Exception

class DBhelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VER){

    companion object {
        private val DATABASE_VER = 3
        private val DATABASE_NAME = "actions.db"

        //Table
        private val TABLE_NAME = "acoes"
        private val COL_NAME = "name"
        private val COL_ID = "id"
        private val COL_CUSTO = "custo"
        private val COL_UNIDADES="unidades"
        private val COL_CUSTO_CORRETAGEM ="custo_corretagem"
        private val COL_TIPO="tipo"
        private val COL_TAXAS="taxas"
        private val COL_VALOR_NEGOCIACAO="valor_negociacao"
        private val COL_VALOR_COMPRA="valor_compra"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE "+TABLE_NAME+" ("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ COL_NAME+" TEXT ,"+ COL_TIPO+" TEXT ,"+ COL_UNIDADES+" INT ,"+ COL_CUSTO_CORRETAGEM+" DOUBLE ,"+ COL_CUSTO+" DOUBLE, "+ COL_TAXAS+" DOUBLE, "+ COL_VALOR_NEGOCIACAO+" DOUBLE, "+ COL_VALOR_COMPRA+" DOUBLE)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }
    fun getAllOperations(): ArrayList<Operation_model> {
        val OperationList = arrayListOf<Operation_model>()
        val db = readableDatabase

        var cursor = db.rawQuery("SELECT * FROM ${TABLE_NAME}", null)

        with(cursor) {
            while(moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                val name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                var custo = cursor.getFloat(cursor.getColumnIndex(COL_CUSTO))
                var quantidade = cursor.getInt(cursor.getColumnIndex(COL_UNIDADES))
                var custo_corretagem = cursor.getFloat(cursor.getColumnIndex(COL_CUSTO_CORRETAGEM))
                var tipo = cursor.getString(cursor.getColumnIndex(COL_TIPO))
                var valor_compra = cursor.getFloat(cursor.getColumnIndex(COL_VALOR_COMPRA))
                var taxas = cursor.getFloat(cursor.getColumnIndex(COL_TAXAS))
                var valor_negociacao = cursor.getFloat(cursor.getColumnIndex(COL_VALOR_NEGOCIACAO))


                var operation = Operation_model(
                    id,
                    name,
                    tipo,
                    quantidade,
                    custo,
                    custo_corretagem,
                    valor_compra,
                    taxas,
                    valor_negociacao
                )
                OperationList.add(operation)
            }
        }

        return OperationList
    }

    fun addReminder(Operation: Operation_model)
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NAME,Operation.name)
        values.put(COL_TIPO,Operation.type)
        values.put(COL_UNIDADES,Operation.qnt)
        values.put(COL_CUSTO_CORRETAGEM,Operation.corretage_price)
        values.put(COL_CUSTO,Operation.price)
        values.put(COL_TAXAS,Operation.taxas)
        values.put(COL_VALOR_NEGOCIACAO,Operation.valorNegociacao)
        values.put(COL_VALOR_COMPRA,Operation.valorCompra)

        db.insert(TABLE_NAME,null,values)
        db.close()
    }
    fun updateReminder(Operation: Operation_model) : Boolean
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NAME,Operation.name)
        values.put(COL_ID,Operation.id)
        values.put(COL_TIPO,Operation.type)
        values.put(COL_UNIDADES,Operation.qnt)
        values.put(COL_CUSTO_CORRETAGEM,Operation.corretage_price)
        values.put(COL_CUSTO,Operation.price)
        values.put(COL_TAXAS,Operation.taxas)
        values.put(COL_VALOR_NEGOCIACAO,Operation.valorNegociacao)
        values.put(COL_VALOR_COMPRA,Operation.valorCompra)

        val _sucess = db.update(TABLE_NAME,values,"$COL_ID=?", arrayOf(Operation.id.toString()))
        db.close()
        return Integer.parseInt("$_sucess") != 1

    }
    fun deleteReminder(_id: Int) : Boolean
    {
        val db = this.writableDatabase
        val _sucess = db.delete(TABLE_NAME, COL_ID +"=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_sucess") != 1
    }

}

