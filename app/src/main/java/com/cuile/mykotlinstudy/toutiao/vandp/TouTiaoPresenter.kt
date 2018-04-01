package com.cuile.mykotlinstudy.toutiao.vandp

import com.cuile.mykotlinstudy.DataInterface
import com.cuile.mykotlinstudy.DataRequestCallBack
import com.cuile.mykotlinstudy.toutiao.data.TouTiaoRequest

/**
 * Created by 崔乐 on 2017/5/27.
 *
 */
class TouTiaoPresenter(val toutiaoView: TouTiaoContract.View) : TouTiaoContract.Presenter, DataRequestCallBack {
    override fun requestMoreSuccess(dataInfo: DataInterface) {
        toutiaoView.addList(dataInfo)
    }

    override fun requestSuccess(dataInterface: DataInterface) {
        toutiaoView.refreshList(dataInterface)
        toutiaoView.hideLoadingBar()

    }

    override fun requestFailed() {
        toutiaoView.hideLoadingBar()
        toutiaoView.refreshFailed()
    }

    init {
        // view绑定presenter
        toutiaoView.setPresenter(this)
    }

    override fun requestDatas(type: String) {
        toutiaoView.showLoadingBar()
        TouTiaoRequest(this).run(type, false)
    }
}