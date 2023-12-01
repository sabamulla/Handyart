package com.example.signin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signin.adapters.WishlistAdapter;
import com.example.signin.models.WishlistModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class favoritesFragment extends Fragment {
//    Button button;
RecyclerView recyclerView;
    java.util.List<WishlistModel> List;

    WishlistAdapter wishlistAdapter;
    private DatabaseReference wish;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    TextView totalprice;
    int TotalPrice=0;

    ImageView image;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_favorites, container, false);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        recyclerView= root.findViewById(R.id.wish);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List=new ArrayList<>();
        wishlistAdapter=new WishlistAdapter(getActivity(),List);
        recyclerView.setAdapter(wishlistAdapter);
//+
////        totalprice=root.findViewById(R.id.totalprice);
////        LocalBroadcastManager.getInstance(getActivity()
////                .registerReceiver(mMessageReciever,new IntentFilter("TotalAmount")));

        image = root.findViewById(R.id.image);


        wish=FirebaseDatabase.getInstance().getReference("Whishlist").child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("favProducts");
//        FirebaseDatabase.getInstance().getReference("CartList")
//                .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                .child("Products")
        wish.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){


                    WishlistModel wishlistModel=dataSnapshot.getValue(WishlistModel.class);


                    if (wishlistAdapter.getItemCount() == -1) {

                        recyclerView.setVisibility(View.GONE);
                        image.setVisibility(View.VISIBLE);
                    } else {
                        recyclerView.setVisibility(View.VISIBLE);
                        image.setVisibility(View.GONE);
                    }

                    List.add(wishlistModel);

                }
                wishlistAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });



        return root;
    }
}




