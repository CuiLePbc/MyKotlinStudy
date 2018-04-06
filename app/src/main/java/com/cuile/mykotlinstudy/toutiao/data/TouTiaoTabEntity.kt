package com.cuile.mykotlinstudy.toutiao.data

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * Created by cuile on 2018/4/6.
 *
 */
class TouTiaoTabEntity(val title: String, val selectedIcon: Int, val unSelectedIcon: Int) : CustomTabEntity {

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabTitle(): String {
        return title
    }

}