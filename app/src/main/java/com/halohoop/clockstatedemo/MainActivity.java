package com.halohoop.clockstatedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "halohoop";
    private MyClockReceiver myClockReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myClockReceiver = new MyClockReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.tplink.tpclock.ALARM_ALERT");//这里是自研的需要换成原生的
        filter.addAction("com.tplink.tpclock.ALARM_DONE");//这里是自研的需要换成原生的
        registerReceiver(myClockReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myClockReceiver != null) {
            unregisterReceiver(myClockReceiver);
        }
    }


    public void logi(String s) {
        Log.i(TAG, "halohoop:" + s);
    }

    class MyClockReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                if ("com.tplink.tpclock.ALARM_ALERT".equals(action)) {
                    logi("ALARM_ALERT");
                } else if ("com.tplink.tpclock.ALARM_DONE".equals(action)) {
                    logi("ALARM_DONE");
                }
            }
        }
    }
}
