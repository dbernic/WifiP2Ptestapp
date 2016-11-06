package md.paynet.wifip2ptestapp.adapters;

import android.net.wifi.p2p.WifiP2pDevice;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import md.paynet.wifip2ptestapp.AppSingle;
import md.paynet.wifip2ptestapp.R;
import md.paynet.wifip2ptestapp.dialogs.PeerDialog;

/**
 * Created by AMD on 11/5/16.
 */

public class PeersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<WifiP2pDevice> itemList;

    AppSingle app;

    public PeersAdapter(AppSingle app, List<WifiP2pDevice> itemList) {
        this.itemList = itemList;
        this.app = app;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_peer, parent, false);
        return new PeersHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PeersHolder myHolder = (PeersHolder) holder;
        myHolder.peerName.setText(itemList.get(position).deviceName);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    protected class PeersHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected TextView peerName;

        public PeersHolder(View itemView) {
            super(itemView);
            peerName = (TextView) itemView.findViewById(R.id.peerName);
            peerName.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.peerName:
                    app.initConnection(itemList.get(getAdapterPosition()).deviceAddress);
                    PeerDialog peerDialog = new PeerDialog(view.getContext());
                    peerDialog.show();
            }
        }
    }
}
