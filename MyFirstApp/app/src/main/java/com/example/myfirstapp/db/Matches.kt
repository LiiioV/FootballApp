package com.example.myfirstapp.db

import android.provider.BaseColumns

object Matches : BaseColumns {
    const val TABLE_NAME = "Matches"
    const val COLUMN_NAME_TEAM1 = "Team1"
    const val COLUMN_NAME_SCORE = "Score"
    const val COLUMN_NAME_TEAM2 = "Team2"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "Matches.db"

    const val CREAT_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "$COLUMN_NAME_TEAM1 TEXT," +
            "$COLUMN_NAME_SCORE TEXT," +
            "$COLUMN_NAME_TEAM2 TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}