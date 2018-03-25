package com.cuile.mykotlinstudy.wechat.vandp

import com.cuile.mykotlinstudy.wechat.data.WeChatInfo
import com.cuile.mykotlinstudy.wechat.data.WeChatRequest

/**
 * Created by cuile on 2018/3/24.
 */
class WeChatPresenter(val weChatView: WeChatContract.View) : WeChatContract.Presenter, WeChatRequest.WeChatCallBack {
    override fun requestSuccess(weChatInfo: WeChatInfo) {
        weChatView.refreshList(weChatInfo)
    }

    override fun requestFailed() {
        weChatView.refreshFailed()
    }

    init {
        weChatView.setPresenter(this)
    }

    override fun start() {

    }

    override fun requestDatas(pageNo: Int) {
        weChatView.showLoadingBar()
        WeChatRequest(touTiaoCallBack = this).run()
    }
}