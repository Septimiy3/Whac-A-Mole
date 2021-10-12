package com.example.whac_a_mole.screens

import android.media.AudioManager
import android.media.MediaPlayer
import com.example.whac_a_mole.APP_ACTIVITY
import com.example.whac_a_mole.R

class Media {

    private var mMedia: MediaPlayer? = null

    fun startMusic(){
        if (mMedia == null){
            mMedia = MediaPlayer.create(APP_ACTIVITY,R.raw.game)
        }
        mMedia?.start()
    }
    fun stopMusic(){
        mMedia?.stop()
    }
    fun releaseMusic(){
        mMedia?.release()
    }
}