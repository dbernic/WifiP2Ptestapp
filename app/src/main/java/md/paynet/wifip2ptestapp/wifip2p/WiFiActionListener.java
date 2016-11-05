package md.paynet.wifip2ptestapp.wifip2p;

import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

/**
 * Created by daniil on 05.11.16.
 */
class WiFiActionListener implements WifiP2pManager.ActionListener{

    private String action;
    private final String TAG = "wifiActionListener";

    public WiFiActionListener(String action) {
        this.action = action;
    }

    @Override
    public void onSuccess() {
        Log.i(TAG, "Action "+action+" success");
    }

    @Override
    public void onFailure(int reason) {
        Log.i(TAG, "Action "+action+" failed: " + reason);
    }
}
