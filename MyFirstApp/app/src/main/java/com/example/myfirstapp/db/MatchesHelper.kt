package com.example.myfirstapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MatchesHelper(context: Context) : SQLiteOpenHelper(context, Matches.DATABASE_NAME, null,
    Matches.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Matches.CREAT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(Matches.SQL_DELETE_TABLE)
        onCreate(db)
    }



}