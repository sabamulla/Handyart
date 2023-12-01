package com.example.signin.adapters;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.signin.R;
import com.example.signin.models.AllproductsModel;
import com.example.signin.models.MyCartModel;
import com.example.signin.payment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class productdescription4 extends AppCompatActivity {

    TextView productname;
    TextView minirating;
    TextView productprice;
    TextView productdescription;
    TextView numrating;
    ImageView productImg;
    Button cart;
    TextView productID ;
    DatabaseReference ref;
    AllproductsModel allproductsModel = null;
    String imageUrl;
    String id;
    Button buy;
    MyCartModel myCartModel = null;
    TextView maxprice;

    ///// rating layout
    private LinearLayout rateNowContainer;
    ///// rating layout
    private static boolean ALREADY_ADDED_TO_WISHLIST = false;
    private FloatingActionButton addToWishlistButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdescription4);

        productID=findViewById(R.id.pid);
        cart=findViewById(R.id.cart_btn);
        addToWishlistButton = findViewById(R.id.add_to_wishlist_button);
        productname=findViewById(R.id.product_title);
        minirating=findViewById(R.id.product_rating_miniview);
        productprice=findViewById(R.id.product_price);
        productdescription=findViewById(R.id.product_details_body);
        numrating=findViewById(R.id.avg_rating);
        productImg=findViewById(R.id.product_image);
        buy = findViewById(R.id.buy_now_btn);
        maxprice=findViewById(R.id.cutted_price);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(productdescription4.this, payment.class);                intent.putExtra("key",productprice.getText().toString());
                intent.putExtra("key",productprice.getText().toString());
                startActivity(intent);
            }
        });



        Intent intent=getIntent();
        String detailed=intent.getStringExtra("detailed");



        ref= FirebaseDatabase.getInstance().getReference("products");
//        Query query=ref.orderByChild()
//        ref.get().geValueEventListener eventListener=new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    for (DataSnapshot ds:snapshot.getChildren()){
////                        Glide.with(getApplicationContext()).load(allproductsModel.getImg_url().into());
//                        productID.setText(ds.child("pid").getValue(String.class));
//                        productname.setText(ds.child("name").getValue(String.class));
//                        minirating.setText(ds.child("ratings").getValue(String.class));
//                        productprice.setText(ds.child("price").getValue(String.class));
//                        productdescription.setText(ds.child("description").getValue(String.class));
//                        numrating.setText(ds.child("ratings").getValue(String.class));
//
//
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        };

        Query query=ref.orderByKey().equalTo(detailed);
        ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                users pdata = snapshot.getValue(.class);
                if(snapshot.exists()){
                    for (DataSnapshot ds:snapshot.getChildren()){
                        System.out.println(ds);
                        imageUrl=ds.child("img_url").getValue(String.class);
                        Glide.with(getApplicationContext()).load(ds.child("img_url").getValue(String.class)).into(productImg);
                        productID.setText(ds.child("pid").getValue(String.class));
                        productname.setText(ds.child("name").getValue(String.class));
                        minirating.setText(String.valueOf(ds.child("ratings").getValue(Long.class)));
                        productprice.setText(String.valueOf(ds.child("price").getValue(Long.class)));
                        productdescription.setText(ds.child("description").getValue(String.class));
                        maxprice.setText(ds.child("maxprice").getValue(String.class));
                        //                       numrating.setText(String.valueOf(ds.child("ratings").getValue(Long.class)));

//                        String description = "";
//
//                        String[] descArray = String.valueOf(allproductsModel.getDescription()).split("\\#");
//                        if (descArray.length > 1) {
//                            for (String item: descArray) {
//                                if (item != "#") {
//                                    String newString = "\n•  ".concat(item);
//                                    description = description + newString;
//                                }
//                            }
//                        } else {
//                            description = "• " + productdescription;
//                        }
//                        productdescription.setText(description);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        query.addListenerForSingleValueEvent(eventListener);


        //

//        String description = "";
//        String[] descArray = String.valueOf(allproductsModel.getDescription()).split("\\#");
//        if (descArray.length > 1) {
//            for (String item: descArray) {
//                if (item != "#") {
//                    String newString = "\n•  ".concat(item);
//                    description = description + newString;
//                }
//            }
//        } else {
//            description = "• " + String.valueOf(allproductsModel.getDescription());
//        }
//        productdescription.setText(description);


        //cart
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                users pdata = snapshot.getValue(.class);
                        if (snapshot.exists()) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                System.out.println(ds);
                                imageUrl = ds.child("img_url").getValue(String.class);
                                id=ds.child("id").getValue(String.class);
                                String saveCurrentTime,saveCurrentDate;
                                Calendar calForDate =  Calendar.getInstance();
                                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                                saveCurrentDate = currentDate.format(calForDate.getTime());

                                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                                saveCurrentTime = currentTime.format(calForDate.getTime());

                                final HashMap<String,Object> cartMap = new HashMap<>();
                                cartMap.put("name",productname.getText().toString());
                                cartMap.put("img_url",imageUrl);
                                cartMap.put("price",productprice.getText().toString());
                                cartMap.put("id",productID.getText().toString());
                                cartMap.put("ratings",minirating.getText().toString());
                                cartMap.put("maxprice",maxprice.getText().toString());
                                cartMap.put("description",productdescription.getText().toString());
                                cartMap.put("currentDate",saveCurrentDate);
                                cartMap.put("currentTime",saveCurrentTime);
//        cartMap.put("quantity",)
                                FirebaseDatabase.getInstance().getReference("CartList")
                                        .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .child("Products").child(productID.getText().toString()).setValue(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()){
                                            Toast.makeText(productdescription.getContext(), "Item added to your bag!", Toast.LENGTH_SHORT).show();

                                        }


                                    }
                                });
                                //                       numrating.setText(String.valueOf(ds.child("ratings").getValue(Long.class)));
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };
                query.addListenerForSingleValueEvent(eventListener);
            }
            });

        addToWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(ALREADY_ADDED_TO_WISHLIST) {
//                    ALREADY_ADDED_TO_WISHLIST = false;
////
////                    addToWishlistButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9f9f9f")));
////
////                    FirebaseDatabase.getInstance().getReference("Whishlist")
////                            .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
////                            .child("favProducts").child(productID.getText().toString()).removeValue();
//                    Toast.makeText(productdescription.getContext(), "Item remove from your favourites", Toast.LENGTH_SHORT).show();
////
//                }else {
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                users pdata = snapshot.getValue(.class);
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if (ALREADY_ADDED_TO_WISHLIST) {
                                ALREADY_ADDED_TO_WISHLIST = false;

                                addToWishlistButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9f9f9f")));

                                FirebaseDatabase.getInstance().getReference("Whishlist")
                                        .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .child("favProducts").child(productID.getText().toString()).removeValue();
                                Toast.makeText(productdescription.getContext(), "Item remove from your favourites", Toast.LENGTH_SHORT).show();

                            } else {
                                ALREADY_ADDED_TO_WISHLIST = true;
                                addToWishlistButton.setSupportImageTintList(getResources().getColorStateList(R.color.red));
                                System.out.println(ds);
                                imageUrl = ds.child("img_url").getValue(String.class);
                                String saveCurrentTime, saveCurrentDate;
                                Calendar calForDate = Calendar.getInstance();
                                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                                saveCurrentDate = currentDate.format(calForDate.getTime());

                                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                                saveCurrentTime = currentTime.format(calForDate.getTime());

                                final HashMap<String, Object> cartMap = new HashMap<>();
                                cartMap.put("name", productname.getText().toString());
                                cartMap.put("img_url", imageUrl);
                                cartMap.put("price", productprice.getText().toString());
                                cartMap.put("maxprice", maxprice.getText().toString());
                                cartMap.put("ratings",minirating.getText().toString());
                                cartMap.put("description", productdescription.getText().toString());
                                cartMap.put("id", productID.getText().toString());
                                cartMap.put("currentDate", saveCurrentDate);
                                cartMap.put("currentTime", saveCurrentTime);
//        cartMap.put("quantity",)
                                FirebaseDatabase.getInstance().getReference("Whishlist")
                                        .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .child("favProducts").child(productID.getText().toString()).setValue(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            Toast.makeText(productdescription.getContext(), "Item added to your bag!", Toast.LENGTH_SHORT).show();

                                        }


                                    }
                                });

                                //                       numrating.setText(String.valueOf(ds.child("ratings").getValue(Long.class)));
                            }
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };

                query.addListenerForSingleValueEvent(eventListener);






            }

        });








        //
        // 1) Retriveing the whishlist items
        FirebaseDatabase.getInstance().getReference("Whishlist")
                .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("favProducts")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            // 2) Checking if the item is there in the whishlist by comparing the ids
                            String id = dataSnapshot.child("id").getValue(String.class);
                            if (id == productID.getText().toString()) {
                                // 3) If the ids are matching than setting the red color & updating the local flag.
                                ALREADY_ADDED_TO_WISHLIST = false;
                                addToWishlistButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }
}