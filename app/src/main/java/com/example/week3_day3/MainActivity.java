package com.example.week3_day3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.week3_day3.MyDandDIntentService.ACTION_CHARACTER;

public class MainActivity extends AppCompatActivity {
    public static final String BROADCAST_CODE = "value";
    RecyclerView recyclerView;
    @BindView(R.id.tvBroadcast)
    TextView tvBroadcast;
    MyBroadcastReceiver myBroadcastReceiver;
    ArrayList<DndCharacter> dndCharacterForRV = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        myBroadcastReceiver = new MyBroadcastReceiver();
        recyclerView = findViewById(R.id.rvDD);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);
        intentFilter.addAction(Intent.ACTION_REBOOT);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(ACTION_CHARACTER);
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);
    }

    public void sendIntent(View view) {
        //Toast.makeText(view.getContext(), "Button MESSAGE", Toast.LENGTH_SHORT).show();
        dndCharacterForRV.clear();
        Intent intent = new Intent();
        intent.setAction("character");
        MyDandDIntentService.startActionCharacter(this);

        //sendBroadcast(intent);
    }

    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
                tvBroadcast.setText("Airplane Mode Has Changed");
                Toast.makeText(context, "Airplane Mode", Toast.LENGTH_SHORT).show();
            }
            if (Intent.ACTION_BATTERY_LOW.equals(intent.getAction())) {
                tvBroadcast.setText("The Screen has Turned on");
                Toast.makeText(context, "Screen On", Toast.LENGTH_SHORT).show();
            }
            if (Intent.ACTION_HEADSET_PLUG.equals(intent.getAction())) {
                tvBroadcast.setText("headset has been plugged in");
                Toast.makeText(context, "Headset In", Toast.LENGTH_SHORT).show();
            }
            if (Intent.ACTION_REBOOT.equals(intent.getAction())) {
                tvBroadcast.setText("the system has rebooted");
                Toast.makeText(context, "System Rebooted", Toast.LENGTH_SHORT).show();
            }
            if (Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())) {
                tvBroadcast.setText("Power has been connected");
                Toast.makeText(context, "Power Connected", Toast.LENGTH_SHORT).show();
            }
            if (ACTION_CHARACTER.equals(intent.getAction())) {
                Log.d("CHARACTER", "onReceive: ");
                Bundle bundle = intent.getExtras();
                DndCharacter dndCharacter = bundle.getParcelable("dndCharacter");
                dndCharacterForRV.add(dndCharacter);
                DDRVAdapter ddrvAdapter = new DDRVAdapter(dndCharacterForRV);
                recyclerView.setAdapter(ddrvAdapter);
            }
            if("character".equals(action)){


            }
        }

    }


}
