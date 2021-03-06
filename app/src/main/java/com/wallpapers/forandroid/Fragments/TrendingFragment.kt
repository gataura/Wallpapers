package com.wallpapers.forandroid.Fragments


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wallpapers.forandroid.Adapter.PicsAdapter

import com.wallpapers.forandroid.R
import com.wallpapers.forandroid.helper.Constants
import com.wallpapers.forandroid.helper.SpaceItemDecoration

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TrendingFragment : androidx.fragment.app.Fragment() {

    var allPics = mutableListOf<String>()
    var constants = Constants()
    lateinit var picsRecyclerView: androidx.recyclerview.widget.RecyclerView
    lateinit var picsAdapter: PicsAdapter
    lateinit var layoutManager: androidx.recyclerview.widget.GridLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_trending, container, false)
        picsRecyclerView = view.findViewById(R.id.trend_recycler_view)
        getPics()

        return view
    }

    fun getPics() {
        for (i in constants.allPics) {
            allPics.addAll(i)
        }
        allPics.shuffle()

        picsRecyclerView.isNestedScrollingEnabled = false
        layoutManager = androidx.recyclerview.widget.GridLayoutManager(this.requireContext(), 2)
        picsRecyclerView.layoutManager = layoutManager
        picsAdapter = PicsAdapter(allPics, this.requireContext())
        picsRecyclerView.adapter = picsAdapter
        picsRecyclerView.addItemDecoration(SpaceItemDecoration(0))
        picsAdapter.notifyDataSetChanged()
        getPermissions()
    }


    fun getPermissions() {
        if (ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
    }



}
