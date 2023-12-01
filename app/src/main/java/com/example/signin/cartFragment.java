package com.example.signin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signin.adapters.MyCartAdapter;
import com.example.signin.models.MyCartModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class cartFragment extends Fragment {
    RecyclerView recyclerView;
  List<MyCartModel>List;

            MyCartAdapter cartAdapter;
    private DatabaseReference cart;
    DatabaseReference cartinfo;
   FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    TextView tp;
    Integer TotalPrice=0;
    String pid;
    Button Buynow;
     ImageView image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root =(ViewGroup) inflater.inflate(R.layout.fragment_cart,container,false);

        image = root.findViewById(R.id.image);
        tp=root.findViewById(R.id.tp);
        Buynow=root.findViewById(R.id.buy_now);

         Buynow.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), payment.class);
        intent.putExtra("key",tp.getText().toString());
        startActivity(intent);


    }
});

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        recyclerView= root.findViewById(R.id.cart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

       List=new ArrayList<>();
        cartAdapter=new MyCartAdapter(getActivity(),List);
        recyclerView.setAdapter(cartAdapter);
//+
////        totalprice=root.findViewById(R.id.totalprice);
////        LocalBroadcastManager.getInstance(getActivity()
////                .registerReceiver(mMessageReciever,new IntentFilter("TotalAmount")));




        cart=FirebaseDatabase.getInstance().getReference("CartList").child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Products");
//        FirebaseDatabase.getInstance().getReference("CartList")
//                .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                .child("Products")
                cart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//                System.out.println(snapshot.child("Products").getKey());
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    System.out.println(dataSnapshot.child("id").getValue());
//                     pid=dataSnapshot.child("id").getValue().toString();
//                     cartinfo=FirebaseDatabase.getInstance().getReference("products").child(pid);
//                     cartinfo.addValueEventListener(new ValueEventListener() {
//                         @Override
//                         public void onDataChange(@NonNull DataSnapshot snapshot) {
//                             System.out.println(snapshot.getValue());
//
//
//                         }
//
//                         @Override
//                         public void onCancelled(@NonNull DatabaseError error) {
//
//                         }
//                     })


                    MyCartModel myCartModel=dataSnapshot.getValue(MyCartModel.class);
                    TotalPrice =TotalPrice+ Integer.valueOf(myCartModel.getPrice());

                    if (cartAdapter.getItemCount() == -1) {

                        recyclerView.setVisibility(View.GONE);
                        image.setVisibility(View.VISIBLE);
                    } else {
                        recyclerView.setVisibility(View.VISIBLE);
                        image.setVisibility(View.GONE);
                    }

                    List.add(myCartModel);

                }
                tp.setText((String.valueOf(TotalPrice)));
                cartAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
//        firebaseDatabase.getReference("CartList").child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                .child("Products").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if(task.isSuccessful()){
////                    for(DataSnapshot dataSnapshot:task.getResult().getChildren()){
////                        MyCartModel myCartModel=dataSnapshot.getValue(MyCartModel.class);
////                        cartModelList.add(myCartModel);
////                    }
////                    cartAdapter.notifyDataSetChanged();
//                    for(DataSnapshot dataSnapshot :task.getResult().getChildren()){
//
//                        MyCartModel myCartModel=dataSnapshot.getValue(MyCartModel.class);
//                       List.add(myCartModel);
//
//
//                    }
//                   cartAdapter.notifyDataSetChanged();
//                }
//            }
//        });



        return root;
    }



//    public BroadcastReceiver mMessageReciever = new BroadcastReceiver(){
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//        }
//    };

}