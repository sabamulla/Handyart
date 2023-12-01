package com.example.signin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signin.adapters.AllproductsAdapter;
import com.example.signin.adapters.CustomAdapter;
import com.example.signin.models.AllproductsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class search_activity extends AppCompatActivity {

    private AutoCompleteTextView searchb;
//    private ImageButton searchbtn;

    private DatabaseReference reference;

    //
//    AllproductsAdapter allproductsAdapter;
    //
    CustomAdapter customAdapter;
    AllproductsAdapter allproductsAdapter;
    List<AllproductsModel> List;
    private RecyclerView mResultList;
    String info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        reference = FirebaseDatabase.getInstance().getReference("products");

        searchb = findViewById(R.id.searchbox);
//        searchbtn=findViewById(R.id.imageButton);
        mResultList = (RecyclerView) findViewById(R.id.result_list);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),2);
        mResultList.setLayoutManager(layoutManager);
//
//        allproductsModelList=new ArrayList<>();
//        allproductsAdapter = new AllproductsAdapter(getApplicationContext(),allproductsModelList);
//        mResultList.setLayoutManager(new GridLayoutManager(search_activity.this, 2));
//        mResultList.setAdapter(allproductsAdapter);
//        mResultList.setHasFixedSize(true);
//        searchb.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if(editable.toString().isEmpty()){
//                    allproductsModelList.clear();
//                    allproductsAdapter.notifyDataSetChanged();
//                }else{
//                    searchProduct(editable.toString());
//                }
//
//            }
//        });

//        ValueEventListener event=new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                populateSearch(snapshot);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        };
//        reference.addListenerForSingleValueEvent(event);
////        mResultList.setHasFixedSize(true);
////        mResultList.setLayoutManager(new GridLayoutManager(this, 2));
//
////        searchbtn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////
////                productdisplay();
////
////            }
////        });
////
////    }
////
////    private void productdisplay() {
////
////
////        FirebaseRecyclerAdapter<AllproductsModel,productsearchholder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<AllproductsModel, productsearchholder>(
////                AllproductsModel.class,
////                R.layout.search_view,
////                productsearchholder.class,
////                reference
////        ) {
////            @Override
////            protected void onBindViewHolder(@NonNull productsearchholder holder, int position, @NonNull AllproductsModel model) {
////
////                holder.setDetails(model.getName(),model.getImg_url());
////            }
////
////            @NonNull
////            @Override
////            public productsearchholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////                return null;
////            }
////        };
////        mResultList.setAdapter(firebaseRecyclerAdapter);
////
////        }
////
////
////
////    }
////
////
////    class  productsearchholder extends RecyclerView.ViewHolder{
////
////        View mView;
////        public productsearchholder(@NonNull View itemView) {
////            super(itemView);
////            mView = itemView;
////
////
////        }
////
////        public void setDetails(  String pname, String img_url ) {
////            ImageView productImg=(ImageView) mView.findViewById(R.id.all_img);
////                    TextView productName=(TextView) mView.findViewById(R.id.all_name);
////
////            productName.setText(pname);
////            Glide.with(mView).load(img_url).into(productImg);
//        }


//    private void searchProduct(String type) {
//
//        if(!type.isEmpty()){
//            reference=FirebaseDatabase.getInstance().getReference("products");
//                    reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DataSnapshot> task) {
//                    if(task.isSuccessful()&& task.getResult() !=null){
//                        allproductsModelList.clear();
//                        allproductsAdapter.notifyDataSetChanged();
//                        for(DataSnapshot dataSnapshot : task.getResult().getChildren()){
//
//                            AllproductsModel allproductsModel=dataSnapshot.getValue(AllproductsModel.class);
//                            allproductsModelList.add(allproductsModel);
//                            allproductsAdapter.notifyDataSetChanged();
//
//                        }
//
//                    }
//                }
//            });
////            reference.addValueEventListener(new ValueEventListener() {
////                @Override
////                public void onDataChange(@NonNull DataSnapshot snapshot) {
////                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
////
////                        AllproductsModel allproductsModel=dataSnapshot.getValue(AllproductsModel.class);
////                        allproductsModelList.add(allproductsModel);
////
////                    }
////                    allproductsAdapter.notifyDataSetChanged();
////                }
////
////                @Override
////                public void onCancelled(@NonNull DatabaseError error) {
////
////                }
////            });
//        }
//    }

//    private void populateSearch(DataSnapshot snapshot) {
//        ArrayList<String> names=new ArrayList<>();
//        if(snapshot.exists())
//        {
//            for(DataSnapshot ds:snapshot.getChildren())
//            {
//                String name=ds.child("name").getValue(String.class);
//                names.add(name);
//            }
//            ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,names);
//           searchb.setAdapter(adapter);
//        }else{
//            Log.d("products","No data found");
//        }
//
//
//    }


        popularSearch();


    }

    private void popularSearch() {
        ValueEventListener eventListener= new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    ArrayList<String> names=new ArrayList<>() ;
                    for(DataSnapshot ds:snapshot.getChildren())
                    {
                        String n=ds.child("name").getValue(String.class);
                        names.add(n);

                    }
                    ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,names);
                    searchb.setAdapter(adapter);
                    searchb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String Selection= parent.getItemAtPosition(position).toString();
                            products(Selection);
                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
            };
        reference.addListenerForSingleValueEvent(eventListener);
    }

    private void products(String selection) {
        Query query =reference.orderByChild("name").equalTo(selection);
        ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                   List =new ArrayList<>();
                    for(DataSnapshot ds: snapshot.getChildren()){
                        AllproductsModel allproductsModel=ds.getValue(AllproductsModel.class);

                       List.add(allproductsModel);
                    }
                    allproductsAdapter = new AllproductsAdapter(search_activity.this, List);
                   mResultList.setAdapter(allproductsAdapter);
//
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query.addListenerForSingleValueEvent(eventListener);

    }
}
