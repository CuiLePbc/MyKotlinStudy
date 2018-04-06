package com.cuile.mykotlinstudy.yike.vandp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cuile.mykotlinstudy.intfac.DataInterface
import com.cuile.mykotlinstudy.intfac.EndLessOnScrollListener
import com.cuile.mykotlinstudy.intfac.OnFragmentInteractionListener
import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.yike.data.YiKeInfo
import com.cuile.mykotlinstudy.yike.vandp.adapter.YiKeListAdapter
import kotlinx.android.synthetic.main.fragment_datas.*
import org.jetbrains.anko.longToast

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [YiKeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class YiKeFragment : Fragment(), YiKeContract.View {
    private var endLessOnScrollListener: EndLessOnScrollListener? = null
    private lateinit var yiKeListAdapter: YiKeListAdapter

    private var yiKePresenter: YiKeContract.Presenter
    init {
        yiKePresenter = YiKePresenter(this)
    }

    override fun setPresenter(presenter: YiKeContract.Presenter) {
        this.yiKePresenter = presenter
    }

    override fun showLoadingBar() {
        data_swip_refresh.post { data_swip_refresh.isRefreshing = true }
    }

    override fun hideLoadingBar() {
        data_swip_refresh.post { data_swip_refresh.isRefreshing = false }
    }

    override fun addList(datas: DataInterface) {
        yiKeListAdapter.items.addAll((datas as YiKeInfo).result)
        yiKeListAdapter.notifyDataSetChanged()
    }
    override fun isActive(): Boolean = isAdded

    override fun refreshList(datas: DataInterface) {
        yiKeListAdapter.items.clear()
        yiKeListAdapter.items.addAll((datas as YiKeInfo).result)
        yiKeListAdapter.notifyDataSetChanged()
    }

    override fun refreshFailed() {
        activity.longToast("加载数据失败")
    }

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

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        yiKeListAdapter = YiKeListAdapter()

        data_list.layoutManager = LinearLayoutManager(activity)
        data_list.adapter = yiKeListAdapter
        data_list.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        data_list.addOnScrollListener(endLessOnScrollListener)

        data_swip_refresh.setOnRefreshListener { yiKePresenter.requestDatas(false) }
        yiKePresenter.requestDatas(true)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        endLessOnScrollListener = EndLessOnScrollListener {
            yiKePresenter.requestMore(false)
        }
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
        endLessOnScrollListener = null
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
         * @return A new instance of fragment YiKeFragment.
         */
        fun newInstance(param1: String = "", param2: String = ""): YiKeFragment {
            val fragment = YiKeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
