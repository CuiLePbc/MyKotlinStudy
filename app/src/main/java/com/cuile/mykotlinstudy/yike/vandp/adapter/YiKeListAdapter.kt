package com.cuile.mykotlinstudy.yike.vandp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.cuile.mykotlinstudy.GlideApp
import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.yike.data.YiKeInfoResultData
import org.jetbrains.anko.find

/**
 * Created by cuile on 2018/3/31.
 *
 */
class YiKeListAdapter(var items: MutableList<YiKeInfoResultData> = mutableListOf(), private val itemClickListener: (YiKeInfoResultData, View) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ITEM_TYPE { ITEM_TYPE_IMG, ITEM_TYPE_TXT }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            if (viewType == ITEM_TYPE.ITEM_TYPE_TXT.ordinal) {
                ViewHolderTXT(LayoutInflater.from(parent.context).inflate(R.layout.item_yike_list_txt, null, true), itemClickListener)
            } else {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_yike_list_img, null, true)
                val lp = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                itemView.layoutParams = lp
                ViewHolderIMG(itemView, itemClickListener)
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
                holder.bindView(items[position])
            }
            is ViewHolderIMG -> {
                holder.bindView(items[position])
            }
        }
    }

    class ViewHolderTXT(itemView: View, private val itemClickListener: (YiKeInfoResultData, View) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val contentTV: TextView = itemView.find(R.id.content)

        fun bindView(yiKeInfoResultData: YiKeInfoResultData) {
            with(yiKeInfoResultData) {
                contentTV.text = content
                itemView.setOnClickListener {
                    itemClickListener(this, it)
                }
            }
        }
    }

    class ViewHolderIMG(itemView: View, private val itemClickListener: (YiKeInfoResultData, View) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val titleTV: TextView = itemView.find(R.id.title)
        private val contentIV: ImageView = itemView.find(R.id.img)

        fun bindView(yiKeInfoResultData: YiKeInfoResultData) {
            with(yiKeInfoResultData) {
                titleTV.text = content
                GlideApp.with(contentIV.context)
                        .load(url)
                        .centerCrop()
                        .placeholder(R.drawable.toutiao_default)
                        .into(contentIV)

                itemView.setOnClickListener {
                    itemClickListener(this, contentIV)
                }
            }
        }
    }
}