package com.example.signin.adapters;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.signin.models.EditorsChoiceModel;
import com.example.signin.payment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Poductdescription extends AppCompatActivity {

    //
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



//    //firebase
//    FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser user;
    //Editors choice
    EditorsChoiceModel editorsChoiceModel = null;
//    AllproductsModel allproductsModel =null;

//    private Toolbar toolbar;
//    private ViewPager productImagesViewPager;
//    private TabLayout viewpagerIndicator;

    ///// rating layout
    private LinearLayout rateNowContainer;
    ///// rating layout
    private static boolean ALREADY_ADDED_TO_WISHLIST = false;
    private FloatingActionButton addToWishlistButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdescription);

 //        toolbar=findViewById(R.id.mytoolbar);

  //     setSupportActionBar(toolbar);
 //    getSupportActionBar().setDisplayShowTitleEnabled(false);
 //     getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//       productImagesViewPager = findViewById(R.id.product_images_viewpager);
//        viewpagerIndicator = findViewById(R.id.viewpager_indicator);


        productID=findViewById(R.id.pid);
        cart=findViewById(R.id.cart_btn);
        addToWishlistButton = findViewById(R.id.add_to_wishlist_button);
        productname=findViewById(R.id.product_title);
        minirating=findViewById(R.id.product_rating_miniview);
        productprice=findViewById(R.id.product_price);
        productdescription=findViewById(R.id.product_details_body);
       // numrating=findViewById(R.id.avg_rating);
        productImg=findViewById(R.id.product_image);
        buy = findViewById(R.id.buy_now_btn);
        maxprice=findViewById(R.id.cutted_price);






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
















        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Poductdescription.this, payment.class);
                intent.putExtra("key",productprice.getText().toString());
                startActivity(intent);
            }
        });

        //firebase
        FirebaseDatabase.getInstance().getReference("EditorsChoice");
        final Object obj = getIntent().getSerializableExtra("detailed");
        if(obj instanceof EditorsChoiceModel){
            editorsChoiceModel = (EditorsChoiceModel) obj;

        }
//        FirebaseDatabase.getInstance().getReference("products");
//        final Object obj1 = getIntent().getSerializableExtra("detailed1");
//        if(obj1 instanceof AllproductsModel){
//            allproductsModel = (AllproductsModel) obj1;
//
//        }


       //cart
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
                 cartMap.put("img_url",editorsChoiceModel.getImg_url());
                cartMap.put("price",productprice.getText().toString());
                cartMap.put("id",productID.getText().toString());
                cartMap.put("maxprice",maxprice.getText().toString());
                cartMap.put("ratings",minirating.getText().toString());
                cartMap.put("description",editorsChoiceModel.getDescription());
                cartMap.put("currentDate",saveCurrentDate);
                cartMap.put("currentTime",saveCurrentTime);
//        cartMap.put("quantity",)
                FirebaseDatabase.getInstance().getReference("CartList")
                        .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("Products").child(productID.getText().toString()).setValue(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){
                                    Toast.makeText(productdescription.getContext(), " Item added to your cart!", Toast.LENGTH_SHORT).show();

                                }


                    }
                });

            }
        });


        //Editors choice

            if (editorsChoiceModel != null) {
                Glide.with(getApplicationContext()).load(editorsChoiceModel.getImg_url()).into(productImg);
                productID.setText(String.valueOf(editorsChoiceModel.getCid()));
                productname.setText(editorsChoiceModel.getName());
                minirating.setText(String.valueOf(editorsChoiceModel.getRatings()));
                productprice.setText(String.valueOf(editorsChoiceModel.getPrice()));
                productdescription.setText(editorsChoiceModel.getDescription());
              //  numrating.setText(String.valueOf(editorsChoiceModel.getRating()));

                String description = "";
                String[] descArray = String.valueOf(editorsChoiceModel.getDescription()).split("\\#");
                if (descArray.length > 1) {
                    for (String item: descArray) {
                        if (item != "#") {
                            String newString = "\n•  ".concat(item);
                            description = description + newString;
                        }
                    }
                } else {
                    description = "• " + String.valueOf(editorsChoiceModel.getDescription());
                }
                productdescription.setText(description);

            }

//            //allproducts
//        if (allproductsModel != null) {
//            Glide.with(getApplicationContext()).load(allproductsModel.getImg_url()).into(productImg);
//            productID.setText(String.valueOf(allproductsModel.getPid()));
//            productname.setText(allproductsModel.getName());
//            minirating.setText(String.valueOf(allproductsModel.getRating()));
//            productprice.setText(String.valueOf(allproductsModel.getPrice()));
//            productdescription.setText(allproductsModel.getDescription());
//            numrating.setText(String.valueOf(allproductsModel.getRating()));
//
//        }


        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.demo1);
        productImages.add(R.drawable.demo2);
        productImages.add(R.drawable.demo3);


//        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
       // productImagesViewPager.setAdapter(productImagesAdapter);


//        viewpagerIndicator.setupWithViewPager(productImagesViewPager,true);

        addToWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(ALREADY_ADDED_TO_WISHLIST) {
                  ALREADY_ADDED_TO_WISHLIST = false;

              addToWishlistButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9f9f9f")));
                  Toast.makeText(productdescription.getContext(), "Item added to your favourites!", Toast.LENGTH_SHORT).show();

              }else{
                  ALREADY_ADDED_TO_WISHLIST = true;
                 addToWishlistButton.setSupportImageTintList(getResources().getColorStateList(R.color.red));

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
                  cartMap.put("img_url",editorsChoiceModel.getImg_url());
                  cartMap.put("price",productprice.getText().toString());
                  cartMap.put("id",productID.getText().toString());
                  cartMap.put("maxprice",maxprice.getText().toString());
                  cartMap.put("ratings",minirating.getText().toString());
                  cartMap.put("description",editorsChoiceModel.getDescription());
                  cartMap.put("currentDate",saveCurrentDate);
                  cartMap.put("currentTime",saveCurrentTime);
//        cartMap.put("quantity",)
                  FirebaseDatabase.getInstance().getReference("Whishlist")
                          .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                          .child("favProducts").child(productID.getText().toString()).setValue(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {

                          if (task.isSuccessful()){
                              Toast.makeText(productdescription.getContext(), "Item added to your favourites!", Toast.LENGTH_SHORT).show();
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

        /////rating layout
    }
//
//    private void addtocart() {
//
//        String saveCurrentTime,saveCurrentDate;
//        Calendar calForDate =  Calendar.getInstance();
//        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
//        saveCurrentDate = currentDate.format(calForDate.getTime());
//
//        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
//        saveCurrentTime = currentTime.format(calForDate.getTime());
//
//        final HashMap<String,Object> cartMap = new HashMap<>();
//        cartMap.put("cid",EditorsChoiceModel.getCid());
//        cartMap.put("name",productname.getText().toString());
////        cartMap.put("img_url",productImg);
////        Glide.with(getApplicationContext()).load(editorsChoiceModel.getImg_url()).into( productImg);
//        cartMap.put("img_url",Glide.with(getApplicationContext()).load(editorsChoiceModel.getImg_url()));
//        cartMap.put("price",productprice.getText().toString());
//        cartMap.put("currentDate",saveCurrentDate);
//        cartMap.put("currentTime",saveCurrentTime);
////        cartMap.put("quantity",)
//
//        FirebaseDatabase.getInstance().getReference("CartList")
//        .child("UserView").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                .child("Products").child("cid")
//                .updateChildren(cartMap)
//                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()){
//                                                Toast.makeText(getApplicationContext(), "Added to Cart List", Toast.LENGTH_SHORT).show();
//
//                                            }
//                                        }
//                                    });
//
//
//                        }

    //stars
//    private void setRating(int starPosition) {
//        for(int x =0;x < rateNowContainer.getChildCount();x++){
//            ImageView starBtn = (ImageView)rateNowContainer.getChildAt(x);
//            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
//            if (x <= starPosition){
//                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
//            }
//        }
//    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // ye h top right side ke 3 items ke liye, unme se koi ek bhi selecct hua to uska code yaha h......

        int id = item.getItemId();
        if (id == android.R.id.home) {
            // todo search
            finish();
            return true;
        }else if (id == R.id.search) {
            //todo: search
            return true;
        } else if (id == R.id.cart){
            //todo cart:
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}