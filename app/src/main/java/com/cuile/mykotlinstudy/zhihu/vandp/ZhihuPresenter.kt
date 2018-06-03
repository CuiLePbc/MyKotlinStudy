package com.cuile.mykotlinstudy.zhihu.vandp

import com.cuile.mykotlinstudy.intfac.DataInterface
import com.cuile.mykotlinstudy.zhihu.data.*
import java.lang.ref.WeakReference

/**
 * Created by cuile on 18-4-13.
 *
 */
class ZhihuPresenter(zhihuViewParam: ZhihuContract.View): ZhihuContract.Presenter, ZhihuRequestCallBack {
    private val zhihuRequest = ZhihuRequest(this)
    private val zhihuView = WeakReference<ZhihuContract.View>(zhihuViewParam)
    init {
        zhihuView.get()?.setPresenter(this)
    }

    override fun requestTodayHot() {
        zhihuView.get()?.showLoadingBar()
        zhihuRequest.getLatestNews()
    }

    override fun requestMoreHot(date: String) {
        zhihuView.get()?.showLoadingBar()
        zhihuRequest.getNewsByDate(date)
    }

    override fun requestTodayThemeNews(themeId: Int) {
        zhihuView.get()?.showLoadingBar()
        zhihuRequest.getNewsByTheme(themeId)
    }

    override fun requestMoreThemeNews(themeId: String, currentNewsId: String) {
        zhihuView.get()?.showLoadingBar()
        zhihuRequest.getNewsByThemeAndDate(themeId, currentNewsId)
    }

    override fun requestNewsDetail(newsId: String) {
        zhihuView.get()?.showLoadingBar()
        zhihuRequest.getNewsBody(newsId)
    }

    override fun requestThemesList() {
        ZhihuRequest(this).getThemes()
    }

    override fun requestThemeSuccess(zhihuThemes: ZhihuThemes) {
        zhihuView.get()?.getThemesList(zhihuThemes)
    }

    override fun requestSuccess(dataInfo: DataInterface) {

    }

    override fun requestMoreSuccess(dataInfo: DataInterface) {

    }

    override fun requestFailed() {
        zhihuView.get()?.hideLoadingBar()
        zhihuView.get()?.refreshFailed()
    }


    override fun requestTodayHotSuccess(zhihuLatestNews: ZhihuLatestNews) {
        zhihuView.get()?.hideLoadingBar()
        zhihuView.get()?.refreshTodayHot(zhihuLatestNews)
    }

    override fun requestMoreHotSuccess(zhihuHistoryNews: ZhihuHistoryNews) {
        zhihuView.get()?.hideLoadingBar()
        zhihuView.get()?.refreshMoreHot(zhihuHistoryNews)
    }

    override fun requestTodayThemeNewsSuccess(zhihuThemeNews: ZhihuThemeNews) {
        zhihuView.get()?.hideLoadingBar()
        zhihuView.get()?.refreshTodayThemeNews(zhihuThemeNews)
    }

    override fun requestMoreThemeNewsSuccess(zhihuThemeNews: ZhihuThemeNews) {
        zhihuView.get()?.hideLoadingBar()
        zhihuView.get()?.refreshMoreThemeNews(zhihuThemeNews)
    }

    override fun requestNewsDetailSuccess(zhihuDetailEntity: ZhihuDetailEntity) {
        zhihuView.get()?.hideLoadingBar()
        zhihuView.get()?.refreshNewsDetail(zhihuDetailEntity)
    }

}