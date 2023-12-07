package com.sagereal.factorymode.ui;

import android.os.Bundle;
import android.os.Vibrator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sagereal.factorymode.R;

public class VibrationTestActivity extends AppCompatActivity {

    private Vibrator mVibrator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vibration_test);

        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        mVibrator.vibrate(new long[]{1000, 1000}, 0);

    }
}
