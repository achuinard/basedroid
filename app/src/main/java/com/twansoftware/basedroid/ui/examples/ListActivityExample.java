package com.twansoftware.basedroid.ui.examples;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockListActivity;
import com.google.inject.Inject;
import com.twansoftware.basedroid.R;
import com.twansoftware.basedroid.singleton.BasedroidHttpClient;

import java.util.concurrent.TimeUnit;

public class ListActivityExample extends RoboSherlockListActivity implements AdapterView.OnItemClickListener {
    private static final String[] EXAMPLE_STRINGS = {"Android", "iPhone", "Blackberry"};
    private static final int DIALOG_SHOW_SECONDS = 3;
    private ProgressDialog progressDialog;

    @Inject
    private SharedPreferences sharedPreferences;

    @Inject
    private BasedroidHttpClient client;

    private final Handler dialogHandler = new Handler() {
        @Override
        public void handleMessage(final Message msg) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    };

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
        final String stringSelected = (String) adapterView.getItemAtPosition(position);
        progressDialog = ProgressDialog.show(this, stringSelected, stringSelected);
        dialogHandler.sendEmptyMessageDelayed(0, TimeUnit.SECONDS.toMillis(DIALOG_SHOW_SECONDS));
    }
}
