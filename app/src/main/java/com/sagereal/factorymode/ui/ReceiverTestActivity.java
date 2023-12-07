package com.sagereal.factorymode.ui;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sagereal.factorymode.R;

public class ReceiverTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiver_test);

        AudioManager audioManager = (AudioManager) this.getSystemService(AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.start();
    }
}
