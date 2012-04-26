package com.twansoftware.basedroid.ui.examples;

import android.os.Bundle;
import android.widget.Toast;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.twansoftware.basedroid.R;

/**
 * Author: achuinard
 * 4/25/12
 */
public class ActionBarButtonsExample extends RoboSherlockActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This has to be called before setContentView and you must use the
        //class in com.actionbarsherlock.view and NOT android.view
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.action_bar_buttons_example);
        setSupportProgressBarIndeterminateVisibility(false);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        menu.add("Refresh")
                .setIcon(R.drawable.ic_refresh)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        menu.add("Compose")
                .setIcon(R.drawable.ic_compose)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        Toast.makeText(this, "Got click: " + item.toString(), Toast.LENGTH_SHORT).show();
        if ("Refresh".equals(item.toString())) {
            setSupportProgressBarIndeterminateVisibility(true);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setSupportProgressBarIndeterminateVisibility(false);
                        }
                    });
                }
            }).start();
        }
        return true;
    }
}
