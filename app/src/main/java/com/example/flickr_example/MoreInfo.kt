package com.example.flickr_example

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.flickr_example.network.jsontokotlin.InfoProperties
import com.example.flickr_example.overview.InfoViewModel

class MoreInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_info)

        val model = ViewModelProviders.of(this)[InfoViewModel::class.java]

        model.propertiesInfo.observe(this, Observer<InfoProperties> { infoProperties ->
            val photoTitle = infoProperties.photo.title._content
            val dateTaken = infoProperties.photo.dates.taken
            val views = infoProperties.photo.views
            val userName = infoProperties.photo.owner.username

            findViewById<TextView>(R.id.textView).apply {
                text = "* Title: \n" + photoTitle +
                        "\n\n* Date taken: \n" + dateTaken +
                        "\n\n* Views: \n" + views +
                        "\n\n* Uploaded by: \n" + userName
            }
        })

        val nav = intent.getStringExtra("INTENT_KEY") ?: " "

        model.grabInfo(BuildConfig.API_KEY_FLICKR, nav)
    }
}
