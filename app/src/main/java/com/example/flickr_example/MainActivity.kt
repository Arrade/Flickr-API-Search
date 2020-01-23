package com.example.flickr_example

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickr_example.network.jsontokotlin.SearchProperties
import com.example.flickr_example.overview.OverviewViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val data: MutableList<MyAdapter.PhotoObject> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model = ViewModelProviders.of(this)[OverviewViewModel::class.java]

            model.properties.observe(this, Observer<SearchProperties> { searchResults ->
                val urlList: List<MyAdapter.PhotoObject> = searchResults.photos!!.photo.map { photo ->
                    val url = "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
                    MyAdapter.PhotoObject(url , photo.id)
                }

                data.clear()
                data.addAll(urlList)
                viewAdapter.notifyDataSetChanged()
            })

        findViewById<Button>(R.id.button).setOnClickListener {
            val query = findViewById<EditText>(R.id.editText).text.toString()
            model.performSearch(BuildConfig.API_KEY_FLICKR, query)
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(data)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerID).apply {

            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter
        }
    }
}