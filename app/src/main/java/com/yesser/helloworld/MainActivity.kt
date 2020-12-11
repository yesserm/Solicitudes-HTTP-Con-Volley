package com.yesser.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun countUp(view: View) {
       /* var num = show_count.text.toString().toInt()
        if (view != null) {
            num++
            show_count.text = num.toString()
        } else {
            show_count.text = "0"
        }*/
        var texto:TextView = findViewById(R.id.show_count)
        var num = texto.text.toString().toInt()
        num++
        texto.text = num.toString()
    }
    fun showToast(view: View) {
        if (view != null) {
            Toast.makeText(this,"Hola soy un toast",Toast.LENGTH_SHORT).show()
        }
        val intent = Intent(this, Clima::class.java)
        startActivity(intent)
    }
}