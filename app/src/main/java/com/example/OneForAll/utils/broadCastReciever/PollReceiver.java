package com.example.OneForAll.utils.broadCastReciever;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.Toast;

public class PollReceiver extends BroadcastReceiver {
    private static final int PERIOD=5000; // 15 minutes equal 900000
    private static final int INITIAL_DELAY=2000; // 5 seconds

    @Override
    public void onReceive(Context ctxt, Intent i) {
//        for repeat
        if (i.getAction() == null) {
            ScheduledService.enqueueWork(ctxt);
            Toast.makeText(ctxt, "every 5 second", Toast.LENGTH_LONG)
                    .show();
        }
        else {
            scheduleAlarms(ctxt);

        }
//                if (i.getAction() != null) {
//
//        if (i.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
//                        scheduleAlarms(ctxt);
//            Toast.makeText(ctxt, "every 5 second", Toast.LENGTH_LONG)
//                .show();}}
    }

    public static void scheduleAlarms(Context ctxt) {
        AlarmManager mgr=
                (AlarmManager)ctxt.getSystemService(Context.ALARM_SERVICE);
        Intent i=new Intent(ctxt, PollReceiver.class);
        PendingIntent pi= PendingIntent.getBroadcast(ctxt, 0, i, 0);
// to repeate
//        mgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                SystemClock.elapsedRealtime() + INITIAL_DELAY,
//                PERIOD, pi);

        // Set the alarm for the next seconds.
        mgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        3 * 1000, pi);    }
}