package com.twansoftware.basedroid.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.google.inject.Inject;
import com.twansoftware.basedroid.R;
import com.twansoftware.basedroid.ui.examples.ActionBarButtonsExample;
import com.twansoftware.basedroid.ui.examples.ListActivityExample;
import com.twansoftware.basedroid.ui.examples.ViewPagerExample;
import roboguice.inject.InjectView;

public class MainActivity extends RoboSherlockActivity implements View.OnClickListener {
    @Inject
    private SharedPreferences sharedPreferences;

    @InjectView(R.id.main_list_view_example_button)
    private Button listViewExampleButton;
    
    @InjectView(R.id.main_action_bar_buttons_example_button)
    private Button actionBarButtonsExampleButton;
    
    @InjectView(R.id.main_view_pager_example_button)
    private Button viewPagerExampleButton;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bindButtons();
    }

    private void bindButtons() {
        listViewExampleButton.setOnClickListener(this);
        actionBarButtonsExampleButton.setOnClickListener(this);
        viewPagerExampleButton.setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.main_list_view_example_button:
                startActivity(new Intent(this, ListActivityExample.class));
                break;
            case R.id.main_action_bar_buttons_example_button:
                startActivity(new Intent(this, ActionBarButtonsExample.class));
                break;
            case R.id.main_view_pager_example_button:
                startActivity(new Intent(this, ViewPagerExample.class));
                break;
            default:
                break;
        }
    }
}
