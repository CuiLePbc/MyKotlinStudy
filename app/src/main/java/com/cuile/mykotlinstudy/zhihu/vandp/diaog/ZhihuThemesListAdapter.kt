package com.cuile.mykotlinstudy.zhihu.vandp.diaog

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.cuile.mykotlinstudy.GlideApp
import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.zhihu.data.ThemeBody
import com.cuile.mykotlinstudy.zhihu.data.ZhihuThemes
import org.jetbrains.anko.find

class ZhihuThemesListAdapter(private var datas: MutableList<ThemeBody> = mutableListOf(), private val zhihuThemeClickListener: (theme: ThemeBody, view: View) -> Unit): RecyclerView.Adapter<ZhihuThemesListAdapter.VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_zhihu_theme_dialog, null)

        return VH(view, zhihuThemeClickListener)
    }

    override fun getItemCount(): Int = datas.size

    fun setDatas(themes: ZhihuThemes) {
        datas.clear()
        datas.addAll(themes.others)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(datas[position])
    }


    class VH(itemView: View, private val zhihuThemeClickListener: (theme: ThemeBody, view: View) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val imageTheme: ImageView = itemView.find(R.id.imageTheme)
        private val titleTheme: TextView = itemView.find(R.id.titleTheme)
        fun bind(themeBody: ThemeBody) {
            with(themeBody) {
                titleTheme.text = name
                GlideApp.with(itemView)
                        .asDrawable()
                        .load(thumbnail)
                        .error(R.drawable.toutiao_default)
                        .into(imageTheme)

                itemView.setOnClickListener { zhihuThemeClickListener(this, itemView) }
            }
        }
    }
}