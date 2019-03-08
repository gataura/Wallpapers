package com.hfad.wallpapers.Fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.hfad.wallpapers.CategoryActivity

import com.hfad.wallpapers.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CategoriesFragment : Fragment() {

    var naturePics = arrayListOf("nature1", "nature2", "nature3", "nature4")
    lateinit var natureImg: ImageView
    lateinit var intent: Intent

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_categories, container, false)

        intent = Intent(this.requireContext(), CategoryActivity::class.java)

        natureImg = view.findViewById(R.id.nature_category)
        natureImg.setOnClickListener {
            intent.putStringArrayListExtra("nature", naturePics)
            startActivity(intent)
        }

        return view
    }


}
