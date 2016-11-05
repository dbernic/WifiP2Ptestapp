package md.paynet.wifip2ptestapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import md.paynet.wifip2ptestapp.adapters.Peer;
import md.paynet.wifip2ptestapp.adapters.PeersAdapter;
import md.paynet.wifip2ptestapp.util.PrefHelper;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    private final String TAG = getClass().getSimpleName();
    private RecyclerView peersRecycler;

    private PeersAdapter peersAdapter;
    private PrefHelper prefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefHelper = new PrefHelper(this);
        if (prefHelper.getDeviceName().length() == 0) {
            showAlertDialog();
        }
        initViews();
    }

    private void showAlertDialog() {
        final EditText editText = new EditText(this);
        new AlertDialog.Builder(this).setView(editText).setMessage("Enter Device Name")
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        prefHelper.setDeviceName(editText.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    private void initViews() {
        ((ToggleButton)findViewById(R.id.scanButton)).setOnCheckedChangeListener(this);

        peersRecycler = (RecyclerView) findViewById(R.id.peerRecyclerView);
        peersAdapter = new PeersAdapter(tmpDummyListInit());
        peersRecycler.setLayoutManager(new LinearLayoutManager(this));
        peersRecycler.setAdapter(peersAdapter);
    }

    private List<Peer> tmpDummyListInit() {
        List<Peer> peerList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            peerList.add(new Peer("Device " + i));
        }
        return peerList;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.scanButton:

                break;
        }
    }
}
