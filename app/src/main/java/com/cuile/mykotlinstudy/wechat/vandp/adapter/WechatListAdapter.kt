package com.cuile.mykotlinstudy.wechat.vandp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.wechat.data.WeChatInfoResultData
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

/**
 * Created by cuile on 2018/3/24.
 *
 */
class WechatListAdapter(var items: MutableList<WeChatInfoResultData> = mutableListOf()) : RecyclerView.Adapter<WechatListAdapter.ViewHolder>() {

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_toutiao_list, null)
        return ViewHolder(view)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val titleTV: TextView = view.find(R.id.toutiao_item_title)
        private val authorTV: TextView = view.find(R.id.toutiao_item_author)
        private val dateTV: TextView = view.find(R.id.toutiao_item_date)
        private val img: ImageView = view.find(R.id.toutiao_item_img)

        fun bindView(weChatInfoResultData: WeChatInfoResultData) {
            with(weChatInfoResultData) {
                titleTV.text = title
                authorTV.text = source
                dateTV.text = mark
                Picasso.with(view.context)
                        .load(url)
                        .placeholder(R.drawable.toutiao_default)
                        .error(R.drawable.toutiao_default)
                        .into(img)
            }
        }
    }
}