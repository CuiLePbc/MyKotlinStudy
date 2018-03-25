package com.cuile.mykotlinstudy.wechat.vandp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.wechat.data.WeChatInfo
import com.cuile.mykotlinstudy.wechat.vandp.adapter.WechatListAdapter
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [WeChatFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [WeChatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeChatFragment : Fragment(), WeChatContract.View {

    lateinit var weChatRecyclerView: RecyclerView
    lateinit var weChatListAdapter: WechatListAdapter
    lateinit var weChatSwipeRefreshLayout: SwipeRefreshLayout

    private var weChatPresenter: WeChatContract.Presenter? = null
    init {
        weChatPresenter = WeChatPresenter(this)
    }

    override fun setPresenter(presenter: WeChatContract.Presenter) {
        this.weChatPresenter = presenter
    }

    override fun showLoadingBar() {
        weChatSwipeRefreshLayout.post { weChatSwipeRefreshLayout.isRefreshing = true }
    }

    override fun hideLoadingBar() {
        weChatSwipeRefreshLayout.post { weChatSwipeRefreshLayout.isRefreshing = false }
    }

    override fun isActive(): Boolean = isAdded

    override fun refreshList(datas: WeChatInfo) {
        weChatListAdapter.items.clear()
        weChatListAdapter.items.addAll(datas.result.list)
        weChatListAdapter.notifyDataSetChanged()
    }

    override fun refreshFailed() {
        activity.longToast("加载数据失败")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weChatRecyclerView = view.find(R.id.toutiao_list)
        weChatSwipeRefreshLayout = view.find(R.id.toutiao_swip_refresh)

        weChatListAdapter = WechatListAdapter()
        weChatRecyclerView.layoutManager = LinearLayoutManager(activity)
        weChatRecyclerView.adapter = weChatListAdapter

        weChatSwipeRefreshLayout.setOnRefreshListener { weChatPresenter?.requestDatas(1) }
        weChatPresenter?.requestDatas(1)
    }

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
            inflater!!.inflate(R.layout.fragment_tou_tiao, container, false)

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WeChatFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String = "", param2: String = ""): WeChatFragment {
            val fragment = WeChatFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
