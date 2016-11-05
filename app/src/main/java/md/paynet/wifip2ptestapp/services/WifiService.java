package md.paynet.wifip2ptestapp.services;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import md.paynet.wifip2ptestapp.AppSingle;
import md.paynet.wifip2ptestapp.wifip2p.Utils;

/**
 * Created by daniil on 02.02.16.
 */
public class WifiService extends IntentService {

    private static final String TAG = "wifiService";
    private int interval = 50000;
    public WifiService() {
        super("WiFiService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        ((AppSingle)getApplication()).getWifiP2pManager().discoverPeers(
                ((AppSingle)getApplication()).getChannel(),
                        Utils.getActionListener("DiscoverStart"));


    }


}
