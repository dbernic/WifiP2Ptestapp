
package md.paynet.wifip2ptestapp.wifip2p;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Handles reading and writing of messages with socket buffers. Uses a Handler
 * to post messages to UI thread for UI updates.
 */
public class SocketStreamManager implements Runnable {
    private Socket socket = null;
    private Handler handler;
    private boolean needFinish = false;
    private boolean isAlienFinish = false;
    private boolean isSelfFinish = false;

    public SocketStreamManager(Socket socket, Handler handler) {
        this.socket = socket;
        this.handler = handler;
    }

    private InputStreamReader iStream;
    private OutputStream oStream;
    private static final String TAG = "wifiChatHandler";

    @Override
    public void run() {
        try {

            iStream = new InputStreamReader(socket.getInputStream(),"UTF-8");
            oStream = socket.getOutputStream();


            int chr;
            StringBuilder sb = new StringBuilder();

            handler.obtainMessage(Utils.CONNECTED, this).sendToTarget();

            while (!needFinish && (chr = iStream.read()) != -1) {

                if (chr != 0) {
                    sb.append((char) chr);
                    //Log.i(TAG, "String "+sb.toString());
                } else {
                    Log.i(TAG, "Receiving complete");
                    handler.obtainMessage(Utils.MESSAGE_READ, sb.toString()).sendToTarget();
                    sb = new StringBuilder();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAlienFinish() {
        isAlienFinish = true;
        if (isSelfFinish) disconnect();
    }

    public void setSelfFinish() {
        isSelfFinish = true;
        if (isAlienFinish) disconnect();
    }

    public void disconnect(){
        needFinish = true;
        Log.i(TAG, "disconnected by call");
        try {
            iStream.close();
            oStream.close();
            socket.close();
            handler.obtainMessage(Utils.DISCONNECTED).sendToTarget();

        } catch (Exception e){
            Log.e(TAG, "Exception: "+e.getMessage());
        }

    }

    public synchronized void write(String s){
        try {

            byte[] msgBytes = s.getBytes();
            Log.i(TAG, "sending");
            oStream.write(msgBytes);
            oStream.flush();
            oStream.write(0);
            oStream.flush();

        } catch (Exception e) {
            Log.e(TAG, "Exception during write", e);
        }
    }


}
