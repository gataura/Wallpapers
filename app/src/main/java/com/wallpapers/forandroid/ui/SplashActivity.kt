package com.wallpapers.forandroid.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.google.firebase.database.DataSnapshot
import com.wallpapers.forandroid.*
import com.wallpapers.forandroid._core.BaseActivity
import kotlinx.android.synthetic.main.activity_web_view.*


/**
 * Created by Andriy Deputat email(andriy.deputat@gmail.com) on 3/13/19.
 */
class SplashActivity : BaseActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    private lateinit var dataSnapshot: DataSnapshot

    override fun getContentView(): Int = R.layout.activity_web_view

    override fun initUI() {
        webView = web_view
        progressBar = progress_bar
    }

    override fun setUI() {
        logEvent("splash-screen")
        webView.webViewClient = object : WebViewClient() {
            /**
             * Check if url contains key words:
             * /money - needed user (launch WebViewActivity or show in browser)
             * /main - bot or unsuitable user (launch ContentActivity)
             */
            @SuppressLint("deprecated")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.contains("/money")) {
                    // task url for web view or browser
                    val taskUrl = dataSnapshot.child(TASK_URL).value as String
                    val value = dataSnapshot.child(SHOW_IN).value as String

                    if (value == WEB_VIEW) {
                        startActivity(
                                Intent(this@SplashActivity, WebViewActivity::class.java)
                                .putExtra(EXTRA_TASK_URL, taskUrl)
                        )
                        finish()
                    } else if (value == BROWSER) {
                        // launch browser with task url
                        val browserIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("")
                        )

                        logEvent("task-url-browser")
                        startActivity(browserIntent)
                        finish()
                    }
                } else if (url.contains("/main")) {
                    //val taskUrl = dataSnapshot.child(TASK_URL).value as String
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
                progressBar.visibility = View.GONE
                return false
            }
        }

        progressBar.visibility = View.VISIBLE

        getValuesFromDatabase({
            dataSnapshot = it
            // load needed url to determine if user is suitable
            webView.loadUrl(it.child(SPLASH_URL).value as String)
        }, {
            Log.d("SplashErrActivity", "didn't work fetchremote")
            progressBar.visibility = View.GONE
        })
    }
}