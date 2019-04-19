package com.wallpapers.forandroid

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.wallpapers.forandroid.Fragments.CategoriesFragment
import com.wallpapers.forandroid.Fragments.TrendingFragment
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var fragmentMain = androidx.fragment.app.Fragment()
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

        val tx: androidx.fragment.app.FragmentTransaction =  supportFragmentManager.beginTransaction()
        tx.replace(R.id.main_frame, TrendingFragment())
        tx.commit()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        var config = YandexMetricaConfig.newConfigBuilder("1a57cd3d-a577-422f-aa97-4441da958713").build()
        YandexMetrica.activate(this, config)
        YandexMetrica.enableActivityAutoTracking(this.application)

    }

    fun setFragment(f: androidx.fragment.app.Fragment) {

        val fm: androidx.fragment.app.FragmentManager = supportFragmentManager
        val ft: androidx.fragment.app.FragmentTransaction = fm.beginTransaction()

        ft.replace(R.id.main_frame, f)
        ft.commit()

    }
}
