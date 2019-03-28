package com.wallpapers.forandroid.Fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.wallpapers.forandroid.CategoryActivity

import com.wallpapers.forandroid.R
import com.wallpapers.forandroid.helper.Constants

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
    lateinit var celImg:ImageView
    lateinit var animalImg:ImageView
    lateinit var flagImg: ImageView
    lateinit var oceanImg: ImageView
    lateinit var seriesimg: ImageView
    lateinit var movieImg: ImageView
    lateinit var cityImg: ImageView
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
        celImg = view.findViewById(R.id.celebrities_category)
        animalImg = view.findViewById(R.id.animals_category)
        flagImg = view.findViewById(R.id.flag_category)
        oceanImg = view.findViewById(R.id.ocean_category)
        seriesimg = view.findViewById(R.id.series_category)
        movieImg = view.findViewById(R.id.movie_category)
        cityImg = view.findViewById(R.id.city_category)

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

        celImg.setOnClickListener {
            intent.putExtra("pics", constants.celebrities)
            startActivity(intent)
        }

        animalImg.setOnClickListener {
            intent.putExtra("pics", constants.animalPics)
            startActivity(intent)
        }

        flagImg.setOnClickListener {
            intent.putExtra("pics", constants.flagPics)
            startActivity(intent)
        }

        oceanImg.setOnClickListener {
            intent.putExtra("pics", constants.oceanPics)
            startActivity(intent)
        }

        seriesimg.setOnClickListener {
            intent.putExtra("pics", constants.seriesPics)
            startActivity(intent)
        }

        movieImg.setOnClickListener {
            intent.putExtra("pics", constants.moviePics)
            startActivity(intent)
        }

        cityImg.setOnClickListener {
            intent.putExtra("pics", constants.cityPics)
            startActivity(intent)
        }

        return view
    }


}
