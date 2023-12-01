package com.example.signin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class signuptabFragment extends Fragment implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private DatabaseReference exist;

    EditText useremail;
    EditText userpass;
    EditText username;
    EditText usernumber,city,address,pincode,cpass;
    Button Signup;
    ProgressDialog progressBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_fab_fragment, container, false);


        useremail = root.findViewById(R.id.email);
        cpass=root.findViewById(R.id.confirmPassword);
        userpass=root.findViewById(R.id.Password);
        username=root.findViewById(R.id.name);
        usernumber=root.findViewById(R.id.number);
        Signup=root.findViewById(R.id.button);
        address=root.findViewById(R.id.address);
        pincode=root.findViewById(R.id.pincode);
        city=root.findViewById(R.id.city);

        mAuth = FirebaseAuth.getInstance();
        Signup.setOnClickListener(this);

        progressBar=new ProgressDialog(getActivity());
        progressBar.setMessage("please wait...");


        return root;
    }


    @Override
    public void onClick(View view) {

        registerUser();
    }


    private void registerUser() {
        String  email =useremail.getText().toString().trim();
        String passw=userpass.getText().toString().trim();
        String confirmpass=cpass.getText().toString().trim();
        String name =username.getText().toString().trim();
        String number=usernumber.getText().toString().trim();
        String Pincode=pincode.getText().toString().trim();
        String Address=address.getText().toString().trim();
        String City=city.getText().toString().trim();
        if (name.isEmpty()){
            username.setError(" user name is required");
            username.requestFocus();
            return;
        }
        if (email.isEmpty()){
            useremail.setError(" user email is required");
            useremail.requestFocus();

            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            useremail.setError("please enter valid email");
            useremail.requestFocus();
            return;
        }

        if (passw.isEmpty()){
            userpass.setError(" user password is required");
            userpass.requestFocus();
            return;
        }
        if(passw.length()<6){
            userpass.setError("Min password length is 6 character");
            userpass.requestFocus();
            return;
        }
        if (!passw.equals(confirmpass)){
            userpass.setError("please enter same password");
            cpass.setError("please enter same password");
            userpass.requestFocus();
            cpass.requestFocus();
            return;
        }


        if (number.isEmpty()){
            usernumber.setError(" user number is required");
            usernumber.requestFocus();
            return;
        }
        if (Pincode.isEmpty()){
           pincode.setError(" pincode is required");
            pincode.requestFocus();
            return;
        }
        if (City.isEmpty()){
            city.setError(" city is required");
            city.requestFocus();
            return;
        }
        if (Address.isEmpty()){
            address.setError(" user name is required");
            address.requestFocus();
            return;
        }
        progressBar.show();
        mAuth.createUserWithEmailAndPassword(email,passw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(getActivity(), "User with this email already exist.", Toast.LENGTH_SHORT).show();
                            progressBar.dismiss();
                        }else if(task.isSuccessful()) {
                            users users = new users(name,email,number,City,Address,Pincode);

//                            Query checkUserName = exist.child("userName").equalTo(email);
//                            exist =FirebaseDatabase.getInstance("Users").equals("email")
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "Sucessfully registered", Toast.LENGTH_LONG).show();
                                        progressBar.dismiss();
                                        //

                                    } else  {
                                        Toast.makeText(getActivity(), "please enter valid email and password or  try again!", Toast.LENGTH_LONG).show();
                                        progressBar.dismiss();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(getActivity(),"Failed",Toast.LENGTH_LONG).show();
                            progressBar.dismiss();
                        }
                    }
                });



    }
}