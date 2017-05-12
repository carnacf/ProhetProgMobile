package com.example.nairolf.projetprogmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

public class ThreadSensor implements Runnable{

    private SensorManager manager = null;
    private Sensor sensor;
    private Context context;
    private SensorEventListener mListener;
    private HandlerThread mHandlerThread;

    public ThreadSensor(Context _context){
        context = _context;
    }


    @Override
    public void run() {
        manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mHandlerThread = new HandlerThread("AccelerometerLogListener");
        mHandlerThread.start();
        Handler handler = new Handler(mHandlerThread.getLooper());


        mListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
                    SharedPreferences sp = context.getSharedPreferences("mode",Context.MODE_PRIVATE);
                    if(sensorEvent.values[0] < 15){
                        sp.edit().putString("mode","dark").apply();
                    }else{
                        sp.edit().putString("mode","light").apply();
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        manager.registerListener(mListener,sensor,SensorManager.SENSOR_DELAY_FASTEST,handler);
    }
}
