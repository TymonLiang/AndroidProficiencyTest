package com.cl.proficiencytest;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.NoActivityResumedException;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.cl.proficiencytest.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.fail;

/**
 * Created by tymon on 28/02/2018.
 *
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecyclerViewScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(
            MainActivity.class, true, false);

    @Test
    public void viewpagerTest() throws Exception{
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition(5));
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view)).perform(ViewActions.swipeUp());

        assertPressingBackExitsApp();

    }

    private void assertPressingBackExitsApp() {
        try {
            Espresso.pressBack();
            fail("Should kill the app and throw an exception");
        } catch (NoActivityResumedException e) {
            // Test OK
        }
    }
}
