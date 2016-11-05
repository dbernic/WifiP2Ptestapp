package md.paynet.wifip2ptestapp.util;

import md.paynet.wifip2ptestapp.MainActivity;
import md.paynet.wifip2ptestapp.dialogs.PeerDialog;

/**
 * Created by daniil on 05.11.16.
 */
public class ActivityHolder {

    private static ActivityHolder instance;

    public static ActivityHolder getInstance() {
        if(instance==null)  instance = new ActivityHolder();
        return instance;
    }


    private MainActivity mainActivity;
    private PeerDialog peerDialog;

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    public PeerDialog getPeerDialog() {
        return peerDialog;
    }

    public void setPeerDialog(PeerDialog peerDialog) {
        this.peerDialog = peerDialog;
    }
}
