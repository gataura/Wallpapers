package com.wallpapers.forandroid.ui


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.wallpapers.forandroid.CONVERSION_DATA
import com.wallpapers.forandroid.Conversion
import com.wallpapers.forandroid._core.BaseActivity
import com.wallpapers.forandroid.EXTRA_TASK_URL
import com.wallpapers.forandroid.R
import im.delight.android.webview.AdvancedWebView
import kotlinx.android.synthetic.main.activity_web_view.*


/**
 * Created by Andriy Deputat email(andriy.deputat@gmail.com) on 3/13/19.
 */
class WebViewActivity : BaseActivity(), AdvancedWebView.Listener {

    private lateinit var webView: AdvancedWebView
    private lateinit var progressBar: ProgressBar

    override fun getContentView(): Int = R.layout.activity_web_view

    override fun initUI() {
        webView = web_view
        progressBar = progress_bar
    }

    private var conversions: MutableList<Conversion> = mutableListOf()
    override fun setUI() {

        getValuesFromDatabase({ dataSnapshot ->
            val values = dataSnapshot.child(CONVERSION_DATA)
            for (conversionSnapshot in values.children) {
                val conversion = conversionSnapshot.getValue(Conversion::class.java)
                conversion?.conversionEvent = conversionSnapshot.key!!
                conversion?.let { conversions.add(it) }
            }
            webView.loadUrl(intent.getStringExtra(EXTRA_TASK_URL))
        })

        logEvent("web-view-screen")
        progressBar.visibility = View.VISIBLE

        configureWebView()

    }

    private fun logEventIfUrlIsSuitable(urlSafe: String) {
        conversions.forEach {
            if (urlSafe.contains(it.offerId!!) && (urlSafe.contains(it.l!!))) {
                val uri = Uri.parse(urlSafe)
                val args = uri.queryParameterNames
                val bundle = Bundle()

                args.forEach {key ->
                    bundle.putString(key, uri.getQueryParameter(key))
                }
                logEvent(it.conversionEvent!!, bundle)
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun configureWebView() {
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                url?.let {urlSafe->
                    logEventIfUrlIsSuitable(urlSafe)
                }
                progressBar.visibility = View.GONE
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true

    }

    override fun onBackPressed() {
        if (!webView.onBackPressed()) {
            return
        }
        super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    override fun onPause() {
        webView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        webView.onDestroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        webView.onActivityResult(requestCode, resultCode, intent)
    }

    override fun onPageFinished(url: String?) {
    }

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {
    }

    override fun onDownloadRequested(
        url: String?,
        suggestedFilename: String?,
        mimeType: String?,
        contentLength: Long,
        contentDisposition: String?,
        userAgent: String?
    ) {
    }

    override fun onExternalPageRequest(url: String?) {
    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {
    }
}