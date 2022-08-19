package com.menilv.feature.home

import com.menilv.common.BasePresenter
import com.menilv.feature.home.adapter.SearchItem
import com.menilv.model.payload.SearchPayload
import com.menilv.network.repository.SearchDataRepository
import io.reactivex.Observable
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val searchDataRepository: SearchDataRepository
) : BasePresenter<HomeView, HomeViewState, HomeFullViewState>(startWithInitialState = true) {

    override fun getInitialState(): HomeFullViewState = HomeFullViewState()

    override fun bindIntents() {
        val onSearch = intent(HomeView::onSearch)
            .switchMapToViewState(
                {
                    searchDataRepository.fetch(SearchPayload(it))
                        .map { response ->
                            response.results.map { result ->
                                SearchItem(result.name, result.kind, result.logoUrl)
                            }
                        }
                },
                { HomeSuccessViewState(it) },
                { throwable, _ -> HomeErrorViewState(throwable) },
                { HomeLoadingViewState(true) }
            )

        val onReset = intent(HomeView::onReset)
            .switchMapToViewState(
                { Observable.just(listOf<SearchItem>())},
                { HomeSuccessViewState(it) },
                { throwable, _ -> HomeErrorViewState(throwable) }
            )

        subscribeForViewStateChanges(onSearch, onReset)
    }

    override fun viewStateReducer(
        previousState: HomeFullViewState,
        changes: HomeViewState
    ): HomeFullViewState {
        return when (changes) {
            is HomeSuccessViewState -> previousState.copy(results = changes.results, error = null, loading = false)
            is HomeLoadingViewState -> previousState.copy(loading = changes.loading)
            is HomeErrorViewState -> previousState.copy(error = changes.error, loading = false)
        }
    }
}
