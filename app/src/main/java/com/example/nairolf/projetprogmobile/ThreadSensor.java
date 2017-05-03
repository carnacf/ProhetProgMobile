package com.example.nairolf.projetprogmobile;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ThreadSensor extends Thread implements SensorEventListener{

    private float val;
    private float max;
    private SensorManager manager;
    private Sensor sensor;

    public ThreadSensor(Context context){
        manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        max = sensor.getMaximumRange();
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            val = event.values[0];
            if(val < max*0.25){

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
