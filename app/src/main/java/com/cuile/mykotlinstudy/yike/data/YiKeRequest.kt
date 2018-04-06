package com.cuile.mykotlinstudy.yike.data

import android.util.Log
import com.cuile.mykotlinstudy.intfac.DataRequestCallBack
import com.google.gson.Gson
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.net.URL

/**
 * Created by cuile on 2018/3/31.
 *
 */
class YiKeRequest(private val yiKeCallBack: DataRequestCallBack) {
    private var completeUrl: String
    companion object {
        private const val APPKEY = "5ad372399682b0a5c72aa5678041a152"
        private const val BASE_URL = "http://v.juhe.cn/joke/randJoke.php"
    }

    init {
        completeUrl = "$BASE_URL?key=$APPKEY"
    }

    fun run(isPic: Boolean, isadded: Boolean) {
        if (isPic) {
            completeUrl += "&type=pic"
        }

        async {
            val yiKeStr = URL(completeUrl).readText()
            val yiKeInfo = Gson().fromJson<YiKeInfo>(yiKeStr, YiKeInfo::class.java)

            Log.i("request ", yiKeInfo.result.size.toString() + " news error_code is " + yiKeInfo.error_code)

            if (yiKeInfo.error_code == 0) {
                uiThread {
                    if (isadded) {
                        yiKeCallBack.requestMoreSuccess(yiKeInfo)
                    } else {
                        yiKeCallBack.requestSuccess(yiKeInfo)
                    }
                }
            } else {
                uiThread { yiKeCallBack.requestFailed() }
            }
        }
    }
}