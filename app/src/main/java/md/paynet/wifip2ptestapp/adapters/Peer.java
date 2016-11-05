package md.paynet.wifip2ptestapp.adapters;

/**
 * Created by AMD on 11/5/16.
 */

public class Peer {
    private String deviceName;

    public Peer(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
