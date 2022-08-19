package com.menilv.common

import androidx.annotation.CallSuper
import java.io.File
import java.net.InetAddress
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.QueueDispatcher
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.validateMockitoUsage
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(application = TestApp_Application::class)
@RunWith(RobolectricTestRunner::class)
abstract class BaseTest {
    @get:Rule
    val testSchedulerRule = TestSchedulerRule()
    protected val webServer: MockWebServer = MockWebServer()

    @Before
    @CallSuper
    open fun setUp() {
        MockitoAnnotations.openMocks(this)
        webServer.dispatcher = getMockWebServerQueueDispatcher()
        webServer.start(InetAddress.getByName("localhost"), 8181)
    }

    @After
    open fun tearDown() {
        webServer.shutdown()
        validateMockitoUsage()
    }

    fun getJson(path: String): String {
        val absPath = "src/test/resources"
        val file = File(absPath + path)
        return String(file.readBytes())
    }

    fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

    private fun getMockWebServerQueueDispatcher(): QueueDispatcher {
        return object : QueueDispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/search/link-items?limit=15&q=Google" -> {
                        MockResponse().setResponseCode(200).setBody(getJson("/searchItems.json"))
                    }
                    "/search/link-items?limit=15&q=ArgyleLink" -> {
                        MockResponse().setResponseCode(200).setBody("{\"results\":[]}")
                    }
                    else -> {
                        MockResponse().setResponseCode(400)
                    }
                }
            }
        }
    }
}
