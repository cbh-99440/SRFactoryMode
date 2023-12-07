package com.sagereal.factorymode.ui;

import android.content.Context;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sagereal.factorymode.R;

public class BatteryTestActivity extends AppCompatActivity {

    private TextView tv_battery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battery_test);
        tv_battery = findViewById(R.id.tv_battery_info);
        tv_battery.setText("当前电量：" + getbattery());
    }

    int getbattery()
    {
        BatteryManager batteryManager = (BatteryManager) getSystemService(Context.BATTERY_SERVICE);
        int capacity = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        return capacity;
    }
}
