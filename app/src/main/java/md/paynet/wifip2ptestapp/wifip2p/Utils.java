package md.paynet.wifip2ptestapp.wifip2p;

import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

/**
 * Created by daniil on 05.11.16.
 */
public class Utils {

    public static int SERVER_PORT = 4223;

    public static final int MESSAGE_READ = 0x400 + 1;
    public static final int CONNECTED = 0x400 + 2;
    public static final int DISCONNECTED = 0x400 + 3;

    public static WifiP2pManager.ActionListener getActionListener(String string){
        return new WiFiActionListener(string);
    }

}
