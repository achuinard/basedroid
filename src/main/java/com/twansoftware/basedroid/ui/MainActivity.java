package com.twansoftware.basedroid.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.InjectView;
import butterknife.Views;
import com.twansoftware.basedroid.R;
import com.twansoftware.basedroid.ui.examples.ActionBarButtonsExample;
import com.twansoftware.basedroid.ui.examples.ListActivityExample;

public class MainActivity extends Activity implements View.OnClickListener {
    @InjectView(R.id.main_list_view_example_button)
    Button listViewExampleButton;
    
    @InjectView(R.id.main_action_bar_buttons_example_button)
    Button actionBarButtonsExampleButton;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Views.inject(this);

        bindButtons();
    }

    private void bindButtons() {
        listViewExampleButton.setOnClickListener(this);
        actionBarButtonsExampleButton.setOnClickListener(this);
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

            default:
                break;
        }
    }
}
