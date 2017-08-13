package com.zino.mobilization.weatheryamblz;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.zino.mobilization.weatheryamblz.presentation.main.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Denis Buzmakov on 13.08.17.
 * <buzmakov.da@gmail.com>
 */

@RunWith(AndroidJUnit4.class)
public class AddCityFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init() {
        onView(withId(R.id.btn_add)).perform(click());
    }

    @Test
    public void shouldShowLoading() {
        onView(withId(R.id.txt_input)).perform(typeText("k"));
        onView(withId(R.id.progress)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldShowClearButton() {
        onView(withId(R.id.btn_clear)).check(matches(not(isDisplayed())));
        onView(withId(R.id.txt_input)).perform(typeText("k"));
        onView(withId(R.id.btn_clear)).check(matches(isDisplayed()));
        onView(withId(R.id.txt_input)).perform(clearText());
        onView(withId(R.id.btn_clear)).check(matches(not(isDisplayed())));
        onView(withId(R.id.txt_input)).perform(typeText("kk"));
        onView(withId(R.id.btn_clear)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldHideLoadingAndShowList() {
        onView(withId(R.id.txt_input)).perform(typeText("k"));
        onView(withId(R.id.progress)).check(matches(isDisplayed()));
        SystemClock.sleep(1000);
        onView(withId(R.id.progress)).check(matches(not(isDisplayed())));
        onView(withId(R.id.list_suggestions)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldClearInput() {
        onView(withId(R.id.txt_input)).perform(typeText("k"));
        onView(withId(R.id.btn_clear)).perform(click());
        onView(withId(R.id.txt_input)).check(matches(withText("")));
    }
}
