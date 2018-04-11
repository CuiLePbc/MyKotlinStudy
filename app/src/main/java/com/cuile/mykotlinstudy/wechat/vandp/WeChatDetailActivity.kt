package com.cuile.mykotlinstudy.wechat.vandp

import android.os.Bundle
import com.cuile.mykotlinstudy.ItemDetailActivity

/**
 * Created by cuile on 2018/3/31.
 *
 */
class WeChatDetailActivity : ItemDetailActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "精选详情"

        setJSEnable(true)
    }
}