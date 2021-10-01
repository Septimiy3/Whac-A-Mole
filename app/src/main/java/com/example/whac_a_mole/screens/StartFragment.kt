package com.example.whac_a_mole.screens

import androidx.fragment.app.Fragment
import com.example.whac_a_mole.R
import com.example.whac_a_mole.replaceFragment
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment(R.layout.fragment_start) {


    override fun onStart() {
        super.onStart()
        btn_start_play.setOnClickListener {
            replaceFragment(GameFragment(),false)
        }
    }
}