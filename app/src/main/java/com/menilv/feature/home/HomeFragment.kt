package com.menilv.feature.home

import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.OrientationHelper
import com.menilv.R
import com.menilv.common.BaseFragment
import com.menilv.databinding.FragmentHomeBinding
import com.menilv.feature.home.adapter.SearchAdapter
import com.menilv.util.rx.RxLifecycle
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFullViewState, FragmentHomeBinding>(R.layout.fragment_home), HomeView {

    @Inject
    override lateinit var presenter: HomePresenter

    @Inject
    lateinit var searchAdapter: SearchAdapter

    override fun initUI() {
        super.initUI()
        binding.recyclerResults.apply{
            adapter = searchAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), OrientationHelper.VERTICAL))
        }
    }

    override fun onLoad(): Observable<Lifecycle.Event> = RxLifecycle.with(this).onStart()

    override fun render(viewState: HomeFullViewState) {
        bindingData.data.set(viewState)
        showError(viewState.error)
    }

}
