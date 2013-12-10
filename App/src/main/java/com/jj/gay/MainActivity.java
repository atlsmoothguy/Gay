package com.jj.gay;

import android.hardware.Sensor;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;
import android.media.MediaPlayer;

public class MainActivity extends ActionBarActivity
{

    private SensorManager mSensorManager;
    private ShakeListener mSensorListener;
    private TextView t;
    private MediaPlayer mp;
    private MediaPlayer mp2;
    private MediaPlayer mp3;

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(this, R.raw.gay);

        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        mSensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        mSensorListener = new ShakeListener();

        mSensorListener.setOnShakeListener(new ShakeListener.OnShakeListener() {

            public void onShake()
            {
                if(i == 0)
                {
                    mp.start();
                    ++i;
                }
                else if (i == 1)
                {
                    mp2.start();
                    ++i;
                }
                else
                {
                    mp3.start();
                    i = 0;
                }

            }
        });




}


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment
    {

        public PlaceholderFragment()
        {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onStop();
    }


}
