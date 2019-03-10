package com.hfad.wallpapers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.hfad.wallpapers.Adapter.PicsAdapter
import com.hfad.wallpapers.helper.SpaceItemDecoration

class CategoryActivity : AppCompatActivity() {


    var pics = mutableListOf<String>()
    lateinit var picsRecyclerView: RecyclerView
    lateinit var picsAdapter: PicsAdapter
    lateinit var layoutManager: GridLayoutManager
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        toolbar = findViewById(R.id.category_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        pics.addAll(intent.getStringArrayListExtra("pics"))


        picsRecyclerView = findViewById(R.id.pics_recycler_view)
        picsRecyclerView.isNestedScrollingEnabled = false
        layoutManager = GridLayoutManager(this,2)
        picsRecyclerView.layoutManager = layoutManager
        picsRecyclerView.addItemDecoration(SpaceItemDecoration(0))
        picsAdapter = PicsAdapter(pics, this)
        picsRecyclerView.adapter = picsAdapter
        picsAdapter.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
