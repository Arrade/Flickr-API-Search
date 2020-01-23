package com.example.flickr_example

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private val photos: List<PhotoObject>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
    class PhotoObject(val url: String, val id: String)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val imageView = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_layout, parent, false) as ImageView

        return MyViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Glide
            .with(holder.imageView)
            .load(photos[position].url)
            .into(holder.imageView)

        holder.imageView.setOnClickListener {

            val intent = Intent(holder.imageView.context, MoreInfo::class.java)

            intent.putExtra("INTENT_KEY", photos[position].id)

            holder.imageView.context.startActivity(intent)
        }
    }
    override fun getItemCount() = photos.size
}