package md.paynet.wifip2ptestapp.wifip2p;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import md.paynet.wifip2ptestapp.AppSingle;
import md.paynet.wifip2ptestapp.util.ActivityHolder;

/**
 * Created by daniil on 05.11.16.
 */
public class PeersManager {

    private final String TAG = "wifiPeersMgr";

    public void requestPeers(AppSingle app){
        WifiP2pManager.PeerListListener peerListListener = new WifiP2pManager.PeerListListener() {

            @Override
            public void onPeersAvailable(WifiP2pDeviceList peers) {
                Log.i(TAG, "Peers Available: "+peers.getDeviceList().size());

                if (ActivityHolder.getInstance().getMainActivity()!=null){
                    ActivityHolder.getInstance().getMainActivity().updatePeerList(peers.getDeviceList());
                }
            }
        };

        app.getWifiP2pManager().requestPeers(
                app.getChannel(), peerListListener
        );
    }

}
