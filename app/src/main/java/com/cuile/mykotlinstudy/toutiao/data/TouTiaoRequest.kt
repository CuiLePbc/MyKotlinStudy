package com.cuile.mykotlinstudy.toutiao.data

import android.util.Log.i
import com.google.gson.Gson
import org.jetbrains.anko.async
import java.net.URL

/**
* Created by 崔乐 on 2017/5/22.
*
*/
class TouTiaoRequest(val typeStr: String, private val touTiaoCallBack: TouTiaoCallBack) {
    companion object {
        private val APPKEY = "70fda4545cf31fafe43253ff0f5dd8f6"
        private val BASE_URL = "http://v.juhe.cn/toutiao/index"
        private val COMPLETE_URL = "$BASE_URL?key=$APPKEY&type="
    }

    fun run() {
        async {
            val toutiaoJsonStr = URL(COMPLETE_URL).readText()
            val toutiaoInfo = Gson().fromJson<TouTiaoInfo>(toutiaoJsonStr, TouTiaoInfo::class.java)
            i("request ", toutiaoInfo.result.data.size.toString() + " news error_code is " + toutiaoInfo.error_code)
            if (toutiaoInfo.error_code == 0) {
                touTiaoCallBack.requestSuccess(toutiaoInfo)
            } else {
                touTiaoCallBack.requestFailed()
            }
        }
    }

    interface TouTiaoCallBack {
        fun requestSuccess(touTiaoInfo: TouTiaoInfo)
        fun requestFailed()
    }
}