package com.twansoftware.basedroid.ui.examples;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.doomonafireball.betterpickers.hmspicker.HmsPickerBuilder;
import com.doomonafireball.betterpickers.hmspicker.HmsPickerDialogFragment;
import com.twansoftware.basedroid.R;

/**
 * Author: achuinard
 * 4/25/12
 */
public class ActionBarButtonsExample extends FragmentActivity implements HmsPickerDialogFragment.HmsPickerDialogHandler {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_bar_buttons_example);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_buttons, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_bar_buttons_save:
                new HmsPickerBuilder()
                        .setFragmentManager(getSupportFragmentManager())
                        .addHmsPickerDialogHandler(this)
                        .setStyleResId(R.style.BetterPickersDialogFragment)
                        .show();
                return true;
            case R.id.action_bar_buttons_settings:
                Toast.makeText(this, R.string.settings, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDialogHmsSet(final int reference, final int hours, final int minutes, final int seconds) {
        // TODO pull English words out of Java file
        Toast.makeText(this, String.format("%d hours, %d minutes, %d seconds", hours, minutes, seconds), Toast.LENGTH_LONG).show();
    }
}
