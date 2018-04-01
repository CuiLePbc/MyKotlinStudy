package com.cuile.mykotlinstudy

/**
 * Created by cuile on 2018/3/31.
 */
interface DataRequestCallBack {

    fun requestSuccess(dataInfo: DataInterface)
    fun requestMoreSuccess(dataInfo: DataInterface)
    fun requestFailed()
}