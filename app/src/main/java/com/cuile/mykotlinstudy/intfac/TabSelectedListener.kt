package com.cuile.mykotlinstudy.intfac

import com.flyco.tablayout.listener.OnTabSelectListener


/**
 * Created by cuile on 2018/4/6.
 */
class TabSelectedListener(private val op: (Int) -> Unit) : OnTabSelectListener {
    override fun onTabSelect(position: Int) {
        op(position)
    }

    override fun onTabReselect(position: Int) {

    }

}