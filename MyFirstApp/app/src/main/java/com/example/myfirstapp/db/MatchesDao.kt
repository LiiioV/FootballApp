package com.example.myfirstapp.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.myfirstapp.TodayMatches

class MatchesDao(val context: Context) {
    val matchesHelper = MatchesHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db = matchesHelper.writableDatabase
        matchesHelper.onCreate(db)
    }

    fun insertToTable(team1: String, score: String, team2: String) {
        val values = ContentValues().apply {
            put(Matches.COLUMN_NAME_TEAM1, team1)
            put(Matches.COLUMN_NAME_SCORE, score)
            put(Matches.COLUMN_NAME_TEAM2, team2)
        }
        db?.insert(Matches.TABLE_NAME, null, values)
    }

    fun insertToTable(match : TodayMatches.Match) {
        insertToTable(match.team1, match.score, match.team2)
    }

    @SuppressLint("Recycle", "Range")
    fun find(): ArrayList<String> {
        val dataList = ArrayList<String>()
        val cursor = db?.query(Matches.TABLE_NAME, null, null, null, null, null, null)
        with(cursor) {
            while (this?.moveToNext() == true) {
                val dataText = cursor?.getString(cursor.getColumnIndex(Matches.COLUMN_NAME_TEAM1))
                dataList.add(dataText.toString())
            }
        }
        cursor?.close()

        return dataList
    }

    @SuppressLint("Range")
    fun findMatch(team: String): MutableList<TodayMatches.Match> {
        val dataList: MutableList<TodayMatches.Match> = mutableListOf()
        val cursor = db?.query(Matches.TABLE_NAME,
            arrayOf(Matches.COLUMN_NAME_TEAM1, Matches.COLUMN_NAME_SCORE, Matches.COLUMN_NAME_TEAM2),
            "${Matches.COLUMN_NAME_TEAM1} == \"$team\" OR ${Matches.COLUMN_NAME_TEAM2} == \"$team\"",
            null, null, null, null
        )

        with(cursor) {
            while(this?.moveToNext() == true) {
                val m = TodayMatches.Match(
                    cursor?.getString(cursor.getColumnIndex(Matches.COLUMN_NAME_TEAM1)).toString(),
                    cursor?.getString(cursor.getColumnIndex(Matches.COLUMN_NAME_SCORE)).toString(),
                    cursor?.getString(cursor.getColumnIndex(Matches.COLUMN_NAME_TEAM2)).toString(),
                )
                dataList.add(m)
            }
        }
        cursor?.close()

        return dataList
    }

    fun closeDB() {
        matchesHelper.close()
    }
}