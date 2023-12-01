
package com.example.signin;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class profile_display extends AppCompatActivity {


    private DatabaseReference reference;
    private String userID;
    private FirebaseUser user;
    private Object Menu;

    Button updateBtn,delete,changepass;

//    ImageView settings_profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_display);

//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//
//            getMenuInflater().inflate(R.menu.common_menu, menu);
//            return super.onCreateOptionsMenu(menu);
//        }
//
//
//        @Override
//        public boolean onOptionsItemSelected(@NonNull MenuItem MenuItem item);
//         {
//
//
//            int id = item.getItemId();
//            if (id == R.id.menu_update_profile){
//                Intent intent = new Intent (profile_display.this, profile_edit.class);
//                startActivity(intent);
//            }else if (id == R.id.menu_change_password);{
//                Intent intent = new Intent (profile_display.this, changepass.class);
//                startActivity(intent);
//                //   }else if (id == R.id.menu_delete);{
//                //           Intent intent = new Intent (profile_display.this, deleteacc.class);
//            }
//            return super.onOptionsItemSelected(item);
//        }



//        getSupportActionBar().setTitle("Home");

        ///////////////////////////Button
        updateBtn = findViewById(R.id.updateBtn);
        delete = findViewById(R.id.btndelete);
        changepass = findViewById(R.id.btnchange);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();


        final TextView userTextView = (TextView) findViewById(R.id.input_name);
        final TextView emailTextView = (TextView) findViewById(R.id.input_Email);
        final TextView numberTextView = (TextView)findViewById(R.id.input_Phone);
        final TextView addressTextView = (TextView) findViewById(R.id.input_address);
        final TextView cityTextView = (TextView) findViewById(R.id.input_City);
        final TextView pinTextView = (TextView) findViewById(R.id.input_Pincode);

//        final ImageView profile=(ImageView)findViewById(R.id.settings_profile_image);
//
//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(profile_display.this,uploadProfile.class);
//                startActivity(i);
//            }
//        });

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users userProfile = snapshot.getValue(users.class);

                if (userProfile != null) {
                    String name = userProfile.name;
                    String email = userProfile.email;
                    String number = userProfile.number;
                    String address = userProfile.Address;
                    String city = userProfile.City;
                    String pin = userProfile.Pincode;

                    userTextView.setText(name);
                    emailTextView.setText(email);
                    numberTextView.setText(number);
                    addressTextView.setText(address);
                    cityTextView.setText(city);
                    pinTextView.setText(pin);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profile_display.this, "Something wrong happened!", Toast.LENGTH_SHORT).show();

            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                String fullname = input_Fullname.getText().toString();
//                String email = input_Email.getText().toString();
//                String phone = input_Phone.getText().toString();
//                String address = input_address.getText().toString();
//                String city = input_City.getText().toString();
//                String pincode = input_Pincode.getText().toString();


                final HashMap<String,Object> cartMap = new HashMap<>();
                cartMap.put("name",userTextView.getText().toString());
//        cartMap.put("img_url",productImg);
                cartMap.put("Address",addressTextView.getText().toString());
                cartMap.put("City",cityTextView.getText().toString());
                cartMap.put("email",emailTextView.getText().toString());
                cartMap.put("number",numberTextView.getText().toString());
                cartMap.put("Pincode",pinTextView.getText().toString());

//        cartMap.put("quantity",)
                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {

                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(profile_display.this, "Updated Successfully", Toast.LENGTH_SHORT).show();

                        }


                    }
                });



            }
        });

        changepass.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              Intent intent = new Intent(profile_display.this, Forgotpass.class);
                                              startActivity(intent);
                                          }
                                      });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FirebaseDatabase
//                        .getInstance()
//                        .getReference()
//                        .child("Users")
//                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue()
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                FirebaseAuth.getInstance().getCurrentUser().delete()
//                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<Void> task) {
//                                                if (task.isSuccessful()){
//                                                    FirebaseAuth.getInstance().getCurrentUser().delete();
//                                                    Toast.makeText(profile_display.this,"Account Deleted",Toast.LENGTH_SHORT).show();
////                                                    startActivity(new Intent(profile_display.this,MainActivity.class));
//                                                    FirebaseAuth.getInstance().signOut();
//                                                    Intent intent = new Intent(profile_display.this,MainActivity.class);
//                                                    startActivity(intent);
//                                                }
//                                                startActivity(new Intent(profile_display.this,MainActivity.class));
//                                            }
//                                        });
//                            }
//                        });
                Log.d(TAG, " delete Account");
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                final FirebaseUser currentUser = firebaseAuth.getCurrentUser();

                currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG,"Account deleted!");
                            Toast.makeText(profile_display.this,"Your account has been successfully deleted",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(profile_display.this, MainActivity.class));
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG,"failed", e);
                    }
                });
                           }
        });

    }


}