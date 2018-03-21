package com.cuile.mykotlinstudy.toutiao.vandp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.toutiao.data.TouTiaoInfoResultData
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

/**
 * Created by 崔乐 on 2017/5/22.
 */
class ToutiaoListAdapter(var items: MutableList<TouTiaoInfoResultData> = mutableListOf<TouTiaoInfoResultData>(),
                         val itemClickListener: (TouTiaoInfoResultData) -> Unit) : RecyclerView.Adapter<ToutiaoListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_toutiao_list, null)
        return ViewHolder(view, itemClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    class ViewHolder(val view: View, val itemClickListener: (TouTiaoInfoResultData) -> Unit) : RecyclerView.ViewHolder(view) {

        private val titleTV: TextView = view.find(R.id.toutiao_item_title)
        private val authorTV: TextView = view.find(R.id.toutiao_item_author)
        private val dateTV: TextView = view.find(R.id.toutiao_item_date)
        private val img: ImageView = view.find(R.id.toutiao_item_img)

        fun bindView(touTiaoInfoResultData: TouTiaoInfoResultData) {
            with(touTiaoInfoResultData) {
                titleTV.text = title
                authorTV.text = author_name
                dateTV.text = date
                Picasso.with(view.context)
                        .load(thumbnail_pic_s)
                        .placeholder(R.drawable.toutiao_default)
                        .error(R.drawable.toutiao_default)
                        .into(img)
                itemView.setOnClickListener { itemClickListener(touTiaoInfoResultData) }
            }
        }
    }
}