package com.menilv.feature.home

import androidx.lifecycle.Lifecycle
import com.menilv.R
import com.menilv.common.BaseFragment
import com.menilv.databinding.FragmentHomeBinding
import com.menilv.util.rx.RxLifecycle
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFullViewState, FragmentHomeBinding>(R.layout.fragment_home), HomeView {

    @Inject
    override lateinit var presenter: HomePresenter

    override fun onLoad(): Observable<Lifecycle.Event> = RxLifecycle.with(this).onStart()

    override fun render(viewState: HomeFullViewState) {
        bindingData.data.set(viewState)
        showError(viewState.error)
    }

}
