package com.example.signin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Adminlogin extends Fragment implements View.OnClickListener {
    EditText email;
    EditText pass;
    TextView forgetpass;
    Button login;
    ProgressDialog progressBar;
    DatabaseReference LoginDetails;
    float v=0;

    //private FirebaseAuth mAuth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root =(ViewGroup) inflater.inflate(R.layout.admin_login,container,false);

        email = root.findViewById(R.id.email);
        pass=root.findViewById(R.id.Password);
        forgetpass=root.findViewById(R.id.forgotpassword);
        login=root.findViewById(R.id.login);
        //mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(this);

        progressBar=new ProgressDialog(getActivity());
        progressBar.setMessage("please wait...");

        email.setTranslationY(800);
        pass.setTranslationY(800);
        forgetpass.setTranslationY(800);
        login.setTranslationY(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        forgetpass.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetpass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();


        return root;
    }

    @Override
    public void onClick(View view) {

        userLogin();
    }
    public void userLogin() {
        String userDetails = email.getText().toString().trim();
        String userpass =pass.getText().toString().trim();

        LoginDetails = FirebaseDatabase.getInstance().getReference("admin");

        Query checkUser = LoginDetails.orderByChild("userName").equalTo(userDetails);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    email.setError(null);

                    String PasswordFromDb = snapshot.child(userDetails).child("userPassword").getValue(String.class);
                    System.out.println(snapshot);
                    System.out.println(PasswordFromDb);


                    if(userpass.equals(PasswordFromDb))
                    {
                        pass.setError(null);
                        Toast.makeText(getActivity(),"admin login success",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(),storedetails.class);
                        startActivity(intent);
                    }
                    else
                    {
                        pass.setError("Wrong Password");
                        pass.requestFocus();
                    }
                }
                else
                {
                    email.setError("No User Found !");
                    email.requestFocus();
                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
//    private void login() {
//        String useremail=email.getText().toString().trim();
//        String password = pass.getText().toString().trim();
//
//        if (useremail.isEmpty()){
//            email.setError(" user email is required");
//            email.requestFocus();
//
//            return;
//        }
//        if (!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){
//            email.setError("please enter valid email");
//            email.requestFocus();
//            return;
//        }
//        if (password.isEmpty()){
//            pass.setError(" user password is required");
//            pass.requestFocus();
//            return;
//        }
//        if(password.length()<6){
//            pass.setError("Min password length is 6 character");
//            pass.requestFocus();
//            return;
//        }
//
//        progressBar.show();
//        mAuth.signInWithEmailAndPassword(useremail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//
//                    Intent i=new Intent(getActivity(),homepage.class);
//                    startActivity(i);
//                    progressBar.dismiss();
//                }else {
//                    progressBar.dismiss();
//                    Toast.makeText(getActivity(), "Failed to login", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }
}