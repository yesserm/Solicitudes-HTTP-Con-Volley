package com.yesser.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_clima.*
import java.lang.Exception

class Clima : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clima)

        if (Network.hayRed(this))
        {
            Toast.makeText(this,"hay red",Toast.LENGTH_LONG).show()
            solicitudHTTPVolley("https://api.openweathermap.org/data/2.5/weather?id=3616253&appid=dfdf1c84757769604f2e9a3082428c27&lang=es&units=metric")
        } else {
            Toast.makeText(this,"No hay red",Toast.LENGTH_LONG).show()
        }
    }

    fun solicitudHTTPVolley(url:String)
    {

        val queue = Volley.newRequestQueue(this)
        val solicitud = StringRequest(Request.Method.GET,url,Response.Listener<String> {
            response ->
            try {
                Log.d("ResultadoVolley",response)
                val gson = Gson()
                val ciudad = gson.fromJson(response, Ciudad::class.java)
                tvGrados.text = ciudad.main!!.temp.toString() + "°"
                tvCiudad.text = ciudad.name
                tvDescripcion.text = ciudad.weather!!.get(0).description
            } catch (e:Exception){

            }
        }, Response.ErrorListener {
            Log.e("ErrorVolley","ERROR EXTRA;o")
        })
        queue.add(solicitud)
    }
}