package com.cuile.mykotlinstudy.wechat.vandp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.cuile.mykotlinstudy.GlideApp
import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.wechat.data.WeChatInfoResultData
import org.jetbrains.anko.find

/**
 * Created by cuile on 2018/3/24.
 *
 */
class WechatListAdapter(var items: MutableList<WeChatInfoResultData> = mutableListOf(), private val itemClickListener: (WeChatInfoResultData, View) -> Unit) : RecyclerView.Adapter<WechatListAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_toutiao_wechat_list, null)
        return ViewHolder(view, itemClickListener)
    }

    class ViewHolder(val view: View, private val itemClickListener: (WeChatInfoResultData, View) -> Unit) : RecyclerView.ViewHolder(view) {
        private val imgIV: ImageView = view.find(R.id.item_img)
        private val titleTV: TextView = view.find(R.id.item_title)
        private val authorTV: TextView = view.find(R.id.item_author)
        private val timeTV: TextView = view.find(R.id.item_date)


        fun bindView(weChatInfoResultData: WeChatInfoResultData) {
            with(weChatInfoResultData) {
                GlideApp.with(view.context)
                        .asDrawable()
                        .load(firstImg)
                        .into(imgIV)
                titleTV.text = title
                authorTV.text = source
                timeTV.text = mark
                itemView.setOnClickListener { itemClickListener(this, itemView) }
            }
        }
    }
}