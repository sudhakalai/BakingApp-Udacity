package com.example.android.bakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Sudha on 17-May-18.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    public static final String RECIPE_NAME = "Nutella Pie";

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);



    @Test
    public void checkRecipeNameOnFirstRecyclerViewItem() {

        onView(withRecyclerView(R.id.rv_main)
                .atPositionOnView(0, R.id.iv_recipe_name))
                .check(matches(withText(RECIPE_NAME)));

    }

}
