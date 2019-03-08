package com.hfad.wallpapers

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.hfad.wallpapers.Fragments.CategoriesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var fragmentMain = Fragment()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                fragmentMain = CategoriesFragment()
                setFragment(fragmentMain)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tx: FragmentTransaction =  supportFragmentManager.beginTransaction()
        tx.replace(R.id.main_frame, CategoriesFragment())
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
