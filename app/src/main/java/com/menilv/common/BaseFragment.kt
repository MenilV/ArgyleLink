package com.menilv.common

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.menilv.extension.toErrorMessage
import com.menilv.presenter.Presenter

abstract class BaseFragment<FVS : Parcelable, VB : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    Fragment(layoutId) {
    abstract val presenter: Presenter<FVS>?
    val bindingData = BindingData()
    protected lateinit var binding: VB
    private var snackbar: Snackbar? = null

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter?.savedState = savedInstanceState?.getParcelable("state")
        retainInstance = true
    }

    final override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("state", presenter?.latestViewState)
    }

    open fun initUI() {
        binding.setVariable(BR.context, this)
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    final override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    @Suppress("UNCHECKED_CAST")
    final override fun onStart() {
        super.onStart()
        presenter?.attachView(this as BaseView<FVS>)
    }

    final override fun onResume() {
        super.onResume()
    }

    final override fun onPause() {
        super.onPause()
    }

    final override fun onStop() {
        super.onStop()
        presenter?.detachView()
    }

    final override fun onDestroy() {
        super.onDestroy()
        if (activity?.isChangingConfigurations == false) {
            presenter?.destroy()
        }
    }

    protected fun showSuccess(message: String?) {
        if (message == null && snackbar?.isShown == true) {
            snackbar?.dismiss()
            return
        }

        if (message == null) {
            return
        }

        snackbar = view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG) }
        snackbar?.show()
    }

    protected fun showError(error: Throwable?) {
        if (error == null) return
        val message = getString(error.toErrorMessage().ordinal)
        if (snackbar?.isShown == true) return
        snackbar = view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG) }
        snackbar?.show()
    }

    inner class BindingData {
        val data: ObservableField<FVS> = ObservableField()
    }
}
