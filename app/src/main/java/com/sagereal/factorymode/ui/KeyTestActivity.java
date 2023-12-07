package com.sagereal.factorymode.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sagereal.factorymode.R;

public class KeyTestActivity extends AppCompatActivity {

    private Button btn_volume_up;
    private Button btn_volume_down;
    private Button btn_power_key;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.key_test);

        btn_volume_up = findViewById(R.id.btn_volume_up);
        btn_volume_down = findViewById(R.id.btn_volume_down);
        btn_power_key = findViewById(R.id.btn_power_key);

    }

    class KeyChangeListener implements View.OnKeyListener{
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP){
                btn_volume_up.setVisibility(View.INVISIBLE);
            }else if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN){
                btn_volume_down.setVisibility(View.INVISIBLE);
            }else if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_POWER){
                btn_power_key.setVisibility(View.INVISIBLE);
            }
            return false;
        }
    }
}
