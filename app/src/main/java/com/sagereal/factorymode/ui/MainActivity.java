package com.sagereal.factorymode.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sagereal.factorymode.R;
import com.sagereal.factorymode.util.DeviceInfoUtil;

public class MainActivity extends AppCompatActivity {

    private TextView tv_info;
    private Button btn_photo;
    private Button btn_call;
    private Button btn_test;
    private Button btn_report;

    private final MyClickListener mcl = new MyClickListener();

    int REQUEST_CODE = 0;

    int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_info = findViewById(R.id.tv_info);

        btn_photo = findViewById(R.id.btn_photo);
        btn_call = findViewById(R.id.btn_call);
        btn_test = findViewById(R.id.btn_test);
        btn_report = findViewById(R.id.btn_report);

        tv_info.setText(getString(R.string.device_name) + DeviceInfoUtil.getDeviceName() + "\n"
                + getString(R.string.device_model) + DeviceInfoUtil.getDeviceModel() + "\n"
                + getString(R.string.version) + DeviceInfoUtil.getVersion() + "\n"
                + getString(R.string.android_version) + DeviceInfoUtil.getAndroidVersion() + "\n"
                + getString(R.string.ram) + DeviceInfoUtil.getRam() + "\n"
                + getString(R.string.rom) + DeviceInfoUtil.getRom() + "\n"
                + getString(R.string.battery_size) + DeviceInfoUtil.getBatterySize(this) + "\n"
                + getString(R.string.screen_size) + DeviceInfoUtil.getScreenSize(this) + "\n"
                + getString(R.string.screen_resolution) + DeviceInfoUtil.getScreenResolution(this));

        btn_photo.setOnClickListener(mcl);
        btn_photo.setTag(1);
        btn_call.setOnClickListener(mcl);
        btn_call.setTag(2);
        btn_test.setOnClickListener(mcl);
        btn_test.setTag(3);
        btn_report.setOnClickListener(mcl);
        btn_report.setTag(4);

        getPermission();


    }
    class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int tag = (Integer) view.getTag();
            switch(tag){
                case 1:
                    dispatchTakePictureIntent();
                    break;
                case 2:
                    dialIntent();
                    break;
                case 3:
                    Intent tintent = new Intent(MainActivity.this, ItemTestActivity.class);
                    startActivity(tintent);
                    break;
                case 4:
                    Intent rintent = new Intent();
                    startActivity(rintent);
                    break;
            }
        }
    }

    void getPermission(){
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

        }else{
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO, Manifest.permission.CALL_PHONE},REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED){

            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("权限申请")
                        .setMessage("请设置权限后使用");
                builder.setPositiveButton("positive", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent request_intent = new Intent();
                        ComponentName comp = new ComponentName("com.android.settings","com.android.settings.applications.InstalledAppDetailsTop");
                        request_intent.setData(Uri.parse("package:"+"com.sagereal.factorymode"));
                        request_intent.setComponent(comp);
                        startActivity(request_intent);
                    }
                });
                builder.setNegativeButton("negative", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).create().show();

            }
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void dialIntent(){
        Intent dialIntent = new Intent();
        dialIntent.setAction(dialIntent.ACTION_DIAL);
        Uri data = Uri.parse("tel:112");
        dialIntent.setData(data);
        startActivity(dialIntent);
    }
}