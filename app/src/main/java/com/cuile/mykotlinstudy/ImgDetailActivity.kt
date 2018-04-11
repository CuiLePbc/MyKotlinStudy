package com.cuile.mykotlinstudy

import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_img_detail.*

class ImgDetailActivity : AppCompatActivity() {
    companion object {
        const val ITEM_URL = "ImgDetailActivity:item_url"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_img_detail)
        setSupportActionBar(null)
        val itemUrl = intent.getStringExtra(ITEM_URL)

        GlideApp.with(this)
                .load(itemUrl)
                .fitCenter()
                .into(detail_photo)

        detail_photo.enable()

        detail_photo.setOnClickListener {
//            ActivityCompat.finishAfterTransition(this)
        }

    }
}
