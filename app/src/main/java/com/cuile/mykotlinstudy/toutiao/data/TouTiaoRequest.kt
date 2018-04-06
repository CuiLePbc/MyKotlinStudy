package com.cuile.mykotlinstudy.toutiao.data

import android.util.Log.i
import com.cuile.mykotlinstudy.intfac.DataRequestCallBack
import com.google.gson.Gson
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.net.URL

/**
* Created by 崔乐 on 2017/5/22.
*
*/
class TouTiaoRequest(private val touTiaoCallBack: DataRequestCallBack) {
    private var completeUrl: String

    companion object {
        private const val APPKEY = "70fda4545cf31fafe43253ff0f5dd8f6"
        private const val BASE_URL = "http://v.juhe.cn/toutiao/index"
        
    }

    init {
        completeUrl = "$BASE_URL?key=$APPKEY"
    }

    fun run(typeStr: String, isAdded: Boolean) {
        completeUrl += "&type=$typeStr"

        async {
            val toutiaoJsonStr = URL(completeUrl).readText()
            val toutiaoInfo = Gson().fromJson<TouTiaoInfo>(toutiaoJsonStr, TouTiaoInfo::class.java)

            i("request ", toutiaoInfo.result.data.size.toString() + " news error_code is " + toutiaoInfo.error_code)

            if (toutiaoInfo.error_code == 0) {
                uiThread {
                    if (isAdded)
                        touTiaoCallBack.requestMoreSuccess(toutiaoInfo)
                    else
                        touTiaoCallBack.requestSuccess(toutiaoInfo) }
            } else {
                uiThread { touTiaoCallBack.requestFailed() }
            }
        }
    }
}