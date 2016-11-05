package md.paynet.wifip2ptestapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by AMD on 11/5/16.
 */

public class PrefHelper {
    public static final String APP_SHARED_PREFERENCES = "sharedPreferences";
    public static final String DEVICE_NAME = "deviceName";

    private SharedPreferences sharedPreferences;

    public PrefHelper(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getDeviceName() {
        return sharedPreferences.getString(DEVICE_NAME, "");
    }

    public void setDeviceName(String deviceName) {
        sharedPreferences.edit().putString(DEVICE_NAME, deviceName).apply();
    }
}
