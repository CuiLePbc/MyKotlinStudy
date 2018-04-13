package com.cuile.mykotlinstudy.zhihu.vandp

import com.cuile.mykotlinstudy.intfac.BasePresenter
import com.cuile.mykotlinstudy.intfac.BaseView
import com.cuile.mykotlinstudy.zhihu.data.*

/**
 * Created by cuile on 18-4-13.
 *
 */
interface ZhihuContract {
    interface View : BaseView<Presenter> {
        fun refreshTodayHot(zhihuLatestNews: ZhihuLatestNews)
        fun refreshMoreHot(zhihuHistoryNews: ZhihuHistoryNews)
        fun refreshThemesList(zhihuThemes: ZhihuThemes)
        fun refreshTodayThemeNews(zhihuThemeNews: ZhihuThemeNews)
        fun refreshMoreThemeNews(zhihuThemeNews: ZhihuThemeNews)
        fun refreshNewsDetail(zhihuDetailEntity: ZhihuDetailEntity)
    }
    interface Presenter : BasePresenter {
        fun requestTodayHot()
        fun requestMoreHot(date: String)
        fun requestThemesList()
        fun requestTodayThemeNews(themeId: String)
        fun requestMoreThemeNews(themeId: String, currentNewsId: String)
        fun requestNewsDetail(newsId: String)
    }
}