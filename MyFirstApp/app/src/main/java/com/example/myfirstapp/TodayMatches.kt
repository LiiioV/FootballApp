package com.example.myfirstapp

import android.annotation.SuppressLint
import android.app.ActionBar.LayoutParams
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myfirstapp.db.Matches
import com.example.myfirstapp.db.MatchesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup


class TodayMatches : AppCompatActivity() {

    private var matchesDao = MatchesDao(this)

    lateinit var list: MutableList<TodayMatches.Match>

    data class Match(val team1: String, val team2: String, val score: String)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_matches)

        lifecycleScope.launch(Dispatchers.Default) {
            list = getMatches()
            for ((id, match) in list.withIndex()) {
                setMatchInLayout(match, id)
            }
        }
    }

    @SuppressLint("ResourceAsColor", "RtlHardcoded")
    private suspend fun setMatchInLayout(match: Match, id: Int) {
        withContext(Dispatchers.Main) {
            val linearLayout = findViewById<LinearLayout>(R.id.line)

            val horizParams =
                LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            horizParams.setMargins(2, 2, 2, 2)

            linearLayout.addView(
                matchLayoutMaker(this@TodayMatches, match, id),
                horizParams
            )

        }
    }


    private suspend fun getMatches(): MutableList<Match> {
        val sUrl = "https://www.liveresult.ru/football/matches"
        val doc = Jsoup.connect(sUrl).get()
        val matches = doc.select(".main").select(".main-content").select(".matches-live")
            .select(".matches-live-matches").select(".live-group").select(".live-group-data")
            .select(".live-match").select(".live-match-row").select(".teams")
        val list: MutableList<Match> = mutableListOf()
        for (match in matches) {
            val mat = Match(
                match.select(".team1").text().trim(),
                match.select(".team2").text().trim(),
                match.select(".score").text().trim()
            )
            if (mat.score.contains(":") && mat.team1 != "" && mat.team2 != "") {
                list.add(mat)
            }
        }
        return list
    }

    fun saveMatches(View: View) {
        matchesDao.openDb()

        for (match in list) {
            matchesDao.insertToTable(match)
        }
        matchesDao.closeDB()

        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
    }
    companion object {
        @SuppressLint("RtlHardcoded")
        fun matchLayoutMaker(
            context: Context,
            match: TodayMatches.Match,
            id: Int
        ): LinearLayout {
            val horizontalLinear = LinearLayout(context)
            horizontalLinear.orientation = LinearLayout.HORIZONTAL
            horizontalLinear.weightSum = 3F
            horizontalLinear.setBackgroundResource(if (id % 2 == 0) R.color.purple_700 else R.color.purple_500)

            val team1 = TextView(context)
            team1.layoutParams = TableLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT,
                1F
            )
            team1.textSize = 20F
            team1.text = match.team1
            team1.gravity = Gravity.LEFT
            horizontalLinear.addView(team1)

            val score = TextView(context)
            score.layoutParams = TableLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT,
                1F
            )

            score.textSize = 25F
            score.text = match.score
            score.gravity = Gravity.CENTER
            horizontalLinear.addView(score)

            val team2 = TextView(context)
            team2.layoutParams = TableLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT,
                1F
            )

            team2.textSize = 20F
            team2.text = match.team2
            team2.gravity = Gravity.RIGHT
            horizontalLinear.addView(team2)
            return horizontalLinear
        }
    }
}