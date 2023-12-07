package com.sagereal.factorymode.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sagereal.factorymode.R;

public class LcdTestActivity extends AppCompatActivity {

    RelativeLayout mRelativeLayout;

    private final LcdClickListener lcl = new LcdClickListener();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lcd_test);
        mRelativeLayout = findViewById(R.id.lcd);
        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.red));
        mRelativeLayout.setOnClickListener(lcl);
    }
    class LcdClickListener implements View.OnClickListener{
        int flag = 1;
        @Override
        public void onClick(View view) {
            if(flag % 3 == 0){
                mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.red));
            }
            if(flag % 3 == 1){
                mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.green));
            }
            if(flag % 3 == 2){
                mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.grey));
            }
            flag ++;
        }
    }
}
