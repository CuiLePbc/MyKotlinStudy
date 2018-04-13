package com.cuile.mykotlinstudy.zhihu.vandp

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.BottomSheetDialogFragment
import android.view.View
import com.cuile.mykotlinstudy.R

/**
 * Created by cuile on 18-4-13.
 * TODO 使用示例:ZhihuThemesDialogFragment().show(getSupportFragmentManager(), "dialog")
 *
 */
class ZhihuThemesDialogFragment: BottomSheetDialogFragment() {
    private lateinit var mBehavior: BottomSheetBehavior<View>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val view = View.inflate(context, R.layout.zhihu_theme_dialog, null)
        dialog.setContentView(view)
        mBehavior = BottomSheetBehavior.from(view.parent as View)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    fun doclick(v: View) {


        // 点击任意布局关闭
        mBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }
}