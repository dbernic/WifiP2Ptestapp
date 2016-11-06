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

    public DiscoveryService(){
        super("WifiTest");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent");
        ((AppSingle)getApplication()).getWifiP2pManager().discoverPeers(
                ((AppSingle)getApplication()).getChannel(),
                Utils.getActionListener("DiscoverStart"));
    }


}
