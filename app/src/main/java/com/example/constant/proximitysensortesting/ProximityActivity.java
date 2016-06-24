package com.example.constant.proximitysensortesting;

import android.content.Context;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.widget.TextView;

public class ProximityActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager sensorManager;
    Sensor sensor;
    TextView txtType, txtRange, txtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtType = (TextView)findViewById(R.id.type);
        txtRange = (TextView)findViewById(R.id.range);
        txtValue = (TextView)findViewById(R.id.value);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);


        if(sensor == null)
            txtType.setText(txtType.getText()+" No Proximity Sensor Detected");
        else {
            txtType.setText(txtType.getText() + " " + sensor.getName());
            txtRange.setText(txtRange.getText()+" "+sensor.getMaximumRange());
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_PROXIMITY)
        {
            txtValue.setText(txtValue.getText());
            txtValue.setText(txtValue.getText()+" "+ String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
