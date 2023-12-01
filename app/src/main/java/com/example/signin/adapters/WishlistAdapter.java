package com.example.signin.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.signin.R;
import com.example.signin.models.WishlistModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class WishlistAdapter extends  RecyclerView.Adapter<WishlistAdapter.ViewHolder>{

    final Context context;
    final List<WishlistModel> list;

    DatabaseReference ref;
    DatabaseReference remove;
//    String ImageUrl;
//    String id;

    public WishlistAdapter(Context context, List<WishlistModel> list) {
        this.context = context;
        this.list =list;
        remove=FirebaseDatabase.getInstance().getReference("Whishlist");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favitems,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.cartImg);
        holder.pname.setText(list.get(position).getName());
        holder.price.setText(String.valueOf(list.get(position).getPrice()));
        String description=list.get(position).getDescription();
        String rating =list.get(position).getRatings();
        String maxprice =list.get(position).getMaxprice();
//         String id = list.get(position).getId();


//        ref= FirebaseDatabase.getInstance().getReference("Whishlist");
//
//        Query query=ref.child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                .child("favProducts");

//        holder.cart.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                ValueEventListener eventListener = new ValueEventListener() {
//                    int position = holder.getAdapterPosition();
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        if(snapshot.exists()){
//                            for (DataSnapshot ds : snapshot.getChildren()) {
//
//                                System.out.println(ds);
//                                ImageUrl= ds.child("img_url").getValue(String.class);
//                                id=ds.child("id").getValue(String.class);
//                                String saveCurrentTime,saveCurrentDate;
//                                Calendar calForDate =  Calendar.getInstance();
//                                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
//                                saveCurrentDate = currentDate.format(calForDate.getTime());
//
//                                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
//                                saveCurrentTime = currentTime.format(calForDate.getTime());
//
//                                final HashMap<String,Object> cartMap = new HashMap<>();
//                                cartMap.put("name", list.get(position).getName());
//                                cartMap.put("img_url",ImageUrl);
//                                cartMap.put("price",list.get(position).getPrice());
//                                cartMap.put("id",list.get(position).getId());
//                                cartMap.put("ratings",list.get(position).getRatings());
//                                cartMap.put("maxprice",list.get(position).getMaxprice());
//                                cartMap.put("description",list.get(position).getDescription());
//                                cartMap.put("currentDate",saveCurrentDate);
//                                cartMap.put("currentTime",saveCurrentTime);
////        cartMap.put("quantity",)
//                                FirebaseDatabase.getInstance().getReference("CartList")
//                                        .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                        .child("Products").child(id).setValue(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (task.isSuccessful()){
//                                            Toast.makeText(context.getApplicationContext(), "Item added to your bag!", Toast.LENGTH_SHORT).show();
//
//                                        }
//                                    }
//                                });
//
//                            }
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//
//
//                    }
//                };
//                query.addListenerForSingleValueEvent(eventListener);
//
//            }
//        });



        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent intent=new Intent(context,productdescription5.class);
                intent.putExtra("detailedwish",list.get(position));
                context.startActivity(intent);
            }
        });

        remove= FirebaseDatabase.getInstance().getReference("Whishlist");

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                remove= FirebaseDatabase.getInstance().getReference("Whishlist").child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("favProducts")
                        .child(list.get(position).getId());
                remove.child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("favProducts")
                        .child(list.get(position).getId()).removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                int position = holder.getAdapterPosition();
                                if(task.isSuccessful()){
                                    remove.removeValue();
                                    list.remove(list.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context," Item removed from your favourites!",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(context,"Error"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });


            }
        });


//        holder.delete.setOnClickListener((view -> {
//            AlertDialog.Builder builder=new AlertDialog.Builder(holder.cartImg.getContext());
//            builder.setTitle("Delete panel");
//            builder.setMessage("Delete...?");
//
//            builder.setPositiveButton("yes",((dialogInterface, i) -> {
//                remove=FirebaseDatabase.getInstance().getReference("Allproducts").child("CartList").child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                        .child("Products");
//                remove.child(list.get(position).getId()).removeValue();
//            }));
//            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//
//                }
//            });
//            builder.show();
//
//        }));

    }

    @Override
    public int getItemCount() {
        return list.size();

//        if (!list.isEmpty()) {
//            return list.size() + COUNT_FOOTER;
//        } else {
//            return COUNT_NO_ITEMS + COUNT_FOOTER;
//        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cartImg;
        TextView pname;
        TextView price;
        ImageView delete;
        TextView id;
        TextView totalp;
//        Button cart;

        String ImageUrl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartImg=itemView.findViewById(R.id.cart_Img);
            pname=itemView.findViewById(R.id.textView22);
            price=itemView.findViewById(R.id.Price);
            delete=itemView.findViewById(R.id.delete);
//            cart=itemView.findViewById(R.id.cart);
//           id=itemView.findViewById(R.id.id);
//            totalp=itemView.findViewById(R.id.tps);
        }
    }
}
