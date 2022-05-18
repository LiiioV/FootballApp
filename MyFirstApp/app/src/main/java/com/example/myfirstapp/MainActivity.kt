package com.example.myfirstapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapp.db.MatchesDao

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getTodayMatches(View: View) {
        val matchesIntent = Intent(this, TodayMatches::class.java)
        startActivity(matchesIntent)
    }

    fun getTodayNews(View: View) {
        val newsIntent = Intent(this, NewsActivity::class.java)
        startActivity(newsIntent)
    }

    fun findMatches(View: View) {
        val findIntent = Intent(this, FindMatch::class.java)
        startActivity(findIntent)
    }

}