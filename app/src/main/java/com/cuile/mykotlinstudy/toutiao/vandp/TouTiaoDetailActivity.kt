package com.cuile.mykotlinstudy.toutiao.vandp

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
import kotlinx.android.synthetic.main.activity_tou_tiao_detail.*

class TouTiaoDetailActivity : AppCompatActivity() {
    companion object {
        val TOUTIAO_ITEM_URL = "TouTiaoDetailActivity:toutiao_item_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tou_tiao_detail)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "新闻详情"

        val toutiaoItemUrl = intent.getStringExtra(TOUTIAO_ITEM_URL)

        toutiao_detail_webview.settings.javaScriptEnabled = false
        toutiao_detail_webview.settings.setSupportZoom(false)
        toutiao_detail_webview.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        toutiao_detail_webview.settings.cacheMode = WebSettings.LOAD_DEFAULT
        toutiao_detail_webview.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(toutiaoItemUrl)
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                toutiaoDetailProgressBar.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                toutiaoDetailProgressBar.visibility = View.INVISIBLE
            }
        })

        toutiao_detail_webview.loadUrl(toutiaoItemUrl)

    }

    override fun onBackPressed() {
        if (toutiao_detail_webview.canGoBack()) toutiao_detail_webview.goBack()
        else finish()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}
