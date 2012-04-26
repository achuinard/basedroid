package com.twansoftware.basedroid.ui.examples;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockListActivity;
import com.google.inject.Inject;
import com.twansoftware.basedroid.R;
import com.twansoftware.basedroid.singleton.BasedroidHttpClient;

public class ListActivityExample extends RoboSherlockListActivity implements AdapterView.OnItemClickListener {
    private static final String[] EXAMPLE_STRINGS = {"Android", "iPhone", "Blackberry"};

    @Inject
    private SharedPreferences sharedPreferences;

    @Inject
    private BasedroidHttpClient client;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_example);
        getSupportActionBar().setTitle("List Activity");
        getListView().setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, EXAMPLE_STRINGS));
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int position, final long id) {
        // This models an HTTP request along with a ProgressDialog.
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
}
