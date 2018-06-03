package com.cuile.mykotlinstudy.zhihu.vandp

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LevelListDrawable
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.cuile.mykotlinstudy.GlideApp
import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.intfac.DataInterface
import com.cuile.mykotlinstudy.zhihu.data.*
import com.pixplicity.htmlcompat.HtmlCompat
import kotlinx.android.synthetic.main.activity_zhihu_detail.*
import org.jetbrains.anko.async
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import java.io.InputStream

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
        zhihu_detail_toolbar_layout.title = zhihuDetailEntity.title
        zhihu_detail_content.text = HtmlCompat.fromHtml(
                this,
                zhihuDetailEntity.body,
                HtmlCompat.FROM_HTML_MODE_LEGACY,
                { source, _ ->
                    val img = LevelListDrawable()
                    Glide.with(applicationContext)
                            .asDrawable()
                            .load(source)
                            .into(object: SimpleTarget<Drawable>() {
                                override fun onResourceReady(drawable: Drawable, transition: Transition<in Drawable>?) {
                                    img.addLevel(1, 1, drawable)
                                    img.level = 1

                                    zhihu_detail_content.invalidate()
                                    zhihu_detail_content.text = zhihu_detail_content.text
                                }
                            })
                    img
                }
        )


        if (zhihuDetailEntity.image.isNullOrBlank() || zhihuDetailEntity.image.isEmpty()) {
            zhihu_detail_toolbar_img.visibility = View.INVISIBLE
        } else {
            GlideApp.with(this)
                    .load(zhihuDetailEntity.image)
                    .placeholder(R.drawable.default_bg)
                    .error(R.drawable.default_bg)
                    .into(zhihu_detail_toolbar_img)
        }

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
