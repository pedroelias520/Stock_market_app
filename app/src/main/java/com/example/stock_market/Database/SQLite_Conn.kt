package com.example.stock_market.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Path
import com.example.stock_market.Model.Operation_model

class DBhelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VER){

    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "actions.db"

        //Table
        private val TABLE_NAME= "acoes"
        private val COL_ID = "Id"
        private val COL_CUSTO = "Nome"
        private val COL_UNIDADES="Hora"
        private val COL_CUSTO_CORRETAGEM ="Descricao"
        private val COL_TIPO="Importancia"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE"+TABLE_NAME+"("+COL_ID+"INT PRIMARY KEY AUTO_INCREMENT,"+ COL_TIPO+" TIPO ,"+ COL_UNIDADES+" QUANTIDADE ,"+ COL_CUSTO_CORRETAGEM+" CUSTO CORRETAGEM ,"+ COL_CUSTO+" CUSTO)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    val allReminds:MutableList<Operation_model>
        get() {
            val lstReminds = ArrayList<Operation_model>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db:SQLiteDatabase = this.writableDatabase
            val cursor = db.rawQuery(selectQuery,null)
            if(cursor.moveToFirst())
            {
                do{
                    val id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    var custo = cursor.getFloat(cursor.getColumnIndex(COL_CUSTO))
                    var quantidade = cursor.getInt(cursor.getColumnIndex(COL_UNIDADES))
                    var custo_corretagem = cursor.getFloat(cursor.getColumnIndex(COL_CUSTO_CORRETAGEM))
                    var tipo = cursor.getString(cursor.getColumnIndex(COL_TIPO))
                    var operation = Operation_model(id,tipo,quantidade,custo,custo_corretagem)
                    allReminds.add(operation)
                }while (cursor.moveToNext())
            }
            db.close()
            return allReminds
        }

    fun addReminder(Operation: Operation_model)
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID,Operation.id)
        values.put(COL_TIPO,Operation.type)
        values.put(COL_UNIDADES,Operation.qnt)
        values.put(COL_CUSTO_CORRETAGEM,Operation.corretage_price)
        values.put(COL_CUSTO,Operation.price)

        db.insert(TABLE_NAME,null,values)
        db.close()
    }
    fun updateReminder(Operation: Operation_model) : Boolean
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID,Operation.id)
        values.put(COL_TIPO,Operation.type)
        values.put(COL_UNIDADES,Operation.qnt)
        values.put(COL_CUSTO_CORRETAGEM,Operation.corretage_price)
        values.put(COL_CUSTO,Operation.price)

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

