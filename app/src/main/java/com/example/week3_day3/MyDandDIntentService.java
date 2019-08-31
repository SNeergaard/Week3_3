package com.example.week3_day3;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class MyDandDIntentService extends IntentService {

    private static final String TAG = "TAG_MyDnDIntentService";
    public static final String ACTION_CHARACTER = "com.example.week3_day3.action.FOO";
//    private static final String DESCRIPTOR = "com.example.week3_day3.extra.DESCRIPTOR";
//    private static final String RACE = "com.example.week3_day3.extra.RACE";
//    private static final String CCLASS = "com.example.week3_day3.extra.CCLASS";
//    private static final String WHAT_DO = "com.example.week3_day3.extra.WHAT_DO";

    public MyDandDIntentService() {
        super("MyDandDIntentService");
    }

    public static void startActionCharacter(Context context/*, String descriptor, String race, String cClass, String whatDO*/) {
        Intent intent = new Intent(context, MyDandDIntentService.class);
        intent.setAction(ACTION_CHARACTER);
//        intent.putExtra(DESCRIPTOR, descriptor);
//        intent.putExtra(RACE, race);
//        intent.putExtra(CCLASS , cClass);
//        intent.putExtra(WHAT_DO, whatDO);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            DndCharacter dndCharacter = new DndCharacter();
            Intent broadcastIntent = new Intent();
            Log.d(TAG, "onHandleIntent: dndCharacter: " + dndCharacter);
            broadcastIntent.setAction(ACTION_CHARACTER);
            Bundle b = new Bundle();
            b.putParcelable("dndCharacter", dndCharacter);
            broadcastIntent.putExtras(b);
            sendBroadcast(broadcastIntent);
        }
    }


//    private void handleActionCharacter(String descriptor, String race, String cClass, String whatDO) {
//
//        final String total = descriptor + race + cClass + whatDO;
//        Intent intent  = new Intent();
//        Bundle bundle = new Bundle();
//        bundle.putString(BROADCAST_CODE, total);
//        intent.putExtras(bundle);
//        getBaseContext().sendBroadcast(intent);
//    }

}
