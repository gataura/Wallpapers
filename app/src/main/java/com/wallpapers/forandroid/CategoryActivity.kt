package com.wallpapers.forandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import com.wallpapers.forandroid.Adapter.PicsAdapter
import com.wallpapers.forandroid.helper.SpaceItemDecoration
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor

class CategoryActivity : AppCompatActivity() {


    var pics = mutableListOf<String>()
    var tempPics = mutableListOf<String>()
    lateinit var picsRecyclerView: androidx.recyclerview.widget.RecyclerView
    lateinit var picsAdapter: PicsAdapter
    lateinit var layoutManager: androidx.recyclerview.widget.GridLayoutManager
    lateinit var toolbar: Toolbar
    lateinit var progressBar: ProgressBar
    private lateinit var compositeDispossable: CompositeDisposable
    private lateinit var pagination: PublishProcessor<Int>
    private val TAG = "CategoryActivity"
    var offset = 0
    var length = 10
    private var totalItemCount = 0
    private var lastVisibleItem = 0
    private var loading = false
    private val VISIBLE_TRESHOLD = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        toolbar = findViewById(R.id.category_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        pics.addAll(intent.getStringArrayListExtra("pics"))

        tempPics.addAll(pics)

        progressBar = findViewById(R.id.category_progress_bar)
        picsRecyclerView = findViewById(R.id.pics_recycler_view)
        picsRecyclerView.isNestedScrollingEnabled = false
        layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 2)
        picsRecyclerView.layoutManager = layoutManager
        picsRecyclerView.addItemDecoration(SpaceItemDecoration(0))
        picsAdapter = PicsAdapter(pics, this)
        picsRecyclerView.adapter = picsAdapter
        compositeDispossable = CompositeDisposable()
        pagination = PublishProcessor.create()
        picsAdapter.notifyDataSetChanged()

        setUpLoadMoreListener()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    fun setUpLoadMoreListener() {

        picsRecyclerView.addOnScrollListener(object: androidx.recyclerview.widget.RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                totalItemCount = picsRecyclerView.layoutManager!!.itemCount
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (!loading && totalItemCount <= (lastVisibleItem + VISIBLE_TRESHOLD)) {

                    offset += length
                    loading = true
                    addElements()

                }
            }

        })

    }

    fun addElements() {

        progressBar.visibility = View.VISIBLE
        tempPics.shuffle()
        pics.addAll(tempPics)
        picsAdapter.notifyDataSetChanged()
        loading = false
        progressBar.visibility = View.INVISIBLE

    }



    override fun onDestroy() {
        super.onDestroy()
        compositeDispossable.dispose()
    }

}
