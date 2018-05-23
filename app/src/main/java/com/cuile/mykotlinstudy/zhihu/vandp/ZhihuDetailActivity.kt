package com.cuile.mykotlinstudy.zhihu.vandp

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.LevelListDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.text.Html
import android.util.Log
import android.widget.ImageView
import com.cuile.mykotlinstudy.GlideApp
import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.intfac.DataInterface
import com.cuile.mykotlinstudy.zhihu.data.*
import com.pixplicity.htmlcompat.HtmlCompat
import kotlinx.android.synthetic.main.activity_zhihu_detail.*
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.net.URL

class ZhihuDetailActivity : AppCompatActivity(), ZhihuContract.View {
    companion object {
        const val ITEM_ID = "zhihuDetailActivity:item_id"
    }

    private var zhihuPresenter: ZhihuContract.Presenter = ZhihuPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zhihu_detail)
        val newsId = intent.getStringExtra(ITEM_ID)
        setSupportActionBar(zhihu_detail_toolbar)

        zhihu_detail_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        zhihu_detail_toolbar.setNavigationOnClickListener {
            ActivityCompat.finishAfterTransition(this)
        }
        zhihu_detail_toolbar_layout.setCollapsedTitleTextColor(Color.WHITE)
        zhihu_detail_toolbar_layout.setExpandedTitleColor(Color.WHITE)

        zhihuPresenter.requestNewsDetail(newsId)
    }

    override fun refreshNewsDetail(zhihuDetailEntity: ZhihuDetailEntity) {
        Log.i("zhihuDetail_img:", zhihuDetailEntity.image)
        zhihu_detail_toolbar_layout.title = zhihuDetailEntity.title
        zhihu_detail_content.text = HtmlCompat.fromHtml(
                this,
                zhihuDetailEntity.image,
                HtmlCompat.FROM_HTML_MODE_LEGACY)


        GlideApp.with(this)
                .load(zhihuDetailEntity.image)
                .centerCrop()
                .placeholder(R.drawable.toutiao_default)
                .into(zhihu_detail_toolbar_img)


    }
    override fun refreshFailed() {
        longToast("连接失败")
    }


    override fun setPresenter(presenter: ZhihuContract.Presenter) {
        this.zhihuPresenter = presenter
    }

    override fun showLoadingBar() {

    }

    override fun hideLoadingBar() {

    }



    override fun refreshTodayHot(zhihuLatestNews: ZhihuLatestNews) {}

    override fun refreshMoreHot(zhihuHistoryNews: ZhihuHistoryNews) {}

    override fun refreshTodayThemeNews(zhihuThemeNews: ZhihuThemeNews) {}

    override fun refreshMoreThemeNews(zhihuThemeNews: ZhihuThemeNews) {}

    override fun getThemesList(zhihuThemes: ZhihuThemes) {}

    override fun isActive(): Boolean = true

    override fun refreshList(datas: DataInterface) {}

    override fun addList(datas: DataInterface) {}
}
