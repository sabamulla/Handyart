package com.example.signin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class logintabFragment extends Fragment implements View.OnClickListener {
      EditText email;
      EditText pass;
      TextView forgetpass;
      Button login;
    ProgressDialog progressBar;
    float v=0;

    private FirebaseAuth mAuth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root =(ViewGroup) inflater.inflate(R.layout.login_fab_fragment,container,false);

        email = root.findViewById(R.id.email);
        pass=root.findViewById(R.id.Password);
        forgetpass=root.findViewById(R.id.forgotpassword);
        login=root.findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() !=null){
            Intent i=new Intent(getActivity(),homepage.class);
            startActivity(i);

        }

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
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),Forgotpass.class);
                startActivity(i);
            }
        });



        return root;
    }

    @Override
    public void onClick(View view) {

      login();
    }

    private void login() {
       String useremail=email.getText().toString().trim();
       String password = pass.getText().toString().trim();

        if (useremail.isEmpty()){
            email.setError(" user email is required");
            email.requestFocus();

            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){
           email.setError("please enter valid email");
            email.requestFocus();
            return;
        }
        if (password.isEmpty()){
            pass.setError(" user password is required");
            pass.requestFocus();
            return;
        }
        if(password.length()<6){
            pass.setError("Min password length is 6 character");
            pass.requestFocus();
            return;
        }

        progressBar.show();
        mAuth.signInWithEmailAndPassword(useremail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        Intent i=new Intent(getActivity(),homepage.class);
                        startActivity(i);
                        progressBar.dismiss();
                    }else{

                        user.sendEmailVerification();
                        Toast.makeText(getActivity(),"check your email to verify your account",Toast.LENGTH_LONG).show();
                        progressBar.dismiss();
                    }

                    Intent i=new Intent(getActivity(),homepage.class);
                    startActivity(i);
                    progressBar.dismiss();

                }else {
                    progressBar.dismiss();
                    Toast.makeText(getActivity(), "Failed to login please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            startActivity(new Intent(getActivity(),homepage.class));


        }
    }




}

