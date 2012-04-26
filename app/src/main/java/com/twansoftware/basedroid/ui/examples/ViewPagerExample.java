package com.twansoftware.basedroid.ui.examples;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.twansoftware.basedroid.R;
import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitleProvider;
import roboguice.inject.InjectView;

/**
 * Author: achuinard
 * 4/25/12
 */
public class ViewPagerExample extends RoboSherlockActivity {
    @InjectView(R.id.view_pager_example_pager)
    private ViewPager viewPager;
    
    @InjectView(R.id.view_pager_example_tpi)
    private TitlePageIndicator titlePageIndicator;

    private ArrayAdapter<String> countriesAdapter;
    private ArrayAdapter<String> statesAdapter;
    private static final String[] COUNTRIES = {"United States", "Mexico", "Canada"};
    private static final String[] STATES = {"Illinois", "Kentucky", "Maine", "Florida"};


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_example);

        setupViewPager();
    }

    private void setupViewPager() {
        countriesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, COUNTRIES);
        statesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, STATES);

        viewPager.setAdapter(new CustomPagerAdapter(this));
        titlePageIndicator.setViewPager(viewPager);
    }

    private class CustomPagerAdapter extends PagerAdapter implements TitleProvider {
        private final Context context;

        private CustomPagerAdapter(final Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(final View view, final Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(final ViewGroup container, final int position) {
            final ListView listView = new ListView(context);
            switch (position) {
                case 0:
                    listView.setAdapter(countriesAdapter);
                    break;
                case 1:
                    listView.setAdapter(statesAdapter);
                    break;
            }
            container.addView(listView, position);
            return listView;
        }

        @Override
        public void destroyItem(final ViewGroup container, final int position, final Object object) {
            container.removeView((ListView) object);
        }

        public String getTitle(final int position) {
            if (position == 0) {
                return "Countries";
            } else if (position == 1) {
                return "States";
            } else {
                return "Unknown";
            }
        }
    }
}
