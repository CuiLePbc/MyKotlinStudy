package com.cuile.mykotlinstudy.toutiao.vandp

import com.cuile.mykotlinstudy.intfac.BasePresenter
import com.cuile.mykotlinstudy.intfac.BaseView

/**
 * Created by 崔乐 on 2017/5/27.
 *
 */
interface TouTiaoContract {

    interface View : BaseView<Presenter>
    interface Presenter : BasePresenter {
        fun requestDatas(type: String)
    }
}