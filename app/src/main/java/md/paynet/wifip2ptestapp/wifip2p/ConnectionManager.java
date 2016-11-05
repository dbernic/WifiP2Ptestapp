package md.paynet.wifip2ptestapp.wifip2p;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by daniil on 05.11.16.
 */
public class ConnectionManager implements WifiP2pManager.ConnectionInfoListener, Handler.Callback {

    private final String TAG = "ConnectionManager";
    Handler handler = new Handler(this);
    SocketStreamManager streamMan;

    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo info) {

        Log.i(TAG, "ConnectionInfo Available");
        Thread thread;

        if (info.isGroupOwner) {
            Log.i(TAG, "Connected as group owner");
            try {
                thread = new GroupOwnerSocketHandler(handler);
            } catch (IOException e) {
                Log.d(TAG, "Failed to create a server thread - " + e.getMessage());
                return;
            }
        } else {
            Log.d(TAG, "Connected as peer");
            thread = new ClientSocketHandler(handler, info.groupOwnerAddress);
        }

        thread.start();

    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            case Utils.MESSAGE_READ:

                //TODO
                break;

            case Utils.CONNECTED:
                Object obj = msg.obj;
                streamMan = (SocketStreamManager) obj;

                Log.i(TAG, "Sending: ");
                streamMan.write("TODO"); //TODO

                break;

            case Utils.DISCONNECTED:

                streamMan = null;

                break;

        }
        return true;
    }
}
