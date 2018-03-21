package com.cuile.mykotlinstudy.wechat.vandp

import com.cuile.mykotlinstudy.BasePresenter
import com.cuile.mykotlinstudy.BaseView

/**
 * Created by cuile on 2018/3/20.
 */
interface WeChatContract {
    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter {
        fun requestDatas(pageNo: Int)
    }
}