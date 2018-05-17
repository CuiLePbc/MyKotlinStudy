package com.cuile.mykotlinstudy.zhihu.vandp


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.intfac.DataInterface
import com.cuile.mykotlinstudy.intfac.OnFragmentInteractionListener
import com.cuile.mykotlinstudy.zhihu.data.*
import com.cuile.mykotlinstudy.zhihu.vandp.adapter.ZhiHuListAdapter
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
    private var currentThemeId: Int = -1

    private val zhihuListAdapter: ZhiHuListAdapter = ZhiHuListAdapter()
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

        zhihu_theme_menu_fab.isClickable = false
        zhihu_theme_menu_fab.setOnClickListener {
            if (themes != null)
                ZhihuThemesDialogFragment(themes,this).show(activity?.supportFragmentManager, "dialog")
        }

        zhihu_data_list.layoutManager = LinearLayoutManager(activity)
        zhihu_data_list.adapter = zhihuListAdapter

        zhihu_swip_refresh.setOnRefreshListener { refreshDatas() }

        zhihuPresenter.requestThemesList()
        zhihuPresenter.requestTodayHot()
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

    private fun refreshDatas(){
        zhihuListAdapter.clearAll()
        if (currentThemeId == -1) {
            zhihuPresenter.requestTodayHot()
        } else {
            zhihuPresenter.requestTodayThemeNews(currentThemeId)
        }
    }
    override fun themeSelected(theme: ThemeBody) {
        zhihuListAdapter.clearAll()
        currentThemeId = theme.id
        zhihuPresenter.requestTodayThemeNews(currentThemeId)

//        val headView = LayoutInflater.from(context).inflate(R.layout.item_zhihulist_head, null)
//        zhihuListAdapter.setHeadView(headView)
    }

    override fun refreshTodayHot(zhihuLatestNews: ZhihuLatestNews) {
        zhihuListAdapter.addDatas(ZhihuListItem.changeIntoThis(zhihuLatestNews.stories))
    }

    override fun refreshMoreHot(zhihuHistoryNews: ZhihuHistoryNews) {
        zhihuListAdapter.addDatas(ZhihuListItem.changeIntoThis(zhihuHistoryNews.stories))
    }

    override fun refreshTodayThemeNews(zhihuThemeNews: ZhihuThemeNews) {
        zhihuListAdapter.addDatas(ZhihuListItem.changeIntoThis(zhihuThemeNews.stories))
    }

    override fun refreshMoreThemeNews(zhihuThemeNews: ZhihuThemeNews) {
        zhihuListAdapter.addDatas(ZhihuListItem.changeIntoThis(zhihuThemeNews.stories))
    }

    override fun refreshNewsDetail(zhihuDetailEntity: ZhihuDetailEntity) {

    }

    override fun setPresenter(presenter: ZhihuContract.Presenter) {
        this.zhihuPresenter = presenter
    }

    override fun showLoadingBar() {
        zhihu_swip_refresh.post { zhihu_swip_refresh.isRefreshing = true }
    }

    override fun hideLoadingBar() {
        zhihu_swip_refresh.post { zhihu_swip_refresh.isRefreshing = false }
    }

    override fun isActive(): Boolean = isAdded

    override fun refreshList(datas: DataInterface){}

    override fun addList(datas: DataInterface) {}

    override fun refreshFailed() {
        activity?.longToast("Loading Failed!")
    }

    override fun getThemesList(zhihuThemes: ZhihuThemes) {
        themes = zhihuThemes
        zhihu_theme_menu_fab.isClickable = true
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
