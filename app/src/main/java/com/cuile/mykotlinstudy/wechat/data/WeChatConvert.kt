package com.cuile.mykotlinstudy.wechat.data

/**
 * Created by cuile on 18-4-11.
 *
 */
object WeChatConvert {
    fun weChatNewsToWeChatInfo(weChatNews: WeChatNews): WeChatInfo {

        var weChatInfoResultInfoDatas: List<WeChatInfoResultData> = mutableListOf()
        weChatNews.newslist.forEach {
            weChatInfoResultInfoDatas += WeChatInfoResultData(
                    id = "0",
                    title = it.title,
                    source = it.description,
                    firstImg = it.picUrl,
                    mark = it.ctime,
                    url = it.url
            )
        }
        return WeChatInfo(WeChatInfoResult(weChatInfoResultInfoDatas, 0, 0, 0), weChatNews.msg, weChatNews.code)
    }
}