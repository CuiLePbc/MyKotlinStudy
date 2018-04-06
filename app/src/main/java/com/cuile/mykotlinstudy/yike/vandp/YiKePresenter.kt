package com.cuile.mykotlinstudy.yike.vandp

import com.cuile.mykotlinstudy.intfac.DataInterface
import com.cuile.mykotlinstudy.intfac.DataRequestCallBack
import com.cuile.mykotlinstudy.yike.data.YiKeRequest

/**
 * Created by cuile on 2018/3/31.
 *
 */
class YiKePresenter(private val yiKeView: YiKeContract.View) : YiKeContract.Presenter, DataRequestCallBack {



    init {
        yiKeView.setPresenter(this)
    }

    override fun requestSuccess(dataInfo: DataInterface) {

        yiKeView.refreshList(dataInfo)
        yiKeView.hideLoadingBar()
    }

    override fun requestMoreSuccess(dataInfo: DataInterface) {
        yiKeView.addList(dataInfo)
    }

    override fun requestFailed() {
        yiKeView.refreshFailed()
        yiKeView.hideLoadingBar()
    }

    override fun requestDatas(isPic: Boolean) {
        yiKeView.showLoadingBar()
        YiKeRequest(this).run(isPic, false)
    }

    override fun requestMore(isPic: Boolean) {
        YiKeRequest(this).run(isPic, true)
    }

}