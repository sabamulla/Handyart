package com.example.signin.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.signin.Productdescription2;
import com.example.signin.R;
import com.example.signin.models.AllproductsModel;

import java.util.ArrayList;
import java.util.List;

public class AllproductsAdapter extends RecyclerView.Adapter<AllproductsAdapter.ViewHolder> implements Filterable {
    private Context context;
    private List<AllproductsModel> allproductsModelList;
    private List<AllproductsModel> backup;
//    private static boolean ALREADY_ADDED_TO_WISHLIST = false;
//    private FloatingActionButton addToWishlistButton;

    public AllproductsAdapter(Context context, List<AllproductsModel> allproductsModelList) {
        this.context = context;
        this.allproductsModelList = allproductsModelList;
        this.backup=new ArrayList<>(allproductsModelList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_products,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(allproductsModelList.get(position).getImg_url()).into(holder.imageview);
        holder.textview.setText(allproductsModelList.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(context, Productdescription2.class);
                intent.putExtra("detailed1",allproductsModelList.get(position));
                context.startActivity(intent);

            }
        });
//        holder.addToWishlistButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = holder.getAdapterPosition();
//                holder.addToWishlistButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9f9f9f")));
//
//                if (ALREADY_ADDED_TO_WISHLIST) {
//                    ALREADY_ADDED_TO_WISHLIST = false;
//                    holder.addToWishlistButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9f9f9f")));
//
//                    Toast.makeText(context.getApplicationContext(), "Item removed from your favourites", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    ALREADY_ADDED_TO_WISHLIST = true;
//                    holder.addToWishlistButton.setSupportImageTintList(context.getResources().getColorStateList(R.color.red));
//                    String saveCurrentTime, saveCurrentDate;
//                    Calendar calForDate = Calendar.getInstance();
//                    SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
//                    saveCurrentDate = currentDate.format(calForDate.getTime());
//
//                    SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
//                    saveCurrentTime = currentTime.format(calForDate.getTime());
//
//                    final HashMap<String, Object> cartMap = new HashMap<>();
//                    cartMap.put("name", allproductsModelList.get(position).getName());
//                    cartMap.put("img_url",allproductsModelList.get(position).getImg_url());
//                    cartMap.put("price", allproductsModelList.get(position).getPrice());
//                    cartMap.put("id", allproductsModelList.get(position).getPid());
//                    cartMap.put("maxprice",allproductsModelList.get(position).getMaxprice());
//                    cartMap.put("description",allproductsModelList.get(position).getDescription());
//                    cartMap.put("currentDate", saveCurrentDate);
//                    cartMap.put("currentTime", saveCurrentTime);
////        cartMap.put("quantity",)
//                    FirebaseDatabase.getInstance().getReference("Whishlist")
//                            .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                            .child("favProducts").child(allproductsModelList.get(position).getPid()).setValue(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//
//                            if (task.isSuccessful()) {
//                                Toast.makeText(context.getApplicationContext(), "Item added to your favourites", Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    });
//
//
//                }
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return allproductsModelList.size();
    }

    @Override
    public Filter getFilter() {

        return filter;
    }

    Filter filter= new Filter() {
        //background thread
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            List<AllproductsModel> filtereddata = new ArrayList<>();
            if(keyword.toString().isEmpty()) {
                filtereddata.addAll(backup);
            } else
                {
                    for(AllproductsModel obj: backup) {
                        if(obj.getName().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                            filtereddata.add(obj);



                }
            }

            FilterResults results=new FilterResults();
            results.values=filtereddata;
            return  results;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            allproductsModelList.clear();
           allproductsModelList.addAll((ArrayList<AllproductsModel>)results.values);
            notifyDataSetChanged();

        }
    };
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageview;
        TextView textview;
//        FloatingActionButton addToWishlistButton;
//        private boolean ALREADY_ADDED_TO_WISHLIST = false;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            addToWishlistButton=itemView.findViewById(R.id.fav);
            imageview =itemView.findViewById(R.id.all_img);
            textview=itemView.findViewById(R.id.all_name);
        }
    }
}
