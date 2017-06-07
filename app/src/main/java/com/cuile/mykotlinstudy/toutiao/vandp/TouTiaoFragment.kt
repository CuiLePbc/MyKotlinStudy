package com.cuile.mykotlinstudy.toutiao.vandp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cuile.mykotlinstudy.R
import com.cuile.mykotlinstudy.toutiao.data.TouTiaoInfo
import com.cuile.mykotlinstudy.toutiao.data.TouTiaoInfoResultData
import com.cuile.mykotlinstudy.toutiao.vandp.adapter.ToutiaoListAdapter
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TouTiaoFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TouTiaoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TouTiaoFragment : Fragment(), TouTiaoContract.View, ToutiaoListAdapter.OnItemClickListener {

    var toutiaoPresenter: TouTiaoContract.Presenter? = null

    init {
        toutiaoPresenter = TouTiaoPresenter(this)
    }

    var toutiaoAdapter = ToutiaoListAdapter(mutableListOf())
    lateinit var toutiaoRecyclerView: RecyclerView
    lateinit var toutiaoSwipRefreshLayout: SwipeRefreshLayout


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
    override fun refreshList(datas: TouTiaoInfo) {
        toutiaoAdapter.items.clear()
        toutiaoAdapter.items.addAll(datas.result.data)
        toutiaoAdapter.notifyDataSetChanged()
    }


    /**
     * 显示进度条
     */
    override fun showLoadingBar() {
        toutiaoSwipRefreshLayout.post { toutiaoSwipRefreshLayout.isRefreshing = true }
    }

    /**
     * 隐藏进度条
     */
    override fun hideLoadingBar() {
        toutiaoSwipRefreshLayout.post { toutiaoSwipRefreshLayout.isRefreshing = false }
    }

    /**
     * 当前view是否存活
     */
    override fun isActive(): Boolean {
        return isAdded
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
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_tou_tiao, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toutiaoRecyclerView = view.find<RecyclerView>(R.id.toutiao_list)
        toutiaoSwipRefreshLayout = view.find<SwipeRefreshLayout>(R.id.toutiao_swip_refresh)

        toutiaoRecyclerView.layoutManager = LinearLayoutManager(activity)
        toutiaoRecyclerView.adapter = toutiaoAdapter

        toutiaoSwipRefreshLayout.setOnRefreshListener { toutiaoPresenter?.requestDatas("top") }
        toutiaoPresenter?.requestDatas("top")
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context as OnFragmentInteractionListener?
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
