package io.rede.bookfront.view.ui.activity


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import io.rede.bookfront.BookfrontTestApp
import io.rede.bookfront.FileReader
import io.rede.bookfront.R
import io.rede.bookfront.network.GetBookData
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    private lateinit var mApiService: GetBookData
    private val mMockWebServer = MockWebServer()

    @Before
    fun setup() {
        mMockWebServer.start(8080)
        mApiService = Retrofit.Builder().apply {
            baseUrl(BookfrontTestApp().getBaseUrl())
            addConverterFactory(GsonConverterFactory.create())
        }.build().create(GetBookData::class.java)
    }

    @After
    fun teardown() {
        mMockWebServer.shutdown()
    }

    @Test
    fun testSuccessfulResponse() {
        mMockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(FileReader.readStringFromFile("success_response.json"))
            }
        }

        mActivityTestRule.launchActivity(null)

        onView(withId(R.id.booklist_recyclerview))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testFailedResponse() {
        mMockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().throttleBody(1024, 5, TimeUnit.SECONDS)
            }
        }
    }

}
