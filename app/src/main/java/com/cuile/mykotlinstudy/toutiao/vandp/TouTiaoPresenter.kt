package com.cuile.mykotlinstudy.toutiao.vandp

import android.util.Log.i
import com.cuile.mykotlinstudy.toutiao.data.TouTiaoInfo
import com.cuile.mykotlinstudy.toutiao.data.TouTiaoRequest

/**
 * Created by 崔乐 on 2017/5/27.
 */
class TouTiaoPresenter(val toutiaoView: TouTiaoContract.View) : TouTiaoContract.Presenter, TouTiaoRequest.TouTiaoCallBack {
    override fun requestSuccess(touTiaoInfo: TouTiaoInfo) {
        i("refresh result", touTiaoInfo.reason)
        toutiaoView.refreshList(touTiaoInfo)
        toutiaoView.hideLoadingBar()
    }

    override fun requestFailed() {
        i("refresh result", "failed!!!")
        toutiaoView.refreshFailed()
        toutiaoView.hideLoadingBar()
    }

    init {
        // view绑定presenter
        toutiaoView.setPresenter(this)
    }

    override fun start() {

    }

    override fun requestDatas(type: String) {
        toutiaoView.showLoadingBar()
        TouTiaoRequest(type, this).run()
    }
}