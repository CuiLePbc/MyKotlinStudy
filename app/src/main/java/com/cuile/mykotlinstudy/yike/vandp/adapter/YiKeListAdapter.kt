package com.cuile.mykotlinstudy.yike.vandp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.cuile.mykotlinstudy.GlideApp
import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.yike.data.YiKeInfoResultData
import org.jetbrains.anko.find

/**
 * Created by cuile on 2018/3/31.
 *
 */
class YiKeListAdapter(var items: MutableList<YiKeInfoResultData> = mutableListOf()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ITEM_TYPE { ITEM_TYPE_IMG, ITEM_TYPE_TXT }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            if (viewType == ITEM_TYPE.ITEM_TYPE_TXT.ordinal) {
                ViewHolderTXT(LayoutInflater.from(parent.context).inflate(R.layout.item_yike_list_txt, null))
            } else {
                ViewHolderIMG(LayoutInflater.from(parent.context).inflate(R.layout.item_yike_list_img, null))
            }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int =
            if (items[position].url == null)
                ITEM_TYPE.ITEM_TYPE_TXT.ordinal
            else
                ITEM_TYPE.ITEM_TYPE_IMG.ordinal


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ViewHolderTXT -> {
                holder.contentTV.text = items[position].content
            }
            is ViewHolderIMG -> {
                holder.titleTV.text = items[position].content
                GlideApp.with(holder.contentIV.context).clear(holder.contentIV)
                GlideApp.with(holder.contentIV.context)
                        .load(items[position].url)
                        .centerCrop()
                        .placeholder(R.drawable.toutiao_default)
                        .error(R.drawable.toutiao_default)
                        .into(holder.contentIV)
            }
        }
    }

    class ViewHolderTXT(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contentTV: TextView = itemView.find(R.id.content)
    }

    class ViewHolderIMG(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTV: TextView = itemView.find(R.id.title)
        val contentIV: ImageView = itemView.find(R.id.img)
    }
}