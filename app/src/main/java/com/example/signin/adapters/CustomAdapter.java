package com.example.signin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.signin.R;
import com.example.signin.models.AllproductsModel;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<AllproductsModel> mDataset;
    private Context context;

    public CustomAdapter(List<AllproductsModel> mDataset, Context context) {
        this.mDataset = mDataset;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AllproductsModel thisproduct=mDataset.get(position);
        Glide.with(context).load(mDataset.get(position).getImg_url()).into(holder.Imgurl);
        holder.pname.setText(mDataset.get(position).getName());

    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView pname;
        ImageView Imgurl;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            pname=(TextView)itemView.findViewById(R.id.all_name);
            Imgurl=(ImageView)itemView.findViewById(R.id.all_img);

        }
        public TextView getTextView() {
            return pname;

        }
    }
}


