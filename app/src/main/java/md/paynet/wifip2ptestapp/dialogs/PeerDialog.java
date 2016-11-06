package md.paynet.wifip2ptestapp.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import md.paynet.wifip2ptestapp.AppSingle;
import md.paynet.wifip2ptestapp.R;
import md.paynet.wifip2ptestapp.util.ActivityHolder;

/**
 * Created by AMD on 11/5/16.
 */

public class PeerDialog extends Dialog{
    private final String TAG = getClass().getSimpleName();
    private TextView peerDescription;
    AppSingle app;

    public PeerDialog(AppSingle app, Context context) {
        super(context);
        this.app=app;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_peer);
        peerDescription = (TextView) findViewById(R.id.peerDescription);
        ActivityHolder.getInstance().setPeerDialog(this);

        findViewById(R.id.disconnectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.disconnect();
            }
        });

        findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        ActivityHolder.getInstance().setPeerDialog(null);
    }

    public void setPeerDescription(String description) {
        peerDescription.setText(peerDescription.getText().toString()+"\n"+description);
    }

}
