package com.example.a20151148060170.jogodavelha

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.ViewAnimator
import android.app.AlarmManager
import android.content.Context.ALARM_SERVICE
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.media.AudioManager
import android.media.SoundPool
import android.media.AudioAttributes
import android.media.MediaPlayer


class Main2Activity : AppCompatActivity() {

    val jogadorUm = arrayListOf<Int>()
    val jogadorDois = arrayListOf<Int>()
    var jogadorDaVez = 1
    var placar1 = 0
    var placar2 = 0


    val linha1 = listOf(1,2,3)
    val linha2 = listOf(4,5,6)
    val linha3 = listOf(7,8,9)

    val coluna1 = listOf(1,4,7)
    val coluna2 = listOf(2,5,8)
    val coluna3 = listOf(3,6,9)

    val diag1 = listOf(1,5,9)
    val diag2 = listOf(3,5,7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val argumentos:Bundle=intent.extras

        val jogador1:String=argumentos.getString("jogador1")
        val jogador2:String=argumentos.getString("jogador2")

        textViewJogador1.text=" $jogador1"
        textViewJogador2.text=" $jogador2"
    }



    fun bntClick(view: View) = play(view.tag.toString().toInt(), view as Button)

    fun play(posicao: Int, btnSelecionado: Button) {

        if (jogadorDaVez == 1) {
            btnSelecionado.text = "X"

            jogadorUm.add(posicao)
            jogadorDaVez = 2
        } else {
            btnSelecionado.text = "O"

            jogadorDois.add(posicao)
            jogadorDaVez = 1
        }

        btnSelecionado.isClickable = false

        checarResultado(jogadorUm, jogadorDois)

    }

    fun trava(travar:Boolean){

        button.isClickable = travar
        button2.isClickable = travar
        button3.isClickable = travar
        button4.isClickable = travar
        button5.isClickable = travar
        button6.isClickable = travar
        button7.isClickable = travar
        button8.isClickable = travar
        button9.isClickable = travar
    }



    fun checarResultado(jogadorUm: ArrayList<Int>, jogadorDois: ArrayList<Int> ){


        if (jogadorUm.containsAll(linha1) || jogadorUm.containsAll(linha2) || jogadorUm.containsAll(linha3) || jogadorUm.containsAll(coluna1) || jogadorUm.containsAll(coluna2) || jogadorUm.containsAll(coluna3) ||
                jogadorUm.containsAll(diag1) || jogadorUm.containsAll(diag2)){

            Toast.makeText(this, "Parabéns! Jogador 1 venceu.", Toast.LENGTH_LONG).show()
            trava(false)
            placar1++
            textView.text = "$placar1"
            sound()
            limpar(textView)

        } else if(jogadorDois.containsAll(linha1) ||jogadorDois.containsAll(linha2) || jogadorDois.containsAll(linha3) || jogadorDois.containsAll(coluna1) || jogadorDois.containsAll(coluna2) || jogadorDois.containsAll(coluna3) ||
                jogadorDois.containsAll(diag1) || jogadorDois.containsAll(diag2)){

            Toast.makeText(this, "Parabéns! Jogador 2 venceu.", Toast.LENGTH_LONG).show()
            trava(false)
            placar2++
            textView2.text = "$placar2"
            sound()
            limpar(textView)
        } else if (jogadorUm.size + jogadorDois.size == 9){
            Toast.makeText(this, "Deu Velha!", Toast.LENGTH_LONG).show()
            limpar(textView)

        }

    }



    fun limpar(view: View) {
        jogadorUm.clear()
        jogadorDois.clear()
        button.text=""
        button2.text=""
        button3.text=""
        button4.text=""
        button5.text=""
        button6.text=""
        button7.text=""
        button8.text=""
        button9.text=""

        trava(true)


    }




     fun restartApp(view: View) {
        val intent = Intent(applicationContext, R.layout.activity_main::class.java)
        val mPendingIntentId = 2
        val mPendingIntent = PendingIntent.getActivity(applicationContext, mPendingIntentId, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        val mgr = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent)
        System.exit(0)
    }

    fun sound(){
        var mp = MediaPlayer.create(getApplicationContext(), R.raw.vitoria)
        mp.start()
    }
































}
