package com.example.flickr_example

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private val myDataset: List<String>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {
        val imageView = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_layout, parent, false) as ImageView

        return MyViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Glide
            .with(holder.imageView)
            .load(myDataset[position])
            .into(holder.imageView)
    }

    override fun getItemCount() = myDataset.size
}