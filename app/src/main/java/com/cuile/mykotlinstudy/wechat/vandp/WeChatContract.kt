package com.cuile.mykotlinstudy.wechat.vandp

import com.cuile.mykotlinstudy.BasePresenter
import com.cuile.mykotlinstudy.BaseView
import com.cuile.mykotlinstudy.wechat.data.WeChatInfo

/**
 * Created by cuile on 2018/3/20.
 *
 */
interface WeChatContract {
    interface View: BaseView<Presenter> {
        fun refreshList(datas: WeChatInfo)
        fun refreshFailed()
    }

    interface Presenter: BasePresenter {
        fun requestDatas(pageNo: Int)
    }
}