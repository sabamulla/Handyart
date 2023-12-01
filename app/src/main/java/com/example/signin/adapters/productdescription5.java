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
import com.example.signin.models.WishlistModel;
import com.example.signin.payment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class productdescription5 extends AppCompatActivity {

    TextView productname;
    TextView minirating;
    TextView productprice;
    TextView productdescription;
    TextView numrating;
    ImageView productImg;
    Button cart;
    TextView productID ;
    Button buy;
    TextView maxprice;
    //

    WishlistModel wishlistModel =null;

    ///// rating layout
    private LinearLayout rateNowContainer;
    ///// rating layout
    private static boolean ALREADY_ADDED_TO_WISHLIST = false;
    private FloatingActionButton addToWishlistButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdescription5);


        productID=findViewById(R.id.pid);
        cart=findViewById(R.id.cart_btn);
        addToWishlistButton = findViewById(R.id.add_to_wishlist_button);
        productname=findViewById(R.id.product_title);
        minirating=findViewById(R.id.product_rating_miniview);
        productprice=findViewById(R.id.product_price);
        productdescription=findViewById(R.id.product_details_body);
        numrating=findViewById(R.id.avg_rating);
        productImg=findViewById(R.id.product_image);
        maxprice=findViewById(R.id.cutted_price);
        buy = findViewById(R.id.buy_now_btn);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(productdescription5.this, payment.class);
                intent.putExtra("key",productprice.getText().toString());
                startActivity(intent);
            }
        });

        //
        FirebaseDatabase.getInstance().getReference("products");
        final Object obj1 = getIntent().getSerializableExtra("detailedwish");
        if(obj1 instanceof WishlistModel){
           wishlistModel = (WishlistModel) obj1;

        }

        if (wishlistModel != null) {
            Glide.with(getApplicationContext()).load(wishlistModel.getImg_url()).into(productImg);
            productID.setText(String.valueOf(wishlistModel.getId()));
            productname.setText(wishlistModel.getName());
            minirating.setText(String.valueOf(wishlistModel.getRatings()));
            productprice.setText(String.valueOf(wishlistModel.getPrice()));
            productdescription.setText(String.valueOf(wishlistModel.getDescription()));
            maxprice.setText(String.valueOf(wishlistModel.getPrice()));
//            productdescription.setText(wishlistModel.getDescription());
            //  numrating.setText(String.valueOf(allproductsModel.getRating()));

        }
//        Intent intent=getIntent();
//        String detailed=intent.getStringExtra("detailed");


        String description = "";
        String[] descArray = String.valueOf(wishlistModel.getDescription()).split("\\#");
        if (descArray.length > 1) {
            for (String item: descArray) {
                if (item != "#") {
                    String newString = "\n•  ".concat(item);
                    description = description + newString;
                }
            }
        } else {
            description = "• " + String.valueOf(wishlistModel.getDescription());
        }
        productdescription.setText(description);




        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String saveCurrentTime,saveCurrentDate;
                Calendar calForDate =  Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                saveCurrentDate = currentDate.format(calForDate.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calForDate.getTime());

                final HashMap<String,Object> cartMap = new HashMap<>();
                cartMap.put("name",productname.getText().toString());
                cartMap.put("img_url",wishlistModel.getImg_url());
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

            }
        });



        addToWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ALREADY_ADDED_TO_WISHLIST) {
                    ALREADY_ADDED_TO_WISHLIST = false;

                    addToWishlistButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9f9f9f")));

                    FirebaseDatabase.getInstance().getReference("Whishlist")
                            .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("favProducts").child(wishlistModel.getId()).removeValue();
                    Toast.makeText(productdescription.getContext(), "Item remove from your favourites", Toast.LENGTH_SHORT).show();

                }else{
                    ALREADY_ADDED_TO_WISHLIST = true;
                    addToWishlistButton.setSupportImageTintList(getResources().getColorStateList(R.color.red));
                    String saveCurrentTime,saveCurrentDate;
                    Calendar calForDate =  Calendar.getInstance();
                    SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                    saveCurrentDate = currentDate.format(calForDate.getTime());

                    SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                    saveCurrentTime = currentTime.format(calForDate.getTime());

                    final HashMap<String,Object> cartMap = new HashMap<>();
                    cartMap.put("name",productname.getText().toString());
                    cartMap.put("img_url",wishlistModel.getImg_url());
                    cartMap.put("price",productprice.getText().toString());
                    cartMap.put("maxprice",maxprice.getText().toString());
                    cartMap.put("ratings",minirating.getText().toString());
                    cartMap.put("description",productdescription.getText().toString());
                    cartMap.put("id",productID.getText().toString());
                    cartMap.put("currentDate",saveCurrentDate);
                    cartMap.put("currentTime",saveCurrentTime);
//        cartMap.put("quantity",)
                    FirebaseDatabase.getInstance().getReference("Whishlist")
                            .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("favProducts").child(productID.getText().toString()).setValue(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(productdescription.getContext(), "Item added to your favourites", Toast.LENGTH_SHORT).show();

                            }


                        }
                    });

                }
            }
        });
        /////rating layout
//        rateNowContainer = findViewById(R.id.rate_now_container);
//        for (int x = 0;x <rateNowContainer.getChildCount() ;x++){
//            final int starPosition = x;
//            rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    setRating(starPosition);
//                }
//            });
//        }
//    }
//    //stars
//        private void setRating(int starPosition) {
//        for(int x =0;x < rateNowContainer.getChildCount();x++){
//            ImageView starBtn = (ImageView)rateNowContainer.getChildAt(x);
//            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
//            if (x <= starPosition){
//                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
//            }
//        }
//    }


    }
}