package com.cuile.mykotlinstudy.toutiao.data

import com.google.gson.Gson
import java.net.URL

/**
 * Created by 崔乐 on 2017/5/22.
 */
public class TouTiaoRequest(val typeStr: String) {
    companion object {
        private val APPKEY = "70fda4545cf31fafe43253ff0f5dd8f6"
        private val BASE_URL = "http://v.juhe.cn/toutiao/index"
        private val COMPLETE_URL = "${BASE_URL}?key=${APPKEY}&type="
    }
    public fun run() : TouTiaoInfo {
        val forecastJsonStr = URL(COMPLETE_URL).readText()
        return Gson().fromJson<TouTiaoInfo>(forecastJsonStr, TouTiaoInfo::class.java)

    }
}