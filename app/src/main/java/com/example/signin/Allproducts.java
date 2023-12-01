package com.example.signin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signin.adapters.AllproductsAdapter;
import com.example.signin.models.AllproductsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Allproducts extends AppCompatActivity {

    RecyclerView recyclerView;
    //All products
    AllproductsAdapter allproductsAdapter;
    List<AllproductsModel> allproductsModelList;

    private DatabaseReference allproduct;
    private DatabaseReference catname;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allproducts);

        recyclerView=findViewById(R.id.cat_all);
        String name= getIntent().getStringExtra("name").toLowerCase();

        String test="festive decor";
        System.out.println(name);

       recyclerView.setLayoutManager(new GridLayoutManager(Allproducts.this, 2));
       recyclerView.setHasFixedSize(true);
        allproductsModelList = new ArrayList<>();
        allproductsAdapter = new AllproductsAdapter(Allproducts.this, allproductsModelList);
       recyclerView.setAdapter(allproductsAdapter);


        allproduct = FirebaseDatabase.getInstance().getReference("products");



        allproduct.orderByChild("category").equalTo(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                 System.out.println(dataSnapshot.getValue());


                    AllproductsModel allproductsModel=dataSnapshot.getValue(AllproductsModel.class);
                    allproductsModelList.add(allproductsModel);


                }
                allproductsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Allproducts.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }

        });
//        if (name!=null && name.equalsIgnoreCase("Needle work")){
//
//            catname= FirebaseDatabase.getInstance().getReference("products");
//            catname.orderByChild("category").equalTo("Needle work")
//                    .addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//
//
//                                AllproductsModel allproductsModel=dataSnapshot.getValue(AllproductsModel.class);
//                                allproductsModelList.add(allproductsModel);
//
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//
//
//
//
//
//
//
//        }
//        catname = FirebaseDatabase.getInstance().getReference("products").child("category");
//        if (type!=null && type.equalsIgnoreCase("needle work")){
//            allproduct = FirebaseDatabase.getInstance().getReference("products").equalTo("needle work","category");
//
//            allproduct.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//
//
//                        AllproductsModel allproductsModel=dataSnapshot.getValue(AllproductsModel.class);
//                        allproductsModelList.add(allproductsModel);
//
//                    }
//                    allproductsAdapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                    Toast.makeText(Allproducts.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                }
//
//            });
//
//        }

        title=findViewById(R.id.title);
        Intent intent = getIntent();
        String s = intent.getStringExtra("name");
        title.setText(s);


    }
}