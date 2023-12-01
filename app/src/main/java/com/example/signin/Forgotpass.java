package com.example.signin;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgotpass extends AppCompatActivity {

    private EditText emailEdittext;
    private Button resetpasswordbutton;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);

        emailEdittext = (EditText) findViewById(R.id.email);
        resetpasswordbutton= (Button) findViewById(R.id.button4);
//        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();
//        progressBar.setVisibility(View.GONE);


        resetpasswordbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpassword();
            }
        });

    }

    private void resetpassword(){
        String email = emailEdittext.getText().toString().trim();

        if (email.isEmpty()){
            emailEdittext.setError("Email is required!");
            emailEdittext.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdittext.setError("Please provide a valid Email");
            emailEdittext.requestFocus();
            return;
        }


//        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(Forgotpass.this, "Check your email to reset your password", Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
                }else {
                    Toast.makeText(Forgotpass.this, "Try again somethig wrong happened!", Toast.LENGTH_SHORT).show();
//                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
    }
