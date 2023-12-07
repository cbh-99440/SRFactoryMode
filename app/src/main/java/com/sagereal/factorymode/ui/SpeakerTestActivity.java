package com.sagereal.factorymode.ui;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sagereal.factorymode.R;

public class SpeakerTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speaker_test);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.start();
    }
}
