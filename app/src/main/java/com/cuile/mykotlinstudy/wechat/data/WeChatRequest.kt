package com.cuile.mykotlinstudy.wechat.data

import android.util.Log.i
import com.cuile.mykotlinstudy.intfac.DataRequestCallBack
import com.google.gson.Gson
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.net.URL

/**
 * Created by cuile on 2018/3/20.
 *
 */
class WeChatRequest(numForOnePage: Int = 20, val weChatCallBack: DataRequestCallBack) {
    private var completeUrl: String
    companion object {
        private const val APPKEY = "cb13c7330d4d351fef3258cb18c470df"
        private const val BASE_URL = "http://api.tianapi.com/wxnew/"
    }
    init {
        completeUrl = "$BASE_URL?key=$APPKEY&num=$numForOnePage"
    }

    fun run(pageNo: Int = 1, isAdded: Boolean) {
        completeUrl += "&page=$pageNo"

        async {
            val weChatJsonStr = URL(completeUrl).readText()
            val weChatNews = Gson().fromJson<WeChatNews>(weChatJsonStr, WeChatNews::class.java)
            val weChatInfo = WeChatConvert.weChatNewsToWeChatInfo(weChatNews)

            i("request ", weChatInfo.result.list.size.toString() + " news error_code is " + weChatInfo.error_code)

            if (weChatInfo.error_code == 200) {
                uiThread {
                    if (isAdded)
                        weChatCallBack.requestMoreSuccess(weChatInfo)
                    else
                        weChatCallBack.requestSuccess(weChatInfo) }
            } else {
                uiThread { weChatCallBack.requestFailed() }
            }
        }

    }
}