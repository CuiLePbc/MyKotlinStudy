package com.cuile.mykotlinstudy.zhihu.data

import android.util.Log
import com.cuile.mykotlinstudy.intfac.DataRequestCallBack
import com.cuile.mykotlinstudy.zhihu.vandp.ZhihuRequestCallBack
import com.google.gson.Gson
import org.jetbrains.anko.async
import java.net.URL

/**
 * Created by cuile on 18-4-13.
 *
 */
class ZhihuRequest(private val zhihuCallBack: ZhihuRequestCallBack) {
    companion object {
        private const val BASE_URL = "https://news-at.zhihu.com/api/4"
    }

    /**
     * 获取今日热门消息
     */
    fun getLatestNews() {
        val url = "$BASE_URL/news/latest"

        async {
            val zhihuResultStr = URL(url).readText()
            val zhihuLatestNews = Gson().fromJson<ZhihuLatestNews>(zhihuResultStr, ZhihuLatestNews::class.java)

            if (zhihuLatestNews != null && zhihuLatestNews.stories.isNotEmpty()) {
                zhihuCallBack.requestTodayHotSuccess(zhihuLatestNews)
            } else {
                zhihuCallBack.requestFailed()
            }
        }
    }


    /**
     * 获取指定日期的热门消息
     * @param date 日期，格式为:20170102
     */
    fun getNewsByDate(date: String) {
        val url = "$BASE_URL/news/before/$date"

        async {
            val zhihuResultStr = URL(url).readText()
            val zhihuHistoryNews = Gson().fromJson<ZhihuHistoryNews>(zhihuResultStr, ZhihuHistoryNews::class.java)

            if (zhihuHistoryNews != null && zhihuHistoryNews.stories.isNotEmpty()) {
                zhihuCallBack.requestMoreHotSuccess(zhihuHistoryNews)
            } else {
                zhihuCallBack.requestFailed()
            }
        }
    }

    /**
     * 获取主题列表
     */
    fun getThemes() {
        val url = "$BASE_URL/themes"
        async {
            val zhihuResultStr = URL(url).readText()
            val zhihuThemes = Gson().fromJson<ZhihuThemes>(zhihuResultStr, ZhihuThemes::class.java)
            Log.i("GetThemes", zhihuThemes.toString())
            if (zhihuThemes != null && zhihuThemes.others.isNotEmpty()) {
                zhihuCallBack.requestThemeSuccess(zhihuThemes)
            } else {
                zhihuCallBack.requestFailed()
            }
        }
    }

    /**
     * 获取某一主题的今日日报
     * @param themeId 主题ID
     */
    fun getNewsByTheme(themeId: Int) {
        val url = "$BASE_URL/theme/$themeId"
        async {
            val zhihuResultStr = URL(url).readText()
            val zhihuThemeNews = Gson().fromJson<ZhihuThemeNews>(zhihuResultStr, ZhihuThemeNews::class.java)

            if (zhihuThemeNews != null && zhihuThemeNews.stories.isNotEmpty()) {
                zhihuCallBack.requestTodayThemeNewsSuccess(zhihuThemeNews)
            } else {
                zhihuCallBack.requestFailed()
            }
        }
    }

    /**
     * 获取某一主题以前的日报
     * @param themeId 主题ID
     * @param storyId 文章ID,本页任一文章ID,会得到从文章ID所在位置往后一页（不包括当前文章）的文章列表
     */
    fun getNewsByThemeAndDate(themeId: String, storyId: String) {
        val url = "$BASE_URL/theme/$themeId/before/$storyId"
        async {
            val zhihuResultStr = URL(url).readText()
            val zhihuThemeHistoryNews = Gson().fromJson<ZhihuThemeNews>(zhihuResultStr, ZhihuThemeNews::class.java)

            if (zhihuThemeHistoryNews != null && zhihuThemeHistoryNews.stories.isNotEmpty()) {
                zhihuCallBack.requestMoreThemeNewsSuccess(zhihuThemeHistoryNews)
            } else {
                zhihuCallBack.requestFailed()
            }
        }
    }

    /**
     * 获得消息内容详情
     * @param newsId 新闻列表中的新闻ID
     */
    fun getNewsBody(newsId: String) {
        val url = "$BASE_URL/news/$newsId"
        async {
            val zhihuResultStr = URL(url).readText()
            val zhihuNewsBody = Gson().fromJson<ZhihuDetailEntity>(zhihuResultStr, ZhihuDetailEntity::class.java)

            if (zhihuNewsBody != null && zhihuNewsBody.body.isNotBlank()) {
                zhihuCallBack.requestNewsDetailSuccess(zhihuNewsBody)
            } else {
                zhihuCallBack.requestFailed()
            }
        }
    }

//    /**
//     * 获取专栏列表
//     */
//    fun getSections() {
//        val url = "$BASE_URL/sections"
//    }
//
//    /**
//     * 获取某一专栏的文章列表
//     */
//    fun getNewsBySectionId(sectionId: String) {
//        val url = "$BASE_URL/section/$sectionId"
//    }
//
//    /**
//     * 获取某一专栏的指定时间的日报
//     * @param themeId 主题ID
//     * @param timeStemp 时间戳 例如 1465772400
//     */
//    fun getNewsBySectionIdAndDate(sectionId: String, timeStemp: String) {
//        val url = "$BASE_URL/section/$sectionId/before/$timeStemp"
//    }

}