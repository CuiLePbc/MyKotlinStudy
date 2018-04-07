package com.cuile.mykotlinstudy.toutiao.vandp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.intfac.DataInterface
import com.cuile.mykotlinstudy.intfac.OnFragmentInteractionListener
import com.cuile.mykotlinstudy.intfac.TabSelectedListener
import com.cuile.mykotlinstudy.toutiao.data.TouTiaoInfo
import com.cuile.mykotlinstudy.toutiao.data.TouTiaoInfoResultData
import com.cuile.mykotlinstudy.DataTypeTabEntity
import com.cuile.mykotlinstudy.toutiao.vandp.adapter.ToutiaoListAdapter
import com.flyco.tablayout.listener.CustomTabEntity
import kotlinx.android.synthetic.main.fragment_datas.*
import org.jetbrains.anko.longToast

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TouTiaoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TouTiaoFragment : Fragment(), TouTiaoContract.View {


    private val TYPE_NAME = mapOf(
            Pair("头条", "top"),
            Pair("社会", "shehui"),
            Pair("国内", "guonei"),
            Pair("国际", "guoji"),
            Pair("娱乐", "yule"),
            Pair("体育", "tiyu"),
            Pair("军事", "junshi"),
            Pair("科技", "keji"),
            Pair("财经", "caijing"),
            Pair("时尚", "shishang")
    )
    private val titleList: ArrayList<CustomTabEntity> = arrayListOf(
            DataTypeTabEntity("头条", R.drawable.navigation_empty_icon, R.drawable.navigation_empty_icon),
            DataTypeTabEntity("社会", R.drawable.navigation_empty_icon, R.drawable.navigation_empty_icon),
            DataTypeTabEntity("国内", R.drawable.navigation_empty_icon, R.drawable.navigation_empty_icon),
            DataTypeTabEntity("国际", R.drawable.navigation_empty_icon, R.drawable.navigation_empty_icon),
            DataTypeTabEntity("娱乐", R.drawable.navigation_empty_icon, R.drawable.navigation_empty_icon),
            DataTypeTabEntity("军事", R.drawable.navigation_empty_icon, R.drawable.navigation_empty_icon),
            DataTypeTabEntity("科技", R.drawable.navigation_empty_icon, R.drawable.navigation_empty_icon),
            DataTypeTabEntity("财经", R.drawable.navigation_empty_icon, R.drawable.navigation_empty_icon),
            DataTypeTabEntity("时尚", R.drawable.navigation_empty_icon, R.drawable.navigation_empty_icon),
            DataTypeTabEntity("体育", R.drawable.navigation_empty_icon, R.drawable.navigation_empty_icon)
    )


    override fun addList(datas: DataInterface) {

    }

    private var toutiaoPresenter: TouTiaoContract.Presenter? = null

    init {
        toutiaoPresenter = TouTiaoPresenter(this)
    }

    private lateinit var toutiaoAdapter: ToutiaoListAdapter

    override fun refreshFailed() {
        activity.longToast("加载数据失败")
    }

    /**
     * 绑定presenter
     */
    override fun setPresenter(presenter: TouTiaoContract.Presenter) {
        toutiaoPresenter = presenter
    }

    /**
     * 刷新列表
     */
    override fun refreshList(datas: DataInterface) {
        toutiaoAdapter.items.clear()
        toutiaoAdapter.notifyDataSetChanged()
        toutiaoAdapter.items.addAll((datas as TouTiaoInfo).result.data)
        toutiaoAdapter.notifyDataSetChanged()


    }


    /**
     * 显示进度条
     */
    override fun showLoadingBar() {
        data_swip_refresh.post { data_swip_refresh.isRefreshing = true }
    }

    /**
     * 隐藏进度条
     */
    override fun hideLoadingBar() {
        data_swip_refresh.post { data_swip_refresh.isRefreshing = false }
    }

    /**
     * 当前view是否存活
     */
    override fun isActive(): Boolean = isAdded

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.fragment_datas, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
        initTab()
        data_swip_refresh.setOnRefreshListener { refreshDatas(titleList[datatype_tablayout.currentTab].tabTitle!!) }
        initData()

    }

    private fun initData() {
        datatype_tablayout.currentTab = 0
        refreshDatas(titleList[datatype_tablayout.currentTab].tabTitle!!)
    }

    private fun initTab() {
        datatype_tablayout.setTabData(titleList)
        datatype_tablayout.setOnTabSelectListener(TabSelectedListener{
            refreshDatas(titleList[it].tabTitle!!)
        })
    }

    private fun initList() {
        toutiaoAdapter = ToutiaoListAdapter{ onItemClicked(it) }
        data_list.layoutManager = LinearLayoutManager(activity)
        data_list.adapter = toutiaoAdapter
        data_list.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
    }

    private fun refreshDatas(title: String) {
        toutiaoPresenter?.requestDatas(TYPE_NAME[title]!!)
        if (title == "头条") {
            mListener?.onActivityTitleChange("每日头条")
        } else {
            mListener?.onActivityTitleChange(title + "头条")
        }
    }


    private fun onItemClicked(touTiaoInfoResultData: TouTiaoInfoResultData) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(touTiaoInfoResultData)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param param1 Parameter 1.
         * *
         * @param param2 Parameter 2.
         * *
         * @return A new instance of fragment TouTiaoFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String = "", param2: String = ""): TouTiaoFragment {
            val fragment = TouTiaoFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
