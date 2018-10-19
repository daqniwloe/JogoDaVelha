package com.example.a20151148060170.jogodavelha

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "Start", Toast.LENGTH_LONG).show()
    }

    fun jogar(view: View){
        val jogador1:String=editTextJogador1.text.toString()
        val jogador2:String=editTextJogador2.text.toString()

            val intentCall: Intent =Intent(this,Main2Activity::class.java)
            val parametros:Bundle = Bundle()
            parametros.putString("jogador1",jogador1)
            parametros.putString("jogador2",jogador2)
            intentCall.putExtras(parametros)
            startActivity(intentCall)

    }
}
