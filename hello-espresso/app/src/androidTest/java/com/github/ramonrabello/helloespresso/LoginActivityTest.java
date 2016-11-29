package com.github.ramonrabello.helloespresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * UI Test for {@link LoginActivity} class.
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    /**
     * Rule needed to instrument the code. Basically, it is used to launch the Activity automatically.
     * But if has constructor version to start the activity manually with customized
     * Intents. Please se {@link ActivityTestRule} class for more details.
     */
    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void clickLoginButton_whenValidCredentials_shouldShowAuthenticationStatus() {

        // type "espresso" in the username field
        onView(withId(R.id.username_field)).perform(typeText("espresso"), closeSoftKeyboard());

        // type "35pr3550" in the password field
        onView(withId(R.id.password_field)).perform(typeText("35pr3550"), closeSoftKeyboard());

        // click on the login button
        onView(withId(R.id.login_button)).perform(click());

        // checks if the username field are filled with the word "espresso"
        onView(withId(R.id.username_field)).check(matches(withText("espresso")));

        // checks if the username field are filled with the word "35pr3550"
        onView(withId(R.id.password_field)).check(matches(withText("35pr3550")));

        // checks if the authentication status has text "User signed in!"
        onView(withId(R.id.authentication_status)).check(matches(withText("User signed in!")));

    }

    @Test
    public void clickLoginButton_whenWrongCredentials_shouldShowAuthenticationStatus() {

        // type "espresso" in the username field
        onView(withId(R.id.username_field)).perform(typeText("espressu"), closeSoftKeyboard());

        // type "35pr3550" in the password field
        onView(withId(R.id.password_field)).perform(typeText("35pr3550"), closeSoftKeyboard());

        // click on the login button
        onView(withId(R.id.login_button)).perform(click());

        // checks if the username field are filled with the word "espresso"
        onView(withId(R.id.username_field)).check(matches(withText("espressu")));

        // checks if the username field are filled with the word "35pr3550"
        onView(withId(R.id.password_field)).check(matches(withText("35pr3550")));

        // checks if the authentication status has text "Sorry! Invalid account."
        onView(withId(R.id.authentication_status)).check(matches(withText("Sorry! Invalid account.")));

    }

    @Test
    public void clickLoginButton_whenCredentialsEmpty_shouldshowErrorDialog() {

        // click the login button. It will show a error dialog
        onView(withId(R.id.login_button)).perform(click());

        // checks if the error dialog title is displayed
        onView(withText(R.string.error_dialog_title)).check(matches(isDisplayed()));

        // checks if the error dialog message is displayed
        onView(withText(R.string.error_dialog_message)).check(matches(isDisplayed()));

        // checks if the error dialog message is displayed
        onView(withText(R.string.error_dialog_close_button)).check(matches(isDisplayed()));
    }
}
