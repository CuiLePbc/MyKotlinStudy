package com.cuile.mykotlinstudy.toutiao.vandp

import com.cuile.mykotlinstudy.BasePresenter
import com.cuile.mykotlinstudy.BaseView
import com.cuile.mykotlinstudy.toutiao.data.TouTiaoInfo

/**
 * Created by 崔乐 on 2017/5/27.
 */
interface TouTiaoContract {
    interface View : BaseView<Presenter> {
        fun refreshList(datas: TouTiaoInfo)
        fun isActive(): Boolean
        fun refreshFailed()

    }
    interface Presenter : BasePresenter {
        fun requestDatas(type: String)
    }
}