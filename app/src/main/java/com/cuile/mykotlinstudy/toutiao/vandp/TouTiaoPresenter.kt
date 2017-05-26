package com.cuile.mykotlinstudy.toutiao.vandp

import com.cuile.mykotlinstudy.toutiao.vandp.TouTiaoContract

/**
 * Created by 崔乐 on 2017/5/27.
 */
class TouTiaoPresenter(val toutiaoView: TouTiaoContract.View) : TouTiaoContract.Presenter {
    init {
        // view绑定presenter
        toutiaoView.setPresenter(this)
    }

    override fun start() {

    }

    override fun requestDatas(type: String) {

    }
}