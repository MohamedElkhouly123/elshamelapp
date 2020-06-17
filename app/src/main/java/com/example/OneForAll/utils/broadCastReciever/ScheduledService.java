package com.example.OneForAll.utils.broadCastReciever;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.JobIntentService;

public class ScheduledService extends JobIntentService {
//    extends JobIntentService
    private static final int UNIQUE_JOB_ID=1337;

    static void enqueueWork(Context ctxt) {
        enqueueWork(ctxt, ScheduledService.class, UNIQUE_JOB_ID,
                new Intent(ctxt, ScheduledService.class));
    }

    @Override
    public void onHandleWork(Intent i) {
        Log.d(getClass().getSimpleName(), "I ran!");


    }


//    @Override
//    public void onCreate() {
//        super.onCreate();
//                PollReceiver.scheduleAlarms(this);
//
//    }
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
}