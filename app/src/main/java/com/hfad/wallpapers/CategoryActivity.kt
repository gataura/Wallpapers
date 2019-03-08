package com.hfad.wallpapers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.hfad.wallpapers.Adapter.PicsAdapter

class CategoryActivity : AppCompatActivity() {


    var pics = mutableListOf<String>()
    lateinit var picsRecyclerView: RecyclerView
    lateinit var picsAdapter: PicsAdapter
    lateinit var layoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        pics.addAll(intent.getStringArrayExtra("nature"))


        picsRecyclerView = findViewById(R.id.pics_recycler_view)
        picsRecyclerView.isNestedScrollingEnabled = false
        layoutManager = GridLayoutManager(this,2)
        picsRecyclerView.layoutManager = layoutManager
        picsAdapter = PicsAdapter(pics, this)
        picsRecyclerView.adapter = picsAdapter
        picsAdapter.notifyDataSetChanged()
    }
}
