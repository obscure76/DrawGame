package com.example.drawgame.activity;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.drawgame.R;
import com.example.drawgame.views.DrawView;

import java.util.ArrayList;
import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;

public class DrawActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "DrawActivity";
    private static final int PREFERENCE_DIALOG_ID = 4;
    private DrawView drawView;
    private View selectedBrush;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Starting draw activity...");
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.activity_main);
        initViews();
        setBrushColor(drawView.getPaintColor());
    }

    private void initViews() {
        drawView = findViewById(R.id.drawing);
        drawView.setBrushSize(getResources().getInteger(R.integer.medium_size));
        setSelectedBrush(R.id.medium_brush);
        setEraseSelected(false);

        for (View v : getMenuOptions()) {
            v.setOnClickListener(this);
        }
    }

    private Iterable<? extends View> getMenuOptions() {
        return getLayoutChildren(R.id.top_options);
    }

    private void setEraseSelected(boolean selected) {
        if(selected){
            findViewById(R.id.erase_btn).setBackgroundColor(getColor(android.R.color.darker_gray));
            findViewById(R.id.color_pick).setBackgroundColor(getColor(android.R.color.transparent));
        } else {
            findViewById(R.id.erase_btn).setBackgroundColor(getColor(android.R.color.transparent));
            findViewById(R.id.color_pick).setBackgroundColor(getColor(android.R.color.darker_gray));
        }
    }

    private void setSelectedBrush(int medium_brush) {
        if (selectedBrush != null) {
            selectedBrush.setBackground(getDrawable(R.drawable.btn_default_normal_holo_dark));
        }
        selectedBrush = findViewById(medium_brush);
        selectedBrush.setBackground(getDrawable(R.drawable.btn_default_normal_holo_dark));
    }

    private void setBrushColor(int color) {
        for (View v : getBrushes()) {
            ImageButton im = (ImageButton) v;
            GradientDrawable d = (GradientDrawable) im.getDrawable();
            d.setColor(color);
        }
    }

    private Iterable<? extends View> getBrushes() {
        return getLayoutChildren(R.id.all_brushes);
    }

    private Iterable<? extends View> getLayoutChildren(int all_brushes) {
        List<View> views = new ArrayList<>();
        LinearLayout layout = findViewById(all_brushes);
        int count = layout.getChildCount();
        for (int i = 0; i < count; i++) {
            views.add(layout.getChildAt(i));
        }

        return views;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "view id clicked: " + v.getId());
        switch(v.getId()) {
            case R.id.color_pick:
                handleColorPick();
                break;
            case R.id.erase_btn:
                handleEraseButton();
                break;
            case R.id.new_btn:
                openNewDrawingDialog();
                break;
            default:
                Log.d(TAG, "default selected");
        }
    }

    private MaterialDialog.SingleButtonCallback newDialogClick() {
        return (dialog, which) -> {
            if (DialogAction.POSITIVE.equals(which)) {
                drawView.startNew();
            }
            dialog.dismiss();
        };
    }

    private void openNewDrawingDialog() {
        new MaterialDialog.Builder(this)
                .onPositive(newDialogClick())
                .onNegative(newDialogClick())
                .title(R.string.action_new_drawing)
                .positiveText(R.string.action_ok)
                .negativeText(R.string.action_cancel)
                .content(R.string.action_new_q)
                .show();
    }

    private AmbilWarnaDialog.OnAmbilWarnaListener getColorPickerCallback() {
        return new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                Log.d(TAG, "cancel clicked...");
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                Log.d(TAG, "selected color: " + color);
                drawView.setColor(color);
                setBrushColor(color);
            }
        };
    }

    private void handleEraseButton() {
        drawView.setErase(true);
        setEraseSelected(true);
    }

    private void handleColorPick() {
        drawView.setErase(false);
        setEraseSelected(false);
        openColorPickerDialog();
    }

    private void openColorPickerDialog() {
        Log.d(TAG, "show color picker dialog...");
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, drawView.getPaintColor(), false, getColorPickerCallback());
        dialog.show();
    }

    public void handleBrushSize(View view) {
        drawView.setErase(false);
        setEraseSelected(false);
        setSelectedBrush(view.getId());

        switch (view.getId()) {
            case R.id.small_brush:
                drawView.setBrushSize(getResources().getInteger(R.integer.small_size));
                break;
            case R.id.large_brush:
                drawView.setBrushSize(getResources().getInteger(R.integer.large_size));
                break;
            default:
                drawView.setBrushSize(getResources().getInteger(R.integer.medium_size));
        }
    }
}
