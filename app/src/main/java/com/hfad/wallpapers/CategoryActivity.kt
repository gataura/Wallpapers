package com.hfad.wallpapers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ProgressBar
import com.hfad.wallpapers.Adapter.PicsAdapter
import com.hfad.wallpapers.helper.SpaceItemDecoration
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.processors.PublishProcessor

class CategoryActivity : AppCompatActivity() {


    var pics = mutableListOf<String>()
    var tempPics = mutableListOf<String>()
    lateinit var picsRecyclerView: RecyclerView
    lateinit var picsAdapter: PicsAdapter
    lateinit var layoutManager: GridLayoutManager
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
        layoutManager = GridLayoutManager(this,2)
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

        picsRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
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
