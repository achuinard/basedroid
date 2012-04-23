package com.twansoftware.basedroid.ui.examples;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockListActivity;
import com.google.inject.Inject;
import com.twansoftware.basedroid.R;
import com.twansoftware.basedroid.singleton.BasedroidHttpClient;

public class ListActivityExample extends RoboSherlockListActivity {

    @Inject
    private SharedPreferences sharedPreferences;

    @Inject
    private BasedroidHttpClient client;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_example);
        getListView().setAdapter(new ListActivityExampleListAdapter(this, android.R.layout.simple_list_item_1));
    }

    private static class ListActivityExampleListAdapter extends ArrayAdapter<String> {
        private static final String[] EXAMPLE_STRINGS = {"Android", "iPhone", "Blackberry"};

        public ListActivityExampleListAdapter(final Context context, final int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public String getItem(final int position) {
            return EXAMPLE_STRINGS[position];
        }
    }
}
