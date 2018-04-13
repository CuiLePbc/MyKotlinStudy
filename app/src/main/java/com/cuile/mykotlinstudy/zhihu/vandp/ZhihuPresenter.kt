package com.cuile.mykotlinstudy.zhihu.vandp

import com.cuile.mykotlinstudy.intfac.DataInterface
import com.cuile.mykotlinstudy.zhihu.data.*

/**
 * Created by cuile on 18-4-13.
 *
 */
class ZhihuPresenter(private val zhihuView: ZhihuContract.View): ZhihuContract.Presenter, ZhihuRequestCallBack {
    private val zhihuRequest = ZhihuRequest(this)
    init {
        zhihuView.setPresenter(this)
    }

    override fun requestTodayHot() {
        zhihuView.showLoadingBar()
        zhihuRequest.getLatestNews()
    }

    override fun requestMoreHot(date: String) {
        zhihuView.showLoadingBar()
        zhihuRequest.getNewsByDate(date)
    }

    override fun requestThemesList() {
        zhihuView.showLoadingBar()
        zhihuRequest.getThemes()
    }

    override fun requestTodayThemeNews(themeId: String) {
        zhihuView.showLoadingBar()
        zhihuRequest.getNewsByTheme(themeId)
    }

    override fun requestMoreThemeNews(themeId: String, currentNewsId: String) {
        zhihuView.showLoadingBar()
        zhihuRequest.getNewsByThemeAndDate(themeId, currentNewsId)
    }

    override fun requestNewsDetail(newsId: String) {
        zhihuView.showLoadingBar()
        zhihuRequest.getNewsBody(newsId)
    }



    override fun requestSuccess(dataInfo: DataInterface) {

    }

    override fun requestMoreSuccess(dataInfo: DataInterface) {

    }

    override fun requestFailed() {
        zhihuView.hideLoadingBar()
        zhihuView.refreshFailed()
    }


    override fun requestTodayHotSuccess(zhihuLatestNews: ZhihuLatestNews) {
        zhihuView.hideLoadingBar()
        zhihuView.refreshTodayHot(zhihuLatestNews)
    }

    override fun requestMoreHotSuccess(zhihuHistoryNews: ZhihuHistoryNews) {
        zhihuView.hideLoadingBar()
        zhihuView.refreshMoreHot(zhihuHistoryNews)
    }

    override fun requestThemesListSuccess(zhihuThemes: ZhihuThemes) {
        zhihuView.hideLoadingBar()
        zhihuView.refreshThemesList(zhihuThemes)
    }

    override fun requestTodayThemeNewsSuccess(zhihuThemeNews: ZhihuThemeNews) {
        zhihuView.hideLoadingBar()
        zhihuView.refreshTodayThemeNews(zhihuThemeNews)
    }

    override fun requestMoreThemeNewsSuccess(zhihuThemeNews: ZhihuThemeNews) {
        zhihuView.hideLoadingBar()
        zhihuView.refreshMoreThemeNews(zhihuThemeNews)
    }

    override fun requestNewsDetailSuccess(zhihuDetailEntity: ZhihuDetailEntity) {
        zhihuView.hideLoadingBar()
        zhihuView.refreshNewsDetail(zhihuDetailEntity)
    }

}