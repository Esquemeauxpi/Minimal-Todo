package com.example.avjindersinghsekhon.minimaltodo.Main;


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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class toDoCreation {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void toDoCreation() {

 ///Test to actually make a 'To Do'

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.addToDoItemFAB)
                        ).perform(click());

       ///Adding sleep statement to allow content to load on next screen
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        onView((withId(R.id.userToDoEditText)))
                .perform(replaceText("Hi. This is a reminder."), closeSoftKeyboard());


        onView((withId(R.id.userToDoDescription)))
                .perform(replaceText("Don't forget me"), closeSoftKeyboard());


        onView(withId(R.id.toDoHasDateSwitchCompat))
                .perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

// Asserting everything if filled in that I want filled in
        onView((withId(R.id.newToDoDateTimeReminderTextView)
            )).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.userToDoDescription), withText("Don't forget me")
                       )).check(matches(isDisplayed()));

        onView((withId(R.id.toDoHasDateSwitchCompat)
                        )).check(matches(isDisplayed()));

        onView((withId(R.id.copyclipboard)
                        )).check(matches(isDisplayed()));

        onView((withId(R.id.makeToDoFloatingActionButton)
                        )).check(matches(isDisplayed()));

        onView((withId(R.id.makeToDoFloatingActionButton)
                        )).perform(click());

// Adding sleep statement before moving back to previous screen

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

// Asserting that 'To Do' was created

        onView(allOf(withId(R.id.toDoListItemTextview), withText("Hi. This is a reminder.")
                )).check(matches(withText("Hi. This is a reminder.")));


        onView((withId(R.id.todoListItemTimeTextView)
                        )).check(matches(isDisplayed()));

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
