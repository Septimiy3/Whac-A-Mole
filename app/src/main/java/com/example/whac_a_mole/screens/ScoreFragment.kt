package com.example.whac_a_mole.screens

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.whac_a_mole.APP_ACTIVITY
import com.example.whac_a_mole.R
import com.example.whac_a_mole.replaceFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_score.*


class ScoreFragment : Fragment(R.layout.fragment_score) {

    val MY_SHARED_PREFERENCES = "SAVE_TEXT"
    val MY_SHARED_PREFERENCES_LIST = "SAVE_LIST"
    val LOG_TAG = "SAVE_AND_LOAD"
    private var names : MutableList<String> = mutableListOf()

    override fun onStart() {
        super.onStart()
        loadData()

        val sharedPreferences = APP_ACTIVITY.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(APP_ACTIVITY, android.R.layout.simple_list_item_1, names)

        var loadText = sharedPreferences?.getString(MY_SHARED_PREFERENCES, null)
        current_score.text = loadText

        list_view_topscore.adapter = adapter

//        names.add(current_score.text.toString())

        txt_score_game.setOnClickListener {
            names.clear()
        }
        btn_try_again.setOnClickListener {
            replaceFragment(GameFragment(),false)
            names.add(current_score.text.toString())
            adapter.notifyDataSetChanged()
            saveData()
        }
        btn_menu.setOnClickListener {
            replaceFragment(StartFragment(),false)
            names.add(current_score.text.toString())
            adapter.notifyDataSetChanged()
            saveData()
        }
    }
    private fun saveData() {
        val sharedPreferences = APP_ACTIVITY.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(names)
        editor.putString(MY_SHARED_PREFERENCES_LIST, json)
        editor.apply()
        Log.d(LOG_TAG,"SAVE_TEXT_LIST")
    }
    private fun loadData() {
        names.sort()
        val sharedPreferences = APP_ACTIVITY.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString(MY_SHARED_PREFERENCES_LIST, null)
        val type = object: TypeToken<MutableList<String>>() {
        }.type

        if(json == null)
            names = mutableListOf()
        else
            names = gson.fromJson(json, type)
        Log.d(LOG_TAG,"LOAD_TEXT_LIST")
    }
}