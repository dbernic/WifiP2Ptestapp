package md.paynet.wifip2ptestapp.wifip2p;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import md.paynet.wifip2ptestapp.AppSingle;

/**
 * Created by daniil on 05.11.16.
 */
public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {

    private final String TAG = "wifiBroadcastRec";

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        Log.i(TAG, "Action received: "+action);
        if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

            NetworkInfo networkInfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);

            if (networkInfo.isConnected()) {

                Log.i(TAG, "--- Request connection info ---");
                ((AppSingle) context).getWifiP2pManager().requestConnectionInfo(
                        ((AppSingle) context).getChannel(),
                        new ConnectionManager()
                );

            } else {
                Log.i(TAG, "Connection finished?");
            }

        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {

            WifiP2pDevice device = intent.getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_DEVICE);
            Log.i(TAG, "Device status -" + device.status);

        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

            //TODO
        }

        /* else if (ALARM_PEER_DISCOVER.equals(action)){
            Log.i(TAG, "Alarm to discover");
            if (!ApplicationSingleton.isConnected) {
                startWakefulService(context, new Intent(context, WifiService.class));
            }

        } */

    }
}
