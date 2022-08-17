package com.menilv.feature.home

import com.menilv.common.BasePresenter
import com.menilv.model.payload.SearchPayload
import com.menilv.network.repository.SearchDataRepository
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val searchDataRepository: SearchDataRepository
) : BasePresenter<HomeView, HomeViewState, HomeFullViewState>() {

    override fun getInitialState(): HomeFullViewState = HomeFullViewState()

    override fun bindIntents() {
        val onHome = intent(HomeView::onLoad)
            .switchMapToViewState(
                { searchDataRepository.fetch(SearchPayload("google")).map { it.toString() } },
                { HomeSuccessViewState(it) },
                { throwable, _ -> HomeErrorViewState(throwable) }
            )
        subscribeForViewStateChanges(onHome)
    }

    override fun viewStateReducer(
        previousState: HomeFullViewState,
        changes: HomeViewState
    ): HomeFullViewState {
        return when (changes) {
            is HomeSuccessViewState -> previousState.copy(result = changes.result, error = null)
            is HomeLoadingViewState -> previousState.copy(loading = changes.loading)
            is HomeErrorViewState -> previousState.copy(error = changes.error, loading = false)
        }
    }
}
