package com.sagereal.factorymode.ui;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class
ItemTestActivity extends LauncherActivity {

    private String[] names = new String[]{"R.string.title_battery_test", "R.string.title_vibration_test", "R.string.title_mic_test", "R.string.title_headset_test", "R.string.title_lcd_test", "R.string.title_speaker_test", "R.string.title_receiver_test", "R.string.title_camera_test", "R.string.title_flash_test", "R.string.title_key_test"};
    private Class[] classes = new Class[]{BatteryTestActivity.class, VibrationTestActivity.class, MicTestActivity.class, HeadsetTestActivity.class, LcdTestActivity.class, SpeakerTestActivity.class, ReceiverTestActivity.class, CameraTestActivity.class, FlashTestActivity.class, KeyTestActivity.class};

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);
    }

    @Override
    protected Intent intentForPosition(int position) {
        return new Intent(ItemTestActivity.this, classes[position]);
    }

    
}
