package com.zino.mobilization.weatheryamblz;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.zino.mobilization.weatheryamblz.presentation.main.MainActivity;
import com.zino.mobilization.weatheryamblz.presentation.settings.SettingsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Denis Buzmakov on 13.08.17.
 * <buzmakov.da@gmail.com>
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public IntentsTestRule<MainActivity> mainActivity = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void shouldOpenSettings() {
        onView(withId(R.id.action_settings)).perform(click());

        intended(hasComponent(SettingsActivity.class.getName()));
    }
}
