package com.example.whac_a_mole

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.whac_a_mole.screens.StartFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        APP_ACTIVITY = this

        initFields()
    }

    fun initFields(){
        replaceFragment(StartFragment(),false)
    }
}