package com.cuile.mykotlinstudy.wechat.vandp

import com.cuile.mykotlinstudy.intfac.DataInterface
import com.cuile.mykotlinstudy.intfac.DataRequestCallBack
import com.cuile.mykotlinstudy.wechat.data.WeChatRequest

/**
 * Created by cuile on 2018/3/24.
 *
 */
class WeChatPresenter(private val weChatView: WeChatContract.View) : WeChatContract.Presenter, DataRequestCallBack {
    override fun requestMoreDatas(pageNo: Int) {
        WeChatRequest(weChatCallBack = this).run(pageNo, true)
    }


    override fun requestSuccess(dataInterface: DataInterface) {
        weChatView.refreshList(dataInterface)
        weChatView.hideLoadingBar()
    }

    override fun requestFailed() {
        weChatView.refreshFailed()
        weChatView.hideLoadingBar()
    }

    init {
        weChatView.setPresenter(this)
    }

    override fun requestMoreSuccess(dataInfo: DataInterface) {
        weChatView.addList(dataInfo)
    }

    override fun requestDatas(pageNo: Int) {
        weChatView.showLoadingBar()
        WeChatRequest(weChatCallBack = this).run(pageNo, false)
    }
}