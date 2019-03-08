package com.hfad.wallpapers.Adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.hfad.wallpapers.R
import id.zelory.compressor.Compressor

class PicsAdapter(var values: List<String>, var context: Context): RecyclerView.Adapter<PicsAdapter.PicsViewHolder>() {

    var res = 0
    lateinit var compressedImage:Bitmap
    lateinit var reso: Drawable

    override fun onBindViewHolder(p0: PicsViewHolder, p1: Int) {

        var item = values[p1]
        res = context.resources.getIdentifier(item[p1].toString(), "drawable", context.packageName)
        p0.pic.setImageResource(res)


    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PicsViewHolder {

        val view:View = LayoutInflater.from(p0.context).inflate(R.layout.pic_item_view,p0,false)
        return PicsViewHolder(view)

    }

    class PicsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        var pic:ImageView = itemView.findViewById(R.id.img)
    }

}