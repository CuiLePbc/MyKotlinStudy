package com.cuile.mykotlinstudy.yike.vandp

import com.cuile.mykotlinstudy.intfac.BasePresenter
import com.cuile.mykotlinstudy.intfac.BaseView

/**
 * Created by cuile on 2018/3/31.
 */
interface YiKeContract {
    interface View : BaseView<Presenter>
    interface Presenter : BasePresenter {
        fun requestDatas(isPic: Boolean)
        fun requestMore(isPic: Boolean)
    }
}