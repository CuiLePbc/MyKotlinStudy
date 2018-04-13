package com.cuile.mykotlinstudy.zhihu.data

import com.cuile.mykotlinstudy.intfac.DataInterface

/**
 * Created by cuile on 18-4-13.
 *
 */

/**
 * 新闻内容
 * URL: https://news-at.zhihu.com/api/4/news/{story id}  story id例如 3892357
 *
 */
data class ZhihuDetailEntity(
        // HTML格式的新闻体
        val body: String,
        // 图片提供方
        val image_source: String,
        // 新闻标题
        val title: String,
        // 文章浏览界面中使用的大图，不同于最新消息中获得的小图
        val image: String,
        // 供在线查看内容与分享至 SNS 用的 URL
        val share_url: String,
        // 供手机端的 WebView(UIWebView) 使用
        val js: List<Any>,
        // 供 Google Analytics 使用
        val ga_prefix: String,
        val images: List<String>,
        // 新闻类型
        val type: Int,
        // 新闻ID
        val id: Int,
        // 供手机端的 WebView(UIWebView) 使用
        val css: List<String>
): DataInterface

/**
 * 最新消息
 * URL: https://news-at.zhihu.com/api/4/news/latest
 */
data class ZhihuLatestNews(
        // 日期，格式如"20140523"
		val date: String,
        // 当日新闻
		val stories: List<Story>,
        // 当日置顶滚动新闻
		val top_stories: List<TopStory>
): DataInterface

/**
 * 指定日期的新闻
 * URL: https://news-at.zhihu.com/api/4/news/before/{date}    date例如20131119
 */
data class ZhihuHistoryNews(
        // 日期
        val date: String,
        // 指定日期的新闻
        val stories: List<Story>
): DataInterface

/**
 * 主题消息列表
 * URL: https://news-at.zhihu.com/api/4/themes
 */
data class ZhihuThemes(
		val limit: Int,
        // 已订阅条目
		val subscribed: List<Any>,
        // 其他条目
		val others: List<ThemeBody>
): DataInterface

/**
 * 主题消息体
 */
data class ThemeBody(
		val color: Int,
        // 主题图片地址
		val thumbnail: String,
        // 主题描述
		val description: String,
        // 主题ID
		val id: Int,
        // 主题名称
		val name: String
)

/**
 * 最新新闻以及指定日期新闻的新闻信息
 */
data class Story(
        // 图像地址（使用数组形式。目前暂未有使用多张图片的情形出现，曾见无 images 属性的情况 ）
		val images: List<String>,
		val type: Int,
        // 新闻内容ID
		val id: Int,
        // 供 Google Analytics 使用
		val ga_prefix: String,
        // 新闻标题
		val title: String
)

/**
 * 置顶最新新闻体
 */
data class TopStory(
        // 图片
		val image: String,
		val type: Int,
        // 新闻ID
		val id: Int,
		val ga_prefix: String,
        // 新闻标题
		val title: String
)

/**
 * 主题内的新闻列表
 * URL: https://news-at.zhihu.com/api/4/theme/{theme id}  theme id 例如 11
 */
data class ZhihuThemeNews(
        // 文章列表
		val stories: List<ThemeStory>,
        // 主题介绍
		val description: String,
        // 主题的背景图片（大图）
		val background: String,
		val color: Int,
        // 主题名称
		val name: String,
        // 主题背景图片的小图版本
		val image: String,
        // 主题编辑『用户推荐日报』中此项的指是一个空数组，在 App 中的主编栏显示为『许多人』
		val editors: List<ThemeEditor>,
        // 图片来源
		val image_source: String
): DataInterface

/**
 * 主题编辑
 */
data class ThemeEditor(
        // 主编的知乎用户主页
		val url: String,
        // 主编的个人简介
		val bio: String,
        // 数据库中的唯一表示符
		val id: Int,
        // 主编的头像
		val avatar: String,
        // 主编的名字
		val name: String
)

/**
 * 主题内文章消息体
 */
data class ThemeStory(
		val images: List<String>,
		val type: Int,
		val id: Int,
		val title: String
)

/**
 * 指定专栏指定日期的新闻列表
 * URL: https://news-at.zhihu.com/api/4/section/#{section id}/before/#{timestamp}
 * 例如：https://news-at.zhihu.com/api/4/section/34/before/1465772400
 */
data class ZhihuSectionHistoryNews(
        // 时间戳
		val timestamp: Int,
        // 文章列表
		val stories: List<SectionHistoryStory>,
        // 主题名称
		val name: String
): DataInterface

/**
 *	指定日期专栏的文章信息
 */
data class SectionHistoryStory(
		// 图片
		val images: List<String>,
		// 日期  20160611
		val date: String,
		// 显示日期  6 月 11 日
		val display_date: String,
		// 文章ID
		val id: Int,
		// 文章标题
		val title: String
)