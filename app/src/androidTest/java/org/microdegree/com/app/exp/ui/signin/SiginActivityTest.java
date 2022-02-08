package org.microdegree.com.app.exp.ui.signin;




import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.microdegree.com.app.exp.R;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SiginActivityTest {

    @Rule
    public ActivityTestRule<SignInActivity> mActivityTestRule = new ActivityTestRule<>(SignInActivity.class);

    @Test
    public void sigInActivityTest() {
        // Click is needed since we perform validation on lose focus
        onView(withId(R.id.txtName)).perform(click(), replaceText("Vikky"));
        onView(withId(R.id.txtMobile)).perform(click(), replaceText("7019490980"));
        onView(withId(R.id.txtEmail)).perform(click(), replaceText("asd@asd.com"));
        onView(withId(R.id.txtRefer)).perform(click(), replaceText("Referral"),
                closeSoftKeyboard());
        onView(withId(R.id.button)).check(matches(isEnabled()));
        onView(withId(R.id.button)).perform(click());
    }
}
