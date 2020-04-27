package org.techtown.testapi_2;

import android.content.Context;;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class BusArriveAdapter extends RecyclerView.Adapter<BusArriveAdapter.Holder> {
    Context context;
    private ArrayList<BusArriveItem> busArriveItems;

    public BusArriveAdapter(ArrayList<BusArriveItem> busInfoItemList, MainActivity mainActivity) {
        busArriveItems = busInfoItemList;
        context = mainActivity;
    }

    @NonNull
    @Override
    public BusArriveAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.busitem,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BusArriveAdapter.Holder holder, int position) {
        BusArriveItem data = busArriveItems.get(position);
        holder.plateNo1.setText(data.getPlateNo1());
        holder.locationNo1.setText(data.getLocationNo1());
        holder.plateNo2.setText(data.getPlateNo2());
        holder.locationNo2.setText(data.getLocationNo2());
    }

    @Override
    public int getItemCount() {
        return busArriveItems.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView plateNo1;
        TextView locationNo1;
        TextView plateNo2;
        TextView locationNo2;

        public Holder(View itemView) {
            super(itemView);
            plateNo1 = itemView.findViewById(R.id.listcell_plateNo1);
            locationNo1 = itemView.findViewById(R.id.listcell_locationNo1);
            plateNo2 = itemView.findViewById(R.id.listcell_plateNo2);
            locationNo2 = itemView.findViewById(R.id.listcell_locationNo2);
        }
    }
}
