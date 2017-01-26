package com.zaraos.colorpop.presenter.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zaraos.colorpop.R;

/**
 * Created by Alex on 26/01/17.
 */

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT >= 19)
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (android.os.Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        initFragment();
    }

    public void initFragment() {
        addFragment(null);
    }

    public void addFragment(Fragment fragment) {
        if (fragment == null)
            return;

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .commit();
    }
}
