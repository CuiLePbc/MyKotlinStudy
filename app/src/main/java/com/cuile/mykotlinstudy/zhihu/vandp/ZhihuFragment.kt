package com.cuile.mykotlinstudy.zhihu.vandp


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.intfac.DataInterface
import com.cuile.mykotlinstudy.intfac.OnFragmentInteractionListener
import com.cuile.mykotlinstudy.zhihu.data.*
import com.cuile.mykotlinstudy.zhihu.vandp.diaog.ZhihuThemesDialogFragment
import com.cuile.mykotlinstudy.zhihu.vandp.diaog.ZhihuThemesDialogSelectedListener
import kotlinx.android.synthetic.main.fragment_zhihu.*
import org.jetbrains.anko.longToast
/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the [ZhihuFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ZhihuFragment : Fragment(), ZhihuContract.View, ZhihuThemesDialogSelectedListener {
    private var zhihuPresenter: ZhihuContract.Presenter
    init {
        zhihuPresenter = ZhihuPresenter(this)
    }

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private var themes: ZhihuThemes? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_zhihu, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        zhihuPresenter.requestThemesList()

        zhihu_theme_menu_fab.visibility = View.INVISIBLE
        zhihu_theme_menu_fab.setOnClickListener {
            if (themes != null)
                ZhihuThemesDialogFragment(themes,this).show(activity?.supportFragmentManager, "dialog")
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    override fun themeSelected(theme: ThemeBody) {
        activity?.longToast(theme.name)
    }

    override fun refreshTodayHot(zhihuLatestNews: ZhihuLatestNews) {

    }

    override fun refreshMoreHot(zhihuHistoryNews: ZhihuHistoryNews) {

    }

    override fun refreshTodayThemeNews(zhihuThemeNews: ZhihuThemeNews) {

    }

    override fun refreshMoreThemeNews(zhihuThemeNews: ZhihuThemeNews) {

    }

    override fun refreshNewsDetail(zhihuDetailEntity: ZhihuDetailEntity) {

    }

    override fun setPresenter(presenter: ZhihuContract.Presenter) {
        this.zhihuPresenter = presenter
    }

    override fun showLoadingBar() {

    }

    override fun hideLoadingBar() {

    }

    override fun isActive(): Boolean = isAdded

    override fun refreshList(datas: DataInterface){}

    override fun addList(datas: DataInterface) {}

    override fun refreshFailed() {
        activity?.longToast("Loading Failed!")
    }

    override fun getThemesList(zhihuThemes: ZhihuThemes) {
        themes = zhihuThemes
        zhihu_theme_menu_fab.visibility = View.VISIBLE
    }

    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ZhihuFragment.
         */
        @JvmStatic
        fun newInstance(param1: String = "", param2: String = ""): ZhihuFragment {
            val fragment = ZhihuFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }

    }
}
