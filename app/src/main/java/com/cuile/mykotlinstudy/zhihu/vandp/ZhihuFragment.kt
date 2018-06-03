package com.cuile.mykotlinstudy.zhihu.vandp


import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.intfac.DataInterface
import com.cuile.mykotlinstudy.intfac.EndLessOnScrollListener
import com.cuile.mykotlinstudy.intfac.OnFragmentInteractionListener
import com.cuile.mykotlinstudy.zhihu.data.*
import com.cuile.mykotlinstudy.zhihu.vandp.adapter.ZhiHuListAdapter
import com.cuile.mykotlinstudy.zhihu.vandp.diaog.ZhihuThemesDialogFragment
import com.cuile.mykotlinstudy.zhihu.vandp.diaog.ZhihuThemesDialogSelectedListener
import kotlinx.android.synthetic.main.fragment_zhihu.*
import org.jetbrains.anko.longToast
import java.time.Month
import java.util.*

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
    private var endLessOnScrollListener: EndLessOnScrollListener? = null

    private lateinit var zhihuListAdapter: ZhiHuListAdapter

    private var currentDate = Date(System.currentTimeMillis())
    private fun getLastDayDate(date: Date): Date {
        val cal = Calendar.getInstance()
        cal.time = date
        var year = cal.get(Calendar.YEAR)
        var month = cal.get(Calendar.MONTH)
        var day = cal.get(Calendar.DAY_OF_MONTH)

        if (day == 1) {
            if (month - 1 in listOf(1, 3, 5, 7, 8, 10, 12)) {
                day = 31
                month -= 1
            } else if (month - 1 in listOf(4, 6, 9, 11)) {
                day = 30
                month -= 1
            } else if (month - 1 == 2) {
                if (year % 4 == 0) {
                    if (year % 100 == 0) {
                        if (year % 400 == 0) {
                            day = 29
                            month -= 1
                        } else {
                            day = 28
                            month -= 1
                        }
                    } else {
                        day = 29
                        month -= 1
                    }
                } else {
                    day = 28
                    month -= 1
                }
            } else if (month == 1) {
                day = 31
                month = 12
                year -= 1
            }
        } else {
            day -= 1
        }

        cal.set(year, month, day)

        return cal.time
    }

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

        zhihu_theme_menu_fab.visibility = View.GONE
        zhihu_theme_menu_fab.isClickable = false
        zhihu_theme_menu_fab.setOnClickListener {
            if (themes != null)
                ZhihuThemesDialogFragment(themes,this).show(activity?.supportFragmentManager, "dialog")
        }

        zhihuListAdapter = ZhiHuListAdapter{ zhihuListItem: ZhihuListItem, view: View ->
            onZhihuItemClicked(zhihuListItem, view)
        }

        zhihu_data_list.setHasFixedSize(true)
        zhihu_data_list.layoutManager = LinearLayoutManager(activity)
        zhihu_data_list.adapter = zhihuListAdapter

        zhihu_data_list.addOnScrollListener(endLessOnScrollListener)

        zhihu_swip_refresh.setOnRefreshListener { refreshDatas() }

        zhihuPresenter.requestThemesList()
        zhihuPresenter.requestTodayHot()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        endLessOnScrollListener = EndLessOnScrollListener {
            //TODO hello
            currentDate = getLastDayDate(currentDate)
            zhihuPresenter.requestMoreHot(SimpleDateFormat("yyyyMMdd").format(currentDate))
        }
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        endLessOnScrollListener = null
        listener = null
    }

    private fun onZhihuItemClicked(zhihuListItem: ZhihuListItem, view: View) {
        listener?.onFragmentInteraction(zhihuListItem, view)
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
