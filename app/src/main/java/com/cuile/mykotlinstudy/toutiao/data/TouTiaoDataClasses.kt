package com.cuile.mykotlinstudy.toutiao.data


/**
 * Created by 崔乐 on 2017/5/25.
 */
data class TouTiaoInfo(val result: TouTiaoInfoResult, val reason: String, val error_code: Int)

data class TouTiaoInfoResult(val stat: String, val data: Array<TouTiaoInfoResultData>)
data class TouTiaoInfoResultData(var date: String,
                                 var author_name: String,
                                 var thumbnail_pic_s: String,
                                 var uniquekey: String,
                                 var thumbnail_pic_s03: String,
                                 var thumbnail_pic_s02: String,
                                 var title: String,
                                 var category: String,
                                 var url: String)