package com.example.signin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class payment extends AppCompatActivity implements PaymentResultListener {

    // variables for our
    // edit text and button.
    private TextView amountEdt;
    private Button payBtn;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    private Button cart_continue_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);



//        /////////fetch the user details ///////////////////
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();


        final TextView userTextView = (TextView) findViewById(R.id.textView);
        final TextView numberTextView = (TextView) findViewById(R.id.textView4);
        final TextView addressTextView = (TextView) findViewById(R.id.textView3);
        final TextView pinTextView = (TextView) findViewById(R.id.textView2);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users userProfile = snapshot.getValue(users.class);

                if (userProfile !=  null){
                    String name = userProfile.name;
                    String number = userProfile.number;
                    String address = userProfile.City;
                    String pin = userProfile.Pincode;

                    userTextView.setText(name);
                    numberTextView.setText(number);
                    addressTextView.setText(address);
                    pinTextView.setText(pin);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(payment.this, "Somethig wrong happened!", Toast.LENGTH_SHORT).show();

            }
        });


        ////////////////////////







        //////////







        //////////////

        // initializing all our variables.
        amountEdt = findViewById(R.id.idEdtAmount);

        Intent intent = getIntent();
        String s = intent.getStringExtra("key");
        amountEdt.setText(s);



        payBtn = findViewById(R.id.idBtnPay);

        // adding on click listener to our button.
       payBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               // on below line we are getting
               // amount that is entered by user.
               String samount = amountEdt.getText().toString();

               // rounding off the amount.
               int amount = Math.round(Float.parseFloat(samount) * 100);

               // initialize Razorpay account.
               Checkout checkout = new Checkout();

               // set your id as below
               checkout.setKeyID("rzp_test_HlKYQjNyZ4A4UD");

               // set image
//                checkout.setImage(R.drawable.gfgimage);

               // initialize json object
               JSONObject object = new JSONObject();
               try {
                   // to put name
                   object.put("name", "HandyArt");

                   // put description
                   object.put("description", "Test payment");

                   // to set theme color
                   object.put("theme.color", "");

                   // put the currency
                   object.put("currency", "INR");

                   // put amount
                   object.put("amount", amount);

                   // put mobile number
                   object.put("prefill.contact", "9511662875");

                   // put email
                   object.put("prefill.email", "sidhantmadkaikar@gmail.com");

                   // open razorpay to checkout activity
                   checkout.open(payment.this, object);
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       });
    }

    @Override
    public void onPaymentSuccess(String s) {
        // this method is called on payment success.
        Toast.makeText(this, "Payment is successful : " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        // on payment failed.
        Toast.makeText(this, "Payment Failed due to error : " + s, Toast.LENGTH_SHORT).show();
    }
}