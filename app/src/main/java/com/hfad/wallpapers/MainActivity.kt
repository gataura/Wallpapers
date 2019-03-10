package com.hfad.wallpapers

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.hfad.wallpapers.Fragments.CategoriesFragment
import com.hfad.wallpapers.Fragments.TrendingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var fragmentMain = Fragment()
    lateinit var toolbar: Toolbar

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_categories -> {
                fragmentMain = CategoriesFragment()
                setFragment(fragmentMain)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_trending -> {
                fragmentMain = TrendingFragment()
                setFragment(fragmentMain)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        val tx: FragmentTransaction =  supportFragmentManager.beginTransaction()
        tx.replace(R.id.main_frame, TrendingFragment())
        tx.commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    fun setFragment(f: Fragment) {

        val fm:FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()

        ft.replace(R.id.main_frame, f)
        ft.commit()

    }
}
