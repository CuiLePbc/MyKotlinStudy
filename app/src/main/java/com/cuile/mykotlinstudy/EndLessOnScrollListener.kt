package com.cuile.mykotlinstudy

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

/**
 * Created by cuile on 2018/4/1.
 *
 */
class EndLessOnScrollListener(private val op: (currentCount: Int) -> Unit) : RecyclerView.OnScrollListener() {

    var totalItemCount: Int = 0
    var previousItemCount: Int = 0

    var visiableItemCount: Int = 0
    var firstVisiableItemPosition: Int = 0

    var isLoading: Boolean = true

    var mCurrentPage: Int = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)



        visiableItemCount = recyclerView.childCount
        totalItemCount = (recyclerView.layoutManager as LinearLayoutManager).itemCount
        firstVisiableItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        Log.d("wnwn","firstVisibleItem: " + firstVisiableItemPosition)
        Log.d("wnwn","totalItemCount:" + totalItemCount)
        Log.d("wnwn", "visibleItemCount:" + visiableItemCount)

        if (isLoading) {
            if (totalItemCount > previousItemCount) {
                isLoading = false
                previousItemCount = totalItemCount
            }
        }

        if (!isLoading && totalItemCount - visiableItemCount <= firstVisiableItemPosition) {
            mCurrentPage ++
            onLoadMore(mCurrentPage)
            isLoading = true
        }

    }

    private fun onLoadMore(currentPage: Int) {
        op(currentPage)
    }
}