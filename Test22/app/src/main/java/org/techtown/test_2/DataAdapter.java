package org.techtown.test_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends  RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private Context context;
    ArrayList<item> arrayList =  new ArrayList<>();
    public DataAdapter(Context context, ArrayList<item> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.custom_layout,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvName.setText(arrayList.get(position).getStNm());
        holder.tvIcon.setText(arrayList.get(position).getStId());
        holder.tvId.setText(arrayList.get(position).getAdir());
        holder.tvSearchUrl.setText(arrayList.get(position).getArrmsg1());
        holder.tvNativeUrl.setText(arrayList.get(position).getArrmsg2());
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvIcon,tvId,tvSearchUrl,tvNativeUrl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tvName);
            tvIcon=itemView.findViewById(R.id.tvIcon);
            tvId=itemView.findViewById(R.id.tvId);
            tvSearchUrl=itemView.findViewById(R.id.tvSearchUrl);
            tvNativeUrl=itemView.findViewById(R.id.tvNativeUrl);
        }
    }
}
