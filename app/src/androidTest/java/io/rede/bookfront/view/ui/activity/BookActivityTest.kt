package io.rede.bookfront.view.ui.activity


import android.content.Context
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import io.rede.bookfront.R
import io.rede.bookfront.model.Book
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookActivityTest {

    @get:Rule
    var mActivityTestRule = ActivityTestRule(BookDetailActivity::class.java, true, false)

    private lateinit var mBook: Book

    @Before
    fun setup() {
        mBook = Book(
            1,
            3,
            "1234567897",
            "4321432138532",
            "Books Inc",
            "This is a cool book about stuff.",
            "Oh, the Places You'll Go!",
            "Dr. Seuss",
            "https://s1.nyt.com/du/books/images/9781455567539.jpg",
            "https://www.amazon.com/dp/B077BM1M6J/"
        )
    }

    @Test
    fun checkBookDetailsLoad() {
        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        mActivityTestRule.launchActivity(BookDetailActivity.createIntent(targetContext, mBook))

        onView(withId(R.id.title_textview)).check(matches(withText("Oh, the Places You'll Go!")))
        onView(withId(R.id.author_textview)).check(matches(withText("Dr. Seuss")))
        onView(withId(R.id.rank_weeks_textview)).check(matches(withText("#1 • 3 weeks on list")))
        onView(withId(R.id.description_textview)).check(matches(withText("This is a cool book about stuff.")))
        onView(withId(R.id.publisher_textview)).check(matches(withText("Publisher : Books Inc")))
        onView(withId(R.id.isbn10_textview)).check(matches(withText("ISBN-10 : 1234567897")))
        onView(withId(R.id.isbn13_textview)).check(matches(withText("ISBN-13 : 4321432138532")))

        onView(withId(R.id.amazon_button))
            .check(matches(isDisplayed()))
        onView(withId(R.id.book_cover_imageview))
            .check(matches(isDisplayed()))
    }
}
