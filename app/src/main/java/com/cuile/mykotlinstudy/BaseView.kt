package com.cuile.mykotlinstudy

/**
 * Created by 崔乐 on 2017/5/26.
 */
interface BaseView<T> {
    fun setPresenter(presenter: T)
    fun showLoadingBar()
    fun hideLoadingBar()
    fun isActive(): Boolean
}