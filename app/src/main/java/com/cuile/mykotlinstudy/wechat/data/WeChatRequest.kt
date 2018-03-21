package com.cuile.mykotlinstudy.wechat.data

import android.util.Log.i
import com.google.gson.Gson
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.net.URL

/**
 * Created by cuile on 2018/3/20.
 *
 */
class WeChatRequest(numForOnePage: Int = 20, val touTiaoCallBack: WeChatCallBack) {
    private var completeUrl: String
    companion object {
        private val APPKEY = "5c1c9a9da16231b89756121f69440766"
        private val BASE_URL = "http://v.juhe.cn/weixin/query"
    }
    init {
        completeUrl = "$BASE_URL?key=$APPKEY&ps=$numForOnePage&dtype=json"
    }

    fun run(pageNo: Int = 1) {
        completeUrl += "&pno=$pageNo"
        async {
            val weChatJsonStr = URL(completeUrl).readText()
            val weChatInfo = Gson().fromJson<WeChatInfo>(weChatJsonStr, WeChatInfo::class.java)

            i("request ", weChatInfo.result.list.size.toString() + " news error_code is " + weChatInfo.error_code)

            if (weChatInfo.error_code == 0) {
                uiThread { touTiaoCallBack.requestSuccess(weChatInfo) }
            } else {
                uiThread { touTiaoCallBack.requestFailed() }
            }
        }

    }

    interface WeChatCallBack {
        fun requestSuccess(weChatInfo: WeChatInfo)
        fun requestFailed()
    }
}