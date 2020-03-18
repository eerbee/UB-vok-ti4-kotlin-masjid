package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MarqueeAdapter (val slideList: ArrayList<Marquee>): RecyclerView.Adapter<MarqueeAdapter.ViewHolder>()
{
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: Marquee = slideList[position]
        holder.textViewIsi.text = user.isi
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.list_marquee, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return slideList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewIsi = itemView.findViewById(R.id.txt_isi_marquee) as TextView
    }

}