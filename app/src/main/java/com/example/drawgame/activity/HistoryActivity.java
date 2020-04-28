package com.example.drawgame.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.drawgame.R;

public class HistoryActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Starting jigsaw history activity...");
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.activity_history);
        enableMenuBarUpButton();
        initViews();
    }

    private void initViews() {

    }
}
