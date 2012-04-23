package com.twansoftware.basedroid.ui.examples;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.google.inject.Inject;
import com.twansoftware.basedroid.R;
import com.twansoftware.basedroid.activity.RoboSherlockScopedListActivity;
import com.twansoftware.basedroid.singleton.BasedroidHttpClient;

public class ListActivityExample extends RoboSherlockScopedListActivity implements AdapterView.OnItemClickListener {

    @Inject
    private SharedPreferences sharedPreferences;

    @Inject
    private BasedroidHttpClient client;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_example);
        getSupportActionBar().setTitle("List Activity");
        getListView().setAdapter(new ListActivityExampleListAdapter(this, android.R.layout.simple_list_item_1));
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, final long id) {
        // This models an HTTP request along with a ProgressDialog.  Note that it is created outside of the thread
        // for convenience but must be used inside runOnUiThread
        final ProgressDialog show = ProgressDialog.show(this, "Dialog...", "Exampling...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                show.dismiss();
            }
        }).start();
    }

    private static class ListActivityExampleListAdapter extends ArrayAdapter<String> {
        private static final String[] EXAMPLE_STRINGS = {"Android", "iPhone", "Blackberry"};

        public ListActivityExampleListAdapter(final Context context, final int textViewResourceId) {
            super(context, textViewResourceId, EXAMPLE_STRINGS);
        }

        @Override
        public String getItem(final int position) {
            return EXAMPLE_STRINGS[position];
        }
    }
}
