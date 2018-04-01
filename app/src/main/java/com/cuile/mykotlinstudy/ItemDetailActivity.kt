package com.cuile.mykotlinstudy

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.cuile.mykotlinstudy.R
import kotlinx.android.synthetic.main.activity_item_detail.*

open class ItemDetailActivity : AppCompatActivity() {
    companion object {
        val ITEM_URL = "ItemDetailActivity:item_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val itemUrl = intent.getStringExtra(ITEM_URL)

        detail_webview.settings.javaScriptEnabled = false
        detail_webview.settings.setSupportZoom(false)
        detail_webview.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        detail_webview.settings.cacheMode = WebSettings.LOAD_DEFAULT
        detail_webview.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(itemUrl)
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                itemLoadDetailProgressBar.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                itemLoadDetailProgressBar.visibility = View.INVISIBLE
            }
        })

        detail_webview.loadUrl(itemUrl)

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
