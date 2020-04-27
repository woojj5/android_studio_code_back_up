package org.techtown.testapi_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {

    private ArrayList<item> mList;
    private LayoutInflater mInflate;
    private Context mContext;

        public Myadapter(Context context, ArrayList<item> itmes) {
            this.mList = itmes;
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
            holder.locationNo1.setText(mList.get(position).locationNo1);
            holder.plateNo1.setText(mList.get(position).plateNo1);
            holder.routeId.setText(mList.get(position).routeId);

            //Click event
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
            public TextView locationNo2;
            public TextView predictTime1;
            public TextView predictTime2;


            public MyViewHolder(View itemView) {
                super(itemView);

                locationNo1 = itemView.findViewById(R.id.tv_locationNo1);
                plateNo1 = itemView.findViewById(R.id.tv_plateNo1);
                routeId = itemView.findViewById(R.id.tv_routeId);

            }
        }
    }