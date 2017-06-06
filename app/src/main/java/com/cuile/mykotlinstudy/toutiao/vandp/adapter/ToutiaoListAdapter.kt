package com.cuile.mykotlinstudy.toutiao.vandp.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.cuile.mykotlinstudy.toutiao.data.TouTiaoInfo
import com.cuile.mykotlinstudy.toutiao.data.TouTiaoInfoResultData
import java.util.*

/**
 * Created by 崔乐 on 2017/5/22.
 */
class ToutiaoListAdapter(var items: MutableList<TouTiaoInfoResultData> = Collections.emptyList()) : RecyclerView.Adapter<ToutiaoListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position].title
    }

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}