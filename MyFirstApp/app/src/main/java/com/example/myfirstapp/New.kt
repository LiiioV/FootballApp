package com.example.myfirstapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup


const val ARG_OBJECT = "object"

class New : Fragment() {

    data class NewsAndImages(val News : String, val Img : String)

    private var myNews: MutableList<New.NewsAndImages> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {

        return inflater.inflate(R.layout.fragment_new, container, false)
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            lifecycleScope.launch(Dispatchers.Default) {
                myNews = getBestNews()
            }
            val text = view.findViewById<TextView>(R.id.tw)
            text.text = getInt(ARG_OBJECT).toString()
//            val button = view.findViewById<Button>(R.id.button)
//            button.setBa
        }
    }

    private suspend fun getBestNews() : MutableList<NewsAndImages> {
        val sUrl = "https://www.eurosport.com/football/"
//        val doc = Jsoup.connect(sUrl).get()
//        val news = doc.select(".watchpage")
//        val list: MutableList<NewsAndImages> = mutableListOf()
//
//        list.add(NewsAndImages(news.html(), ""))
//        list.add(NewsAndImages(news.html(), ""))
//        list.add(NewsAndImages(news.html(), ""))
//        list.add(NewsAndImages(news.html(), ""))
//        list.add(NewsAndImages(news.html(), ""))
        return mutableListOf()
    }
}