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
import com.example.signin.models.MyCartModel;
import com.example.signin.payment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class productdescription3 extends AppCompatActivity {

    TextView productname;
    TextView minirating;
    TextView productprice;
    TextView productdescription;
    TextView numrating;
    ImageView productImg;
    Button cart;
    TextView productID;
    Button buy;
    TextView maxprice;

    //
    MyCartModel myCartModel = null;
    DatabaseReference reference;

    ///// rating layout
    private LinearLayout rateNowContainer;
    ///// rating layout
    private static boolean ALREADY_ADDED_TO_WISHLIST = false;
    private FloatingActionButton addToWishlistButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_productdescription3);
        productID = findViewById(R.id.pid);
        cart = findViewById(R.id.cart_btn);
        addToWishlistButton = findViewById(R.id.add_to_wishlist_button);
        productname = findViewById(R.id.product_title);
        minirating = findViewById(R.id.product_rating_miniview);
        productprice = findViewById(R.id.product_price);
        productdescription = findViewById(R.id.product_details_body);
        numrating = findViewById(R.id.avg_rating);
        productImg = findViewById(R.id.product_image);
        buy=findViewById(R.id.buy_now_btn);
        maxprice=findViewById(R.id.cutted_price);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(productdescription3.this, payment.class);
                intent.putExtra("key",productprice.getText().toString());
                startActivity(intent);
            }
        });

        //firebase
        FirebaseDatabase.getInstance().getReference("CartList")
                .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Products");

        final Object obj1 = getIntent().getSerializableExtra("detailed3");
        if (obj1 instanceof MyCartModel) {
            myCartModel = (MyCartModel) obj1;

        }

        if (myCartModel != null) {
            Glide.with(getApplicationContext()).load(myCartModel.getImg_url()).into(productImg);
            productID.setText(myCartModel.getId());
            productname.setText(myCartModel.getName());
            minirating.setText(String.valueOf(myCartModel.getRatings()));
            productprice.setText(myCartModel.getPrice());
            productdescription.setText(myCartModel.getDescription());
//            numrating.setText(String.valueOf(myCartModel.getRating()));

        }




        addToWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ALREADY_ADDED_TO_WISHLIST) {
                    ALREADY_ADDED_TO_WISHLIST = false;

                    addToWishlistButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9f9f9f")));

                    Toast.makeText(productdescription.getContext(), "Item removed from your favourites", Toast.LENGTH_SHORT).show();
                } else {

                    ALREADY_ADDED_TO_WISHLIST = true;
                    addToWishlistButton.setSupportImageTintList(getResources().getColorStateList(R.color.red));
                    String saveCurrentTime, saveCurrentDate;
                    Calendar calForDate = Calendar.getInstance();
                    SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                    saveCurrentDate = currentDate.format(calForDate.getTime());

                    SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                    saveCurrentTime = currentTime.format(calForDate.getTime());

                    final HashMap<String, Object> cartMap = new HashMap<>();
                    cartMap.put("name", productname.getText().toString());
                    cartMap.put("img_url", myCartModel.getImg_url());
                    cartMap.put("price", productprice.getText().toString());
                    cartMap.put("id", productID.getText().toString());
                    cartMap.put("ratings",minirating.getText().toString());
                    cartMap.put("maxprice",maxprice.getText().toString());
                    cartMap.put("description",myCartModel.getDescription());
                    cartMap.put("currentDate", saveCurrentDate);
                    cartMap.put("currentTime", saveCurrentTime);
//        cartMap.put("quantity",)
                    FirebaseDatabase.getInstance().getReference("Whishlist")
                            .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .child("favProducts").child(productID.getText().toString()).setValue(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(productdescription.getContext(), "Item added to your favourites", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }
            }
        });

        String description = "";
        String[] descArray = String.valueOf(myCartModel.getDescription()).split("\\#");
        if (descArray.length > 1) {
            for (String item: descArray) {
                if (item != "#") {
                    String newString = "\n•  ".concat(item);
                    description = description + newString;
                }
            }
        } else {
            description = "• " + String.valueOf(myCartModel.getDescription());
        }
        productdescription.setText(description);



        //cart
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String saveCurrentTime, saveCurrentDate;
                Calendar calForDate = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                saveCurrentDate = currentDate.format(calForDate.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calForDate.getTime());

                final HashMap<String, Object> cartMap = new HashMap<>();
                cartMap.put("name", productname.getText().toString());
                cartMap.put("img_url", myCartModel.getImg_url());
                cartMap.put("price", productprice.getText().toString());
                cartMap.put("maxprice",maxprice.getText().toString());
                cartMap.put("description",myCartModel.getDescription());
                cartMap.put("ratings",minirating.getText().toString());
                cartMap.put("id", productID.getText().toString());
                cartMap.put("currentDate", saveCurrentDate);
                cartMap.put("currentTime", saveCurrentTime);
//        cartMap.put("quantity",)
                FirebaseDatabase.getInstance().getReference("CartList")
                        .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("Products").child(productID.getText().toString()).setValue(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(productdescription.getContext(), "Item added to your cart!", Toast.LENGTH_SHORT).show();

                        }


                    }
                });

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
//
//    }
//    //stars
//    private void setRating(int starPosition) {
//        for(int x =0;x < rateNowContainer.getChildCount();x++){
//            ImageView starBtn = (ImageView)rateNowContainer.getChildAt(x);
//            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
//            if (x <= starPosition){
//                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
//            }
//        }
//    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        // ye h top right side ke 3 items ke liye, unme se koi ek bhi selecct hua to uska code yaha h......
//
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//            // todo search
//            finish();
//            return true;
//        }else if (id == R.id.search) {
//            //todo: search
//            return true;
//        } else if (id == R.id.cart){
//            //todo cart:
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
    }
}

