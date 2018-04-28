package com.cuile.mykotlinstudy.zhihu.vandp

import com.cuile.mykotlinstudy.intfac.DataRequestCallBack
import com.cuile.mykotlinstudy.zhihu.data.*

/**
 * Created by cuile on 18-4-13.
 *
 */
interface ZhihuRequestCallBack: DataRequestCallBack {
    fun requestTodayHotSuccess(zhihuLatestNews: ZhihuLatestNews)
    fun requestMoreHotSuccess(zhihuHistoryNews: ZhihuHistoryNews)
    fun requestTodayThemeNewsSuccess(zhihuThemeNews: ZhihuThemeNews)
    fun requestMoreThemeNewsSuccess(zhihuThemeNews: ZhihuThemeNews)
    fun requestNewsDetailSuccess(zhihuDetailEntity: ZhihuDetailEntity)
    fun requestThemeSuccess(zhihuThemes: ZhihuThemes)
}