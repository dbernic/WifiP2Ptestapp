package md.paynet.wifip2ptestapp.services;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.util.Log;

import md.paynet.wifip2ptestapp.AppSingle;
import md.paynet.wifip2ptestapp.wifip2p.Utils;

/**
 * Created by AMD on 11/6/16.
 */

public class DiscoveryService extends IntentService{
    private final String TAG = getClass().getSimpleName();
    private final int FOREGROUND_ID = 32;
    private Notification foregroundNotification;

    public DiscoveryService(){
        super("");
    }

    public DiscoveryService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ");
//        foregroundNotification = Notification.
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//        startForeground(FOREGROUND_ID, );
        Log.e(TAG, "onHandleIntent: ");
        ((AppSingle)getApplication()).getWifiP2pManager().discoverPeers(
                ((AppSingle)getApplication()).getChannel(),
                Utils.getActionListener("DiscoverStart"));
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.e(TAG, "onStart: ");
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        ((AppSingle) getApplicationContext()).getWifiP2pManager().stopPeerDiscovery(
                ((AppSingle) getApplicationContext()).getChannel(),
                Utils.getActionListener("DiscoverStop"));
    }
}
