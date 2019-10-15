package com.cyborg.currencyconverter.activity

import androidx.annotation.UiThread
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.cyborg.currencyconverter.R
import com.cyborg.currencyconverter.presentation.common.CurrencyState
import com.cyborg.currencyconverter.presentation.home.HomeActivity
import com.cyborg.currencyconverter.presentation.home.fragment.CurrencyFragment
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsNull.notNullValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    private lateinit var homeActivity: HomeActivity

    @get:Rule
    val activityTestRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java)

    @Before
    fun setUp() {
        homeActivity = activityTestRule.activity
        Assert.assertThat(homeActivity, notNullValue())
    }

    @Test
    fun swipePage() {
        onView(withId(R.id.currencyViewPager)).check(matches(isDisplayed()))
        onView(withId(R.id.currencyViewPager)).perform(swipeLeft())
    }

    @Test
    fun checkTabLayoutDisplayed() {
        onView(withId(R.id.currencyTabLayout)).perform(click()).check(matches(isDisplayed()))
    }

    @Test
    @UiThread
    fun checkTabSwitch() {
        onView(
            allOf(
                withText("Converter"),
                isDescendantOfA(withId(R.id.currencyTabLayout))
            )
        ).perform(
            click()
        ).check(matches(isDisplayed()))

        assertThat(
            (homeActivity.mCurrencyViewPagerAdapter.getItem(0) as CurrencyFragment).mCurrencyState,
            Matchers.equalTo(CurrencyState.CURRENCY_CONVERSION)
        )
    }
}