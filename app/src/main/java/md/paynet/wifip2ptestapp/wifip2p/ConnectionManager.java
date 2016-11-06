package md.paynet.wifip2ptestapp.wifip2p;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import md.paynet.wifip2ptestapp.util.ActivityHolder;
import md.paynet.wifip2ptestapp.util.PrefHelper;

/**
 * Created by daniil on 05.11.16.
 */
public class ConnectionManager implements WifiP2pManager.ConnectionInfoListener, Handler.Callback {

    private final String TAG = "ConnectionManager";
    Handler handler = new Handler(this);
    SocketStreamManager streamMan;

    Context context;

    public ConnectionManager(Context context) {
        this.context = context;
    }

    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo info) {

        Log.i(TAG, "ConnectionInfo Available");
        Thread thread;

        if (info.isGroupOwner) {
            Log.i(TAG, "Connected as group owner");
            Utils.writeLog("Connected as group owner");
            try {
                thread = new GroupOwnerSocketHandler(handler);
            } catch (IOException e) {
                Log.d(TAG, "Failed to create a server thread - " + e.getMessage());
                return;
            }
        } else {
            Utils.writeLog("Connected as peer");
            Log.d(TAG, "Connected as peer");
            thread = new ClientSocketHandler(handler, info.groupOwnerAddress);
        }

        thread.start();

    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            case Utils.MESSAGE_READ:
                String receive = (String) msg.obj;
                Log.i(TAG, "Received: "+receive);
                Utils.writeLog("Received: "+receive);
                //TODO
                break;

            case Utils.CONNECTED:
                Object obj = msg.obj;
                streamMan = (SocketStreamManager) obj;
                String name = new PrefHelper(context).getDeviceName();
                Utils.writeLog("Sending: "+name);
                Log.i(TAG, "Sending: "+name);
                streamMan.write(name); //TODO

                break;

            case Utils.DISCONNECTED:

                streamMan = null;

                break;

        }
        return true;
    }
}
