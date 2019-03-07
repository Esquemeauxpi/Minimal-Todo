package com.example.avjindersinghsekhon.minimaltodo.Main;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.avjindersinghsekhon.minimaltodo.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class settingsTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void settingsTest() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       onView(
                allOf(withContentDescription("More options"),
                        isDisplayed())).check(matches(isDisplayed())).perform(click());

        onView(
                allOf(withId(R.id.title), withText("Settings"),
                        isDisplayed())).check(matches(withText("Settings")));

        onView(
                allOf(withId(R.id.title), withText("About"),
                        isDisplayed())).check(matches(withText("About")));

        onView(
                allOf(withId(R.id.title), withText("Settings"),
                        isDisplayed())).perform(click());


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
////////////////////////////////////////////////////////////////////////////////////////////
/// Also, I really wanted to put in a try/catch here to check if Night mode was on,then  ///
///   assert that it was on, and if off assert that it was off, but I couldn't quite     ///
/// figure it out. I know Espresso isn't "built" for that anyway, but would've been nice //
///////////////////////////////////////////////////////////////////////////////////////////
        onView(
                allOf(withText("Minimal"),
                        isDisplayed())).check(matches(withText("Minimal")));

        onView(
                allOf(withId(android.R.id.summary), withText("Night mode is off"),
                        isDisplayed())).check(matches(withText("Night mode is off")));


///////////////////////////
/// Turn night mode on////
//////////////////////////

        onData(anything())
                .inAdapterView(allOf(withId(android.R.id.list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0)))
                .atPosition(0).perform(click());


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

///////////////////////////////////
///Assert that night mode is on////
///////////////////////////////////

        onView(
                allOf(withId(android.R.id.summary), withText("Night mode is on"),
                        isDisplayed())).check(matches(withText("Night mode is on")));

//////////////////////////////////////////
/// Turn night mode off to reset test ////
/////////////////////////////////////////

        onData(anything())
                .inAdapterView(allOf(withId(android.R.id.list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                0)))
                .atPosition(0).perform(click());


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

///////////////////////////////////
///Assert that night mode is off///
///////////////////////////////////

       onView(
                allOf(withId(android.R.id.summary), withText("Night mode is off"),
                        isDisplayed())).check(matches(withText("Night mode is off")));

///////////////////////////////////
///Navigate up to reset the test///
///////////////////////////////////

        onView(
                allOf(withContentDescription("Navigate up"),
                        isDisplayed())).perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
