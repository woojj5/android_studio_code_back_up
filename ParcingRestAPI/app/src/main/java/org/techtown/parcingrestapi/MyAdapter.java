package org.techtown.parcingrestapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter< MyAdapter.MyViewHolder> {
    private ArrayList<Item> alist;
    private LayoutInflater minflater;
    private Context context;

    public MyAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.alist =items;
        this.minflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = minflater.inflate(R.layout.item, parent, false);
        MyViewHolder hold =new MyViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.t.setText(alist.get(position).stId);
        holder.t1.setText(alist.get(position).stNm);
        holder.t2.setText(alist.get(position).adir);
        holder.t3.setText(alist.get(position).armsg1);
        holder.t4.setText(alist.get(position).armsg2);
        holder.t5.setText(alist.get(position).rtNm);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Toast.makeText(context,position+ "", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return alist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView t;
        public TextView t1;
        public TextView t2;
        public TextView t3;
        public TextView t4;
        public TextView t5;

        public MyViewHolder(View view){
            super(view);

            t = view.findViewById(R.id.textView2);
            t1 = view.findViewById(R.id.textView4);
            t2 = view.findViewById(R.id.textView6);
            t3 = view.findViewById(R.id.textView8);
            t4 = view.findViewById(R.id.textView10);
            t5 = view.findViewById(R.id.textView12);

        }
    }
}
