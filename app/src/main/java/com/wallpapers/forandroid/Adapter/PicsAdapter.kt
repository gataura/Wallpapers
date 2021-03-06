package com.wallpapers.forandroid.Adapter

import android.content.Context
import android.content.Intent
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.wallpapers.forandroid.R
import com.wallpapers.forandroid.helper.ItemClickListener
import com.wallpapers.forandroid.PhotoViewActivity

class PicsAdapter(var values: List<String>, var context: Context): androidx.recyclerview.widget.RecyclerView.Adapter<PicsAdapter.PicsViewHolder>() {

    var res = 0
    lateinit var intent: Intent

    override fun onBindViewHolder(p0: PicsViewHolder, p1: Int) {

        var item = values[p1]
        res = context.resources.getIdentifier(item, "drawable", context.packageName)
        p0.pic.setImageResource(res)
        intent = Intent(context, PhotoViewActivity::class.java)

        p0.setItemClickListener1(object: ItemClickListener {
            override fun onClick(v: View, position: Int, isLongClick: Boolean) {
                res = context.resources.getIdentifier(item, "drawable", context.packageName)
                intent.putExtra("pic",res)
                Log.d("sendId","$res")
                context.startActivity(intent)
            }
        })

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PicsViewHolder {

        val view:View = LayoutInflater.from(p0.context).inflate(R.layout.pic_item_view,p0,false)
        return PicsViewHolder(view)

    }

    class PicsViewHolder(itemView: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView), View.OnLongClickListener, View.OnClickListener {

        fun setItemClickListener1(itemClickListener: ItemClickListener) {
            this.itemClickListener = itemClickListener
        }

        override fun onClick(v: View) {
            itemClickListener.onClick(v, adapterPosition,false)
        }

        override fun onLongClick(v: View?): Boolean {
            return true
        }

        var pic:ImageView = itemView.findViewById(R.id.img)
        lateinit var itemClickListener: ItemClickListener
        var picCard: androidx.cardview.widget.CardView = itemView.findViewById(R.id.pic_card)

        init {
            picCard.setOnClickListener(this)
            picCard.setOnLongClickListener(this)
        }
    }

}