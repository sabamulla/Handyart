package com.example.signin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Adminsignup extends Fragment implements View.OnClickListener {
   // private FirebaseAuth mAuth;
   DatabaseReference LoginDetails;
    EditText useremail;
    EditText userpass;
    EditText username;
    EditText usernumber;
    EditText confirmpass;
    Button Signup;
    ProgressDialog progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.admin_signup, container, false);

        useremail = root.findViewById(R.id.email);
        userpass=root.findViewById(R.id.Password);
        confirmpass=root.findViewById(R.id.confirmPassword);
        username=root.findViewById(R.id.name);
        usernumber=root.findViewById(R.id.number);
        Signup=root.findViewById(R.id.button);

        LoginDetails = FirebaseDatabase.getInstance().getReference().child("admin");
      //  mAuth = FirebaseAuth.getInstance();
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
        String userName = username.getText().toString();
        String userEmail = useremail.getText().toString();
        String userPassword = userpass.getText().toString();
        String confirmPass = confirmpass.getText().toString();
        String number=usernumber.getText().toString();

        Query checkUserName = LoginDetails.orderByChild("userName").equalTo(userName);
        checkUserName.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                username.setError(null);
                if(snapshot.exists())
                {
                   username.setError("User Name Already Exist");
                }
                else
                {
                    if (confirmPass.equals(userPassword))
                    {
                       confirmpass.setError(null);
                        useremail.setError(null);
                        logInDetails logInDetails = new logInDetails(userName,userEmail,userPassword,number);
                        LoginDetails.child(userName).setValue(logInDetails);
                        Toast.makeText(getActivity(),"Registered successfully",Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        confirmpass.setError("Password Does not match");
                    }

                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

//    private void registerUser() {
//        String  email =useremail.getText().toString().trim();
//        String passw=userpass.getText().toString().trim();
//        String confirm=confirmpass.getText().toString().trim();
//        String name =username.getText().toString().trim();
//        String number=usernumber.getText().toString().trim();
//        if (name.isEmpty()){
//            username.setError(" user name is required");
//            username.requestFocus();
//            return;
//        }
//        if (email.isEmpty()){
//            useremail.setError(" user email is required");
//            useremail.requestFocus();
//
//            return;
//        }
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            useremail.setError("please enter valid email");
//            useremail.requestFocus();
//            return;
//        }
//
//        if (passw.isEmpty()){
//            userpass.setError(" user password is required");
//            userpass.requestFocus();
//            return;
//        }
//        if(passw.length()<6){
//            userpass.setError("Min password length is 6 character");
//            userpass.requestFocus();
//            return;
//        }
//
//        if(!confirm.equals(passw)){
//            confirmpass.setError("please provide same password");
//            userpass.setError("please provide same password");
//            confirmpass.requestFocus();
//            userpass.requestFocus();
//            return;
//        }
//
//
//        if (number.isEmpty()){
//            usernumber.setError(" user number is required");
//            usernumber.requestFocus();
//            return;
//        }
//        progressBar.show();
//        mAuth.createUserWithEmailAndPassword(email,passw)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()) {
//                            admin admin= new admin(name,email,number);
//
//                            FirebaseDatabase.getInstance().getReference("admin")
//                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
//                                    .setValue(admin).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(getActivity(), "Sucessfully registered", Toast.LENGTH_LONG).show();
//                                        progressBar.dismiss();
//
//                                        //
//                                    } else {
//                                        Toast.makeText(getActivity(), "Failed to Register try again!", Toast.LENGTH_LONG).show();
//                                        progressBar.dismiss();
//                                    }
//                                }
//                            });
//                        }else{
//                            Toast.makeText(getActivity(),"Failed",Toast.LENGTH_LONG).show();
//                            progressBar.dismiss();
//                        }
//                    }
//                });
//
//
//
//
//    }

}