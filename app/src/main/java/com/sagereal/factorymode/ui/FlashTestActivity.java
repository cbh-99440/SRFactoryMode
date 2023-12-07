package com.sagereal.factorymode.ui;

import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sagereal.factorymode.R;

public class FlashTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash_test);
        open();
    }

    void open(){
        boolean isCameraAvailable = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
        if(isCameraAvailable){
            int surfaceId = 1;
            try{
                Camera camera = Camera.open();
                camera.setPreviewTexture(new SurfaceTexture(surfaceId));
                Camera.Parameters parameters = camera.getParameters();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                camera.startPreview();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{

        }
    }

}


