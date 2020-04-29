package com.example.drawgame.activity;

import android.util.Log;
import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class DrawActivityTest {
    private static String TAG = "DrawActivityTest";
    private DrawActivity activity;

    @Rule
    public ActivityTestRule<DrawActivity> mActivityRule = new ActivityTestRule<>(DrawActivity.class);

    @Before
    public void init() {
        activity = mActivityRule.getActivity();
    }

    @Test
    public void testActivityNotNull() {
        Log.d(TAG, "testing activity not null...");
        assertNotNull(activity);
    }

    @Test
    public void testControlsVisible() {
        Log.d(TAG, "testing ui controls visible...");
        for (View v : activity.getMenuOptions()) {
            assertEquals(View.VISIBLE, v.getVisibility());
        }

        for (View b : activity.getBrushes()) {
            assertEquals(View.VISIBLE, b.getVisibility());
        }
    }

}
