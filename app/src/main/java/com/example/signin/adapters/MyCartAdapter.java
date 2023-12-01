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
import com.example.signin.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MyCartAdapter extends  RecyclerView.Adapter<MyCartAdapter.ViewHolder>{

    final Context context;
    final List<MyCartModel> list;
    int TotalP ;



    DatabaseReference remove;

    public MyCartAdapter(Context context, List<MyCartModel> list) {
        this.context = context;
        this.list =list;
         remove=FirebaseDatabase.getInstance().getReference("CartList");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.cartImg);
        holder.pname.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice() );
//        String id=list.get(position).getId();
//        System.out.println(id);
//        holder.id.setText(list.get(position).getPrice());
//        holder.totalp.setText(TotalPrice= TotalPrice+list.get(position).getPrice());

//      String TotalPrice = TotalP +list.get(position).getPrice();
//        Intent intent = new Intent("TotalAmount");
//        intent.putExtra("totalamount",TotalPrice);



        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Intent intent=new Intent(context,productdescription3.class);
                intent.putExtra("detailed3",list.get(position));
                context.startActivity(intent);
            }
        });

         remove= FirebaseDatabase.getInstance().getReference("CartList");

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                remove= FirebaseDatabase.getInstance().getReference("CartList").child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("Products")
                        .child(list.get(position).getId());
                remove.child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Products")
                        .child(list.get(position).getId()).removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                int position = holder.getAdapterPosition();
                                if(task.isSuccessful()){
                                    remove.removeValue();
                                    list.remove(list.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context,"Item removed from your cart",Toast.LENGTH_LONG).show();
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartImg=itemView.findViewById(R.id.cart_Img);
           pname=itemView.findViewById(R.id.textView22);
           price=itemView.findViewById(R.id.Price);
           delete=itemView.findViewById(R.id.delete);
//           id=itemView.findViewById(R.id.id);
           totalp=itemView.findViewById(R.id.tp);
        }
    }
}
