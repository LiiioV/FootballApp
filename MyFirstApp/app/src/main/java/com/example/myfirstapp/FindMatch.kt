package com.example.myfirstapp

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import com.example.myfirstapp.db.Matches
import com.example.myfirstapp.db.MatchesDao

class FindMatch : AppCompatActivity() {

    private var matchesDao = MatchesDao(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_match)
    }

    fun findMatch(view: View) {
        val team = findViewById<EditText>(R.id.edit_query).text.toString().trim()
        matchesDao.matchesHelper.onCreate(matchesDao.db)
        matchesDao.openDb()
        val list = matchesDao.findMatch(team)
        matchesDao.closeDB()

        val linearLayout = findViewById<LinearLayout>(R.id.line)

        val horizParams =
            LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        horizParams.setMargins(2, 2, 2, 2)

        for ((id, match) in list.withIndex()) {
            linearLayout.addView(
                TodayMatches.matchLayoutMaker(this, match, id),
                horizParams
            )
        }

    }

}