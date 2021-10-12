package com.example.whac_a_mole.screens


import android.content.Context
import android.content.SharedPreferences
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.whac_a_mole.APP_ACTIVITY
import com.example.whac_a_mole.R
import com.example.whac_a_mole.replaceFragment
import kotlinx.android.synthetic.main.fragment_game.*
import kotlin.random.Random
import android.media.AudioManager
import android.media.MediaPlayer


class GameFragment : Fragment(R.layout.fragment_game) {

    var score = 0
    var textTest = "20"
    val LOG_TAG = "SAVE_AND_LOAD"
    var sp: SoundPool? = null
    var hm: HashMap<Int, Int>? = null
    var currStreamId = 0
    var media : MediaPlayer? = null



    var sPref: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timerGame()
        initSoundPool()

    }

    override fun onStart() {
        super.onStart()
        gameStart()
//        setBlackHole()
        playMusic()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun timerGame() {
        val timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                view_timer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                Stopped()
                replaceFragment(ScoreFragment(), false)
                sp?.stop(currStreamId)
            }
        }
        timer.start()
    }

    fun saveScore() {
        sPref = APP_ACTIVITY.getSharedPreferences("pref", Context.MODE_PRIVATE)
        var ed: SharedPreferences.Editor = sPref!!.edit()
        ed.putString("SAVE_TEXT", txt_scrore_subtitle.text.toString())
        ed.commit()
    }


    fun gameStart() {
        val ha = Handler()
        val he = Handler()
        ha.postDelayed(object : Runnable {
            override fun run() {
                //call functionЛ
                setBlackHole()
                ha.postDelayed(this, 500)
            }
        }, 500)
        he.postDelayed(object : Runnable {
            override fun run() {
                //call function
                setMoleImage()
                ha.postDelayed(this, 1000)
            }
        }, 0)


    }

    fun setBlackHole() {
        var listImage = mutableListOf(im1, im2, im3, im4, im5, im6, im7, im8, im9)
        if (isAdded() && activity != null) {
            for (i in listImage.indices) {
                var blackhole = listImage[i]
                blackhole.setImageResource(R.drawable.ic_hole1)
            }
        }
    }

    fun setMoleImage() {
        var listImage = mutableListOf(im1, im2, im3, im4, im5, im6, im7, im8, im9)
        val random = Random
        var setImageMoleRandom = listImage[random.nextInt(listImage.size)]
        if (isAdded() && activity != null) {
            setImageMoleRandom.setImageResource(R.drawable.ic_mole)
            for (i in listImage.indices){
                var setImage = listImage[i]
                setImage.setOnClickListener {
                    if (setImage == setImageMoleRandom ){
                        txt_scrore_subtitle.text = score.toString()
                        score = score + 1
                        saveScore()
                        Log.d(LOG_TAG, "GO GO POWER RANGERS")
//                        playFonMusic(1,0)
                    }
                }
            }
        }
    }

    fun playSounPool(sound:Int, loop:Int){
        val am = APP_ACTIVITY
            .getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val streamVolumeCurrent = am
            .getStreamVolume(AudioManager.STREAM_MUSIC).toFloat()
        val streamVolumeMax = am
            .getStreamMaxVolume(AudioManager.STREAM_MUSIC).toFloat()
        val volume = streamVolumeCurrent / streamVolumeMax
        currStreamId = sp!!.play(hm?.get(sound)!!, volume, volume, 1, loop, 1.0f)
    }

    fun playMusic(){
        media = MediaPlayer.create(APP_ACTIVITY,R.raw.game)
        media?.start()
    }
    fun initSoundPool(){
        sp = SoundPool(4, AudioManager.STREAM_MUSIC, 0)
        hm = HashMap()
        hm!!.put(1, sp!!.load(APP_ACTIVITY,R.raw.game,1))
    }
    fun Stopped() {
        return setBlackHole()
        return setMoleImage()
    }

    override fun onStop() {
        super.onStop()
        saveScore()
        media?.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        media?.release()
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