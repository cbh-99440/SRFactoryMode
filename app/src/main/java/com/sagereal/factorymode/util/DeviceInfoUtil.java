package com.sagereal.factorymode.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;

public class DeviceInfoUtil {

    public static String getDeviceName(){
        return Build.DEVICE;
    }

    public static String getDeviceModel(){
        return Build.MODEL;
    }

    public static String getVersion(){
        return Build.DISPLAY;
    }

    public static String getAndroidVersion(){
        return Build.VERSION.RELEASE;
    }

    //RAM内存大小, 返回1GB/2GB/3GB/4GB/8G/16G
    public static String getRam(){
        String path = "/proc/meminfo";
        String ramMemorySize = null;
        int totalRam = 0 ;
        try{
            FileReader fileReader = new FileReader(path);
            BufferedReader br = new BufferedReader(fileReader, 4096);
            ramMemorySize = br.readLine().split("\\s+")[1];
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(ramMemorySize != null){
            totalRam = (int)Math.ceil((new Float(Float.valueOf(ramMemorySize) / (1024 * 1024)).doubleValue()));
        }

        return totalRam + "GB";
    }

    //ROM内存大小，返回 64G/128G/256G/512G
    public static String getRom() {
        File dataDir = Environment.getDataDirectory();
        StatFs stat = new StatFs(dataDir.getPath());
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        long size = totalBlocks * blockSize;
        long GB = 1024 * 1024 * 1024;
        final long[] deviceRomMemoryMap = {2*GB, 4*GB, 8*GB, 16*GB, 32*GB, 64*GB, 128*GB, 256*GB, 512*GB, 1024*GB, 2048*GB};
        String[] displayRomSize = {"2GB","4GB","8GB","16GB","32GB","64GB","128GB","256GB","512GB","1024GB","2048GB"};
        int i;
        for(i = 0 ; i < deviceRomMemoryMap.length; i++) {
            if(size <= deviceRomMemoryMap[i]) {
                break;
            }
            if(i == deviceRomMemoryMap.length) {
                i--;
            }
        }
        return displayRomSize[i];
    }

    public static double getBatterySize(Context context){
        Object mPowerProfile;
        double batteryCapacity = 0;
        final String POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile";
        try{
            mPowerProfile = Class.forName(POWER_PROFILE_CLASS).getConstructor(Context.class).newInstance(context);
            batteryCapacity = (double) Class.forName(POWER_PROFILE_CLASS).getMethod("getBatteryCapacity").invoke(mPowerProfile);
        }catch (Exception e){
            e.printStackTrace();
        }
        return batteryCapacity;
    }

    public static String getScreenSize(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        double width = displayMetrics.widthPixels/displayMetrics.xdpi;
        double height = displayMetrics.heightPixels/displayMetrics.ydpi;
        double screenSize = Math.sqrt(width * width + height * height);
        DecimalFormat decimalFormat = new DecimalFormat("#");
        return decimalFormat.format(screenSize);
    }

    public static String getScreenResolution(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels + "X" + displayMetrics.heightPixels;
    }

}
