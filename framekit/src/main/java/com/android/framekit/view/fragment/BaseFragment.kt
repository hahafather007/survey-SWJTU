package com.android.framekit.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.framekit.contract.BaseContract
import com.android.framekit.view.dialog.LoadingDialog

abstract class BaseFragment<in V : BaseContract.BaseView, P : BaseContract.BasePresenter<V>>: Fragment(){
    protected var mPresenter: P? = null
    private var loading: LoadingDialog? = null;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initData()
        mPresenter = initPresenter()
        mPresenter!!.attachView(this as V)
        return inflater!!.inflate(getLayoutId(), container, false);
        configView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter!!.attachView(this as V)
    }

    fun showLoading(){
        if (loading == null) {
            loading = LoadingDialog()
            loading!!.setCallBack(object : LoadingDialog.OnDisMissCallBack {
                override fun disMiss() {
                }
            })
        }
        if (!loading!!.isVisible) {
            loading!!.show(activity?.supportFragmentManager!!, "Loading")
        }
    }
    fun disMissLoading(){
        loading!!.dismiss()
    }
    protected abstract fun getLayoutId():Int
    protected abstract fun configView()
    protected abstract fun initData()
    protected abstract fun initPresenter():P
}