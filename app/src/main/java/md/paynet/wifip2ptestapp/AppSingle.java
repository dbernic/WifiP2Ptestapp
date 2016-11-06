package md.paynet.wifip2ptestapp;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import md.paynet.wifip2ptestapp.services.WifiService;
import md.paynet.wifip2ptestapp.wifip2p.Utils;
import md.paynet.wifip2ptestapp.wifip2p.WiFiDirectBroadcastReceiver;

/**
 * Created by daniil on 02.11.16.
 */
public class AppSingle extends Application {

    private final String TAG = "ApplicationSingleton";

    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel channel;
    private IntentFilter intentFilter = new IntentFilter();
    private BroadcastReceiver receiver;


    @Override
    public void onCreate() {
        super.onCreate();

        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter
                .addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter
                .addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        receiver = new WiFiDirectBroadcastReceiver();
        registerReceiver(receiver, intentFilter);

        wifiP2pManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = wifiP2pManager.initialize(this, getMainLooper(), null);
    }

    public void startDiscover(){
        startService(new Intent(this, WifiService.class));

    }

    public WifiP2pManager getWifiP2pManager() {
        return wifiP2pManager;
    }

    public WifiP2pManager.Channel getChannel() {
        return channel;
    }

    public void initConnection(final String mac){
        final WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = mac;
        config.wps.setup = WpsInfo.PBC;
        Utils.writeLog("Try to connect");
        wifiP2pManager.connect(channel, config, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "Connecting to "+mac);
                Utils.writeLog("Connecting to "+mac);
            }

            @Override
            public void onFailure(int reason) {
                Log.i(TAG, "Connecting failed. Reason: "+reason);
                Utils.writeLog("Connecting failed. Reason: "+reason);
            }
        });

    }

    public void disconnect(){
        wifiP2pManager.cancelConnect(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "Disconnecting to ");
                Utils.writeLog("Disconnecting to ");
            }

            @Override
            public void onFailure(int reason) {
                Log.i(TAG, "Disconnecting failed. Reason: "+reason);
                Utils.writeLog("Disconnecting failed. Reason: "+reason);
            }
        });
    }

}
