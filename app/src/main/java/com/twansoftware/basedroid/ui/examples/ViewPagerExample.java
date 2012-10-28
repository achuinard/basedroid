package com.twansoftware.basedroid.ui.examples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.twansoftware.basedroid.R;
import com.viewpagerindicator.TitlePageIndicator;
import roboguice.inject.InjectView;

public class ViewPagerExample extends RoboSherlockFragmentActivity {
    @InjectView(R.id.view_pager_example_pager)
    private ViewPager viewPager;

    @InjectView(R.id.view_pager_example_tpi)
    private TitlePageIndicator titlePageIndicator;

    private static final String[] COUNTRIES = {"United States", "Mexico", "Canada"};
    private static final String[] STATES = {"Illinois", "Kentucky", "Maine", "Florida"};


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_example);

        setupViewPager();
    }

    private void setupViewPager() {
        viewPager.setAdapter(new SimpleAdapter(getSupportFragmentManager()));
        titlePageIndicator.setViewPager(viewPager);
    }

    private static class SimpleAdapter extends FragmentPagerAdapter {
       public SimpleAdapter(final FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return COUNTRIES.length;
        }

        @Override
        public Fragment getItem(int position) {
            return ArrayListFragment.newInstance(position);
        }

        @Override
        public CharSequence getPageTitle(final int position) {
            return COUNTRIES[position];
        }
    }

    public static class ArrayListFragment extends Fragment {
        private int position;

        public static ArrayListFragment newInstance(int position) {
            final ArrayListFragment newFragment = new ArrayListFragment();

            final Bundle args = new Bundle();
            args.putInt("position", position);
            newFragment.setArguments(args);

            return newFragment;
        }

        public ArrayListFragment() {

        }

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            position = (getArguments() != null ? getArguments().getInt("position") : 0);
        }

        @Override
        public View onCreateView(final LayoutInflater inflater,
                                 final ViewGroup container,
                                 final Bundle savedInstanceState) {
            final ListView listView = new ListView(getActivity());
            listView.setAdapter(new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, STATES));
            return listView;
        }

        @Override
        public void onSaveInstanceState(final Bundle outState) {
            outState.putInt("position", position);
        }
    }
}
