package com.cuile.mykotlinstudy.toutiao.vandp.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.cuile.mykotlinstudy.toutiao.data.TouTiaoInfo

/**
 * Created by 崔乐 on 2017/5/22.
 */
class ToutiaoListAdapter(val items: TouTiaoInfo?) : RecyclerView.Adapter<ToutiaoListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun getItemCount(): Int = items?.result?.data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (items != null && itemCount > 0)
            holder.textView.text = items.result.data[position].title

    }

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}