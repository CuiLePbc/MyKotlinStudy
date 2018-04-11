package com.cuile.mykotlinstudy.toutiao.vandp

import android.os.Bundle
import com.cuile.mykotlinstudy.ItemDetailActivity

class TouTiaoDetailActivity : ItemDetailActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "新闻详情"
        setJSEnable(false)
    }
}
