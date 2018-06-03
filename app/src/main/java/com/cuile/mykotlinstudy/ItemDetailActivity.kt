package com.cuile.mykotlinstudy

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.webkit.*
import com.cuile.mykotlinstudy.R
import kotlinx.android.synthetic.main.activity_item_detail.*

open class ItemDetailActivity : AppCompatActivity() {
    companion object {
        const val ITEM_URL = "ItemDetailActivity:item_url"
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        setSupportActionBar(detail_appbar)
        detail_appbar.navigationIcon = getDrawable(R.drawable.ic_arrow_back_white_24dp)
        detail_appbar.setNavigationOnClickListener { finish() }

        val itemUrl = intent.getStringExtra(ITEM_URL)

        detail_webview.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        detail_webview.settings.setSupportZoom(false)
        detail_webview.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        detail_webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(itemUrl)
                return true
            }
        }
        detail_webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) itemLoadDetailProgressBar.visibility = View.INVISIBLE
                else itemLoadDetailProgressBar.visibility = View.VISIBLE
            }

            override fun onReceivedTitle(view: WebView?, t: String?) {
                super.onReceivedTitle(view, t)
                detail_appbar.title = t
            }
        }
        detail_webview.loadUrl(itemUrl)

    }

    fun setJSEnable(isEnabled: Boolean) {
        detail_webview.settings.javaScriptEnabled = isEnabled
    }

    override fun onBackPressed() {
        if (detail_webview.canGoBack()) detail_webview.goBack()
        else finish()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}
