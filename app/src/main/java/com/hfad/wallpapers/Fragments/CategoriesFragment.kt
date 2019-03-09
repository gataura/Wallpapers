package com.hfad.wallpapers.Fragments


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.hfad.wallpapers.CategoryActivity

import com.hfad.wallpapers.R
import com.hfad.wallpapers.helper.Constants

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

class CategoriesFragment : Fragment() {

    var constants = Constants()
    lateinit var natureImg: ImageView
    lateinit var spaceImg: ImageView
    lateinit var artImg: ImageView
    lateinit var carImg: ImageView
    lateinit var intent: Intent

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_categories, container, false)

        intent = Intent(this.requireContext(), CategoryActivity::class.java)

        natureImg = view.findViewById(R.id.nature_category)
        spaceImg = view.findViewById(R.id.space_category)
        artImg = view.findViewById(R.id.art_category)
        carImg = view.findViewById(R.id.cars_category)

        natureImg.setOnClickListener {
            intent.putExtra("pics", constants.naturePics)
            startActivity(intent)
        }

        spaceImg.setOnClickListener {
            intent.putExtra("pics", constants.spacePics)
            startActivity(intent)
        }

        artImg.setOnClickListener {
            intent.putExtra("pics", constants.artPics)
            startActivity(intent)
        }

        carImg.setOnClickListener {
            intent.putExtra("pics", constants.carPics)
            startActivity(intent)
        }

        return view
    }


}
