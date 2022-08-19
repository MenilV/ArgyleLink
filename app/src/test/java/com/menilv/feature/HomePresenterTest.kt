package com.menilv.feature

import com.menilv.common.BaseTest
import com.menilv.di.application.ApplicationModule
import com.menilv.di.fragment.FragmentModule
import com.menilv.feature.home.HomeFullViewState
import com.menilv.feature.home.HomePresenter
import com.menilv.feature.home.HomeView
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@UninstallModules(
    FragmentModule::class,
    ApplicationModule::class
)
@HiltAndroidTest
class HomePresenterTest @Inject constructor() : BaseTest() {

    @Inject
    lateinit var presenter: HomePresenter

    @Mock
    lateinit var view: HomeView

    @Captor
    lateinit var viewStateCaptor: ArgumentCaptor<HomeFullViewState>

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
        `when`(view.onSearch()).thenReturn(Observable.empty())
        `when`(view.onReset()).thenReturn(Observable.empty())
    }

    @Test
    fun testInitialState() {
        // Given

        // When
        presenter.attachView(view)
        testSchedulerRule.testScheduler.advanceTimeBy(1,TimeUnit.SECONDS)

        // Then
        verify(view).render(capture(viewStateCaptor))
        assert(viewStateCaptor.allValues.last().error == null)

        // Finally
        presenter.detachView()
    }

    @Test
    fun `Typing ArgyleLink in the search box should return empty list`() {
        // Given
        val searchText = "ArgyleLink"
        `when`(view.onSearch()).thenReturn(Observable.just(searchText))

        // When
        presenter.attachView(view)
        testSchedulerRule.testScheduler.advanceTimeBy(3, TimeUnit.SECONDS)

        // Then
        verify(view, atLeast(3)).render(capture(viewStateCaptor))
        assert(viewStateCaptor.allValues.last().results?.isEmpty() == true)
        assert(viewStateCaptor.allValues.last().loading==false)
        assert(viewStateCaptor.allValues.last().error==null)

        // Finally
        presenter.detachView()
    }

    @Test
    fun `Typing Google in the search box should return non-empty list`() {
        // Given
        val searchText = "Google"
        `when`(view.onSearch()).thenReturn(Observable.just(searchText))

        // When
        presenter.attachView(view)
        testSchedulerRule.testScheduler.advanceTimeBy(3, TimeUnit.SECONDS)

        // Then
        verify(view, atLeast(3)).render(capture(viewStateCaptor))
        assert(viewStateCaptor.allValues.last().results?.isNotEmpty() == true)
        assert(viewStateCaptor.allValues.last().loading==false)
        assert(viewStateCaptor.allValues.last().error==null)

        // Finally
        presenter.detachView()
    }

    @Test
    fun `Clicking cancel button should clear search results`() {
        // Given
        `when`(view.onReset()).thenReturn(Observable.just(Unit))

        // When
        presenter.attachView(view)
        testSchedulerRule.testScheduler.advanceTimeBy(3, TimeUnit.SECONDS)

        // Then
        verify(view, atLeast(2)).render(capture(viewStateCaptor))
        assert(viewStateCaptor.allValues.last().results?.isEmpty() == true)
        assert(viewStateCaptor.allValues.last().loading==false)
        assert(viewStateCaptor.allValues.last().error==null)

        // Finally
        presenter.detachView()
    }




}