package com.apisense.bee.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.apisense.bee.R;
import com.apisense.bee.ui.fragment.*;

public class SlideshowActivity extends FragmentActivity {

    /**
     * The number of pages (wizard steps) to show
     * Be careful if you are adding some slides, button listeners may not match
     */
    private static final int NUM_PAGES = 5;

    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        // Add onClick listeners
        Button signInBtn = (Button) findViewById(R.id.signIn);
        Button registerBtn = (Button) findViewById(R.id.register);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPager.setCurrentItem(3);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPager.setCurrentItem(4);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.slideshow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return new WhatFragment();
                case 1: return new HowFragment();
                case 2: return new RewardFragment();
                case 3: return new SignInFragment();
                case 4: return new RegisterFragment();
                default: return new NotFoundFragment();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
