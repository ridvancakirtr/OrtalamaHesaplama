package com.example.ortalamahesaplama

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*

class ActivitySplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var asagidanGelenButon = AnimationUtils.loadAnimation(this,R.anim.asagidangelenbutton)
        animbutton.animation=asagidanGelenButon

        var assagiyagidenbuton = AnimationUtils.loadAnimation(this,R.anim.assagiyagidenbuton)
        animbutton.animation=asagidanGelenButon

        var yukaridanassainenresim = AnimationUtils.loadAnimation(this,R.anim.yukaridanassagiresim)
        imageView.animation=yukaridanassainenresim

        var assagidanyukariresim = AnimationUtils.loadAnimation(this,R.anim.assagidanyukariresim)
        imageView.animation=yukaridanassainenresim

        animbutton.setOnClickListener {
            animbutton.startAnimation(assagiyagidenbuton)
            imageView.startAnimation(assagidanyukariresim)

            object : CountDownTimer(1000,1000){
                override fun onFinish() {
                    var intent=Intent(this@ActivitySplash,MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onTick(millisUntilFinished: Long) {

                }

            }.start()


        }
    }
}
