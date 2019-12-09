package com.example.mechanicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmailAddress;
    private EditText editTextpassword;
    private Button LogIn;
    private TextView textViewForgotPassword;
    private TextView SignUp;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmailAddress = findViewById(R.id.etEmailAddress);
        editTextpassword = findViewById(R.id.etPassword);
        LogIn = findViewById(R.id.btnLogin);
        textViewForgotPassword = findViewById(R.id.tvForgotPassword);
        SignUp = findViewById(R.id.tvSignUp);

        progressDialog = new ProgressDialog(this);


        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()!= null){
            // Home Activity here
            finish();
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == LogIn) {
                    userLogIn();
                }

            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == SignUp) {
                    finish();
                    Pair[] pairs=new Pair[1];
                    pairs[0]=new Pair<View,String>(editTextEmailAddress,"etTransition");

                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);

                    startActivity(new Intent(MainActivity.this,RegisterActivity.class),options.toBundle());

                }

            }
        });
    }

    private void userLogIn() {

        String emailAddress = editTextEmailAddress.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if (TextUtils.isEmpty(emailAddress)) {
            //Email address is Empty
            Toast.makeText(this, "Please Enter your Email Address", Toast.LENGTH_SHORT).show();
            //stoping the function execution further
            return;

        }
        if (TextUtils.isEmpty(password)) {
            //Password Is empty
            Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        //if email and password are not Empty
        //Display a progress Dialog
        progressDialog.setMessage("Please Wait a moment....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(emailAddress,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            //start HomeActivity
                            finish();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        }

                    }
                });

    }
}