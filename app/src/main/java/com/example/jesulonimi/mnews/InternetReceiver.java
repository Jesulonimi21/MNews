package com.example.jesulonimi.mnews;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class InternetReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
boolean noConnectivity=intent.getBooleanExtra(
        ConnectivityManager.EXTRA_NO_CONNECTIVITY,false
);

if(noConnectivity){
    StyleableToast.makeText(context,"check your internet connection, \nnews might not load without internet connection",R.style.customToast).show();
}
    }
}
