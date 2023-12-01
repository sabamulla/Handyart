package com.example.signin.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.signin.R;
import com.example.signin.models.EditorsChoiceModel;

import java.util.List;

public class EditorsChoiseAdapter extends RecyclerView.Adapter<EditorsChoiseAdapter.ViewHolder> {
     final Context context;
     final List<EditorsChoiceModel> list;

    public EditorsChoiseAdapter(Context context, List<EditorsChoiceModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.editors_choice,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.choiceImg);
        holder.choiceName.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(context,Poductdescription.class);
                intent.putExtra("detailed",list.get(position));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView choiceImg;
        TextView choiceName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            choiceImg=itemView.findViewById(R.id.product_img);
            choiceName=itemView.findViewById(R.id.product_name);

        }
    }
}
