package md.paynet.wifip2ptestapp;

import android.app.Application;

/**
 * Created by daniil on 02.11.16.
 */
public class AppSingle extends Application {

    private static final String TAG = "ApplicationSingleton";

    private static AppSingle instance;
    public static AppSingle getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;


    }
}
