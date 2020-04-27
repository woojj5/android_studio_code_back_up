package  org.techtown.testapii4;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<item> mList;
    private LayoutInflater mInflate;
    private Context mContext;

    public MyAdapter(Context context, ArrayList<item> items) {
        this.mList = items;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //binding
        holder.locationNo1.setText(mList.get(position).getLocationNo1());
        holder.plateNo1.setText(mList.get(position).getPlateNo1());
        holder.routeId.setText(mList.get(position).getRouteId());
        holder.stationId.setText(mList.get(position).getStationId());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView locationNo1;
        public TextView plateNo1;
        public TextView routeId;
        public TextView stationId;

        public MyViewHolder(View itemView) {
            super(itemView);

            locationNo1 = (TextView) itemView.findViewById(R.id.tv_locationNo1);
            plateNo1 = (TextView) itemView.findViewById(R.id.tv_plateNo1);
            routeId = (TextView) itemView.findViewById(R.id.tv_routeId);
            stationId = (TextView) itemView.findViewById(R.id.tv_stationId);

        }
    }
}