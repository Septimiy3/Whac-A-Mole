package com.example.whac_a_mole.screens

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import com.example.whac_a_mole.R
import com.example.whac_a_mole.replaceFragment
import kotlinx.android.synthetic.main.fragment_score.*
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_game.*


class ScoreFragment : Fragment(R.layout.fragment_score) {

    val MY_SHARED_PREFERENCES = "SAVE_TEXT"
    var sPref: SharedPreferences? = null

    override fun onStart() {
        super.onStart()
        getTextScore()
        btn_menu.setOnClickListener { replaceFragment(StartFragment(),false) }
        btn_try_again.setOnClickListener { replaceFragment(GameFragment(),false) }
        addList()
    }
    fun getTextScore(){
        sPref = this.requireActivity()
            .getSharedPreferences("pref", Context.MODE_PRIVATE)
        var loadText = sPref?.getString(MY_SHARED_PREFERENCES,null)
        current_score.text = loadText
    }
    fun addList(){
        val names = mutableListOf<String>(current_score.text.toString())
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_list_item_1, names
        )
        list_view_topscore.adapter = adapter
    }
}