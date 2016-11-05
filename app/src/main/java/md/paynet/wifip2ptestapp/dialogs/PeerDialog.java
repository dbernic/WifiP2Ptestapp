package md.paynet.wifip2ptestapp.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import md.paynet.wifip2ptestapp.R;

/**
 * Created by AMD on 11/5/16.
 */

public class PeerDialog extends Dialog{
    private final String TAG = getClass().getSimpleName();
    private TextView peerDescription;

    public PeerDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_peer);
        peerDescription = (TextView) findViewById(R.id.peerDescription);
    }

    public void setPeerDescription(String description) {
        peerDescription.setText(description);
    }

}
