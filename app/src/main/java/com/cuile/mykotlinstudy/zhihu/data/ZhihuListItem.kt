package com.cuile.mykotlinstudy.zhihu.data

import android.util.Log
import com.cuile.mykotlinstudy.intfac.DataInterface

/**
 * Created by cuile on 18-5-16.
 *
 */
data class ZhihuListItem(
        /** 图片 */
        val image: String,
        val type: Int,
        // 新闻ID
        val id: Int,
        // 新闻标题
        val title: String){
        companion object {
            fun changeIntoThis(data: Any): ZhihuListItem? {
                when(data) {
                    is Story -> {
                        return ZhihuListItem(if (data.images.isEmpty()) "" else data.images[0], data.type, data.id, data.title)
                    }
                    is TopStory -> {
                        return ZhihuListItem(data.image, data.type, data.id, data.title)
                    }
                    is ThemeStory -> {
                        return ZhihuListItem(if (data.images.isEmpty()) "" else data.images[0], data.type, data.id, data.title)
                    }
                }

                return null
            }

            fun changeIntoThis(datas: List<Any>): MutableList<ZhihuListItem> {
                val results = mutableListOf<ZhihuListItem>()
                datas.forEach {
                    if (changeIntoThis(it) == null) return results

                    results.add(this.changeIntoThis(it)!!)
                }
                Log.i("ZhihuLists", results.toString())
                return results
            }
        }
}