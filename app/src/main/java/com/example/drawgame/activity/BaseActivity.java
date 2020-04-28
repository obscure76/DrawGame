package com.example.drawgame.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.example.drawgame.R;

public class BaseActivity extends AppCompatActivity {
    public static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "menu item selected: " + item.getItemId());
        switch (item.getItemId()) {
            case R.id.menu_history:
                startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                return true;
            default:
                return false;
        }
    }

    /**
     * Enable the go back to parent activity in menu bar
     */
    public void enableMenuBarUpButton() {
        ActionBar bar = getActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
