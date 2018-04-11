package com.cuile.mykotlinstudy.intfac

import android.view.View

/**
 * Created by cuile on 2018/3/31.
 *
 */
interface OnFragmentInteractionListener {
    fun onFragmentInteraction(itemData: DataItemInterface, view: View)
    fun onActivityTitleChange(newTitle: String)
}