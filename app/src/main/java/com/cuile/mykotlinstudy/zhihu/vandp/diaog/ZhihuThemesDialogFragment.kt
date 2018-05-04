package com.cuile.mykotlinstudy.zhihu.vandp.diaog

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.zhihu.data.ThemeBody
import com.cuile.mykotlinstudy.zhihu.data.ZhihuThemes
import org.jetbrains.anko.find

@SuppressLint("ValidFragment")
/**
 * Created by cuile on 18-4-13.
 * TODO 使用示例:ZhihuThemesDialogFragment().show(getSupportFragmentManager(), "dialog")
 *
 */
class ZhihuThemesDialogFragment(private val themes: ZhihuThemes?, private val themeSelectedListenerListener: ZhihuThemesDialogSelectedListener): BottomSheetDialogFragment() {
    private lateinit var mBehavior: BottomSheetBehavior<View>

    private lateinit var zhihuThemesRecyclerView: RecyclerView
    private var themeListAdapter: ZhihuThemesListAdapter? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val view = View.inflate(context, R.layout.zhihu_theme_dialog, null)
        dialog.setContentView(view)
        mBehavior = BottomSheetBehavior.from(view.parent as View)

        zhihuThemesRecyclerView = view.find(R.id.zhihu_dialog_themes)
        return dialog
    }

    private fun themeClicked(theme: ThemeBody, view: View) {
        closeDialog()
        themeSelectedListenerListener.themeSelected(theme)
    }

    override fun onStart() {
        super.onStart()
        mBehavior.state = BottomSheetBehavior.STATE_EXPANDED


        zhihuThemesRecyclerView.layoutManager = GridLayoutManager(context,2)
        themeListAdapter = ZhihuThemesListAdapter(themes?.others?.toMutableList()!!) { theme: ThemeBody, view: View ->
            themeClicked(theme, view)
        }
        zhihuThemesRecyclerView.adapter = themeListAdapter

    }

    private fun closeDialog() {
        mBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }
}