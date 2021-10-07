package com.example.whac_a_mole.screens


import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.fragment.app.Fragment
import com.example.whac_a_mole.R
import com.example.whac_a_mole.replaceFragment
import kotlinx.android.synthetic.main.fragment_game.*
import kotlin.random.Random
import android.content.SharedPreferences


class GameFragment : Fragment(R.layout.fragment_game) {

    var score = 0
    var textTest = "20"

    var sPref: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameStart()
        timerGame()
    }

    fun timerGame() {
        val timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                view_timer.text = (millisUntilFinished / 1000).toString()
            }
            override fun onFinish() {
                Stopped()
                replaceFragment(ScoreFragment(), false)
            }
        }
        timer.start()
    }

    fun saveScore() {
        sPref = this.requireActivity()
            .getSharedPreferences("pref", Context.MODE_PRIVATE)
        var ed: SharedPreferences.Editor = sPref!!.edit()
        ed.putString("SAVE_TEXT",txt_scrore_subtitle.text.toString())
        ed.commit()
    }


    fun gameStart() {
        val ha = Handler()
        val he = Handler()
        ha.postDelayed(object : Runnable {
            override fun run() {
                //call function
                MoleHole()
                ha.postDelayed(this, 500)
            }
        }, 0)
        he.postDelayed(object : Runnable {
            override fun run() {
                //call function
                HoleBlack()
                ha.postDelayed(this, 1000)
            }
        }, 0)
    }

    fun HoleBlack() {
        var holeList = mutableListOf(im1, im2, im3, im4, im5, im6, im7, im8, im9)
        val random = Random
        var redHole = holeList[random.nextInt(holeList.size)]
        if (isAdded() && activity != null) {
            redHole.setImageDrawable(resources.getDrawable(R.drawable.ic_mole))
            redHole.setOnClickListener {
                txt_scrore_subtitle.text = score.toString()
                score = score + 1
                saveScore()
            }
        }
    }

    fun MoleHole() {
        var holeList = mutableListOf(im1, im2, im3, im4, im5, im6, im7, im8, im9)
        val random = Random
        val redHole = holeList[random.nextInt(holeList.size)]
        if (isAdded() && activity != null) {
            for (i in 0 until holeList.size) {
                var blackhole = holeList[i]
                blackhole!!.setImageDrawable(resources.getDrawable(R.drawable.ic_hole1))
            }

        }
    }

    fun Stopped() {
        return MoleHole()
        return HoleBlack()
    }

    override fun onStop() {
        super.onStop()
        saveScore()
    }
}


//fun Game() {
//    var holeList = mutableListOf(im1, im2, im3, im4, im5, im6, im7, im8, im9)
//    val random = Random
//    val score: Int = 0
//    view_timer.text = timeTxt.toString()
//    fixedRateTimer("timer", false, 600, 700) {
//        requireActivity().runOnUiThread {
//            var redHole = holeList[random.nextInt(holeList.size)]
//
//            redHole.setImageDrawable(resources.getDrawable(R.drawable.ic_mole))
//        }
//    }
//    fixedRateTimer("timer", false, 0, 800) {
//        requireActivity().runOnUiThread {
//            for (i in 0 until holeList.size) {
//                var blackhole = holeList[i]
//                blackhole!!.setImageDrawable(resources.getDrawable(R.drawable.ic_hole1))
//            }
//        }
//    }
//    val timer = object : CountDownTimer(30000, 1000) {
//        override fun onTick(millisUntilFinished: Long) {
//            timeTxt - 1
//
//        }
//
//        override fun onFinish() {
//            replaceFragment(ScoreFragment(), false)
//        }
//    }
//    timer.start()
//}