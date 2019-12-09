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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    //define object views
    private EditText editTextFullNames;
    private EditText editTextEmailAddress;
    private EditText editTextPhoneNumber;
    private EditText editTextPassword;
    private EditText editTextConferPassword;

    private Button btnSignUp;

    private TextView textViewLogIn;
    private  TextView textViewForgotPassword;

    private ProgressDialog progressDialog;

    //define fire baseAuth object
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initializing Views
        editTextFullNames = findViewById(R.id.etFullNames);
        editTextEmailAddress = findViewById(R.id.etEmailAddress);
        editTextPhoneNumber =findViewById(R.id.etPhoneNumber);
        editTextPassword = findViewById(R.id.etPassword);
        editTextConferPassword =findViewById(R.id.etConfirmPassword);

        btnSignUp = findViewById(R.id.btnSignUp);

        textViewLogIn = findViewById(R.id.tvLogin);
        textViewForgotPassword = findViewById(R.id.tvRegForgotPassword);

        progressDialog = new ProgressDialog(this);

        //initializing Fire baseAuth
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!= null){
            // Home Activity here
            finish();
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == btnSignUp){
                    registerUser();
                }

            }
        });

        textViewLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == textViewLogIn){
                    //Open Log In Activity
                    Pair[] pairs=new Pair[1];
                    pairs[0]=new Pair<View,String>(editTextEmailAddress,"etTransition");

                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this,pairs);

                    startActivity(new Intent(RegisterActivity.this,MainActivity.class),options.toBundle());

                }
            }
        });


    }

    private void registerUser() {

        //getting user Inputs from the EditText
        String fullNames = editTextFullNames.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String emailAddress = editTextEmailAddress.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confermPassword = editTextConferPassword.getText().toString();

        //check if EditText are Empty
        if (TextUtils.isEmpty(fullNames)){
            //FullName is Empty
            Toast.makeText(this, "Please Enter Your Full Names", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        if (TextUtils.isEmpty(phoneNumber)){
            //Phone Number is Empty
            Toast.makeText(this, "Please Enter your Phone Number", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        if (TextUtils.isEmpty(emailAddress)){
            //Email address is Empty
            Toast.makeText(this, "Please Enter your Email Address", Toast.LENGTH_SHORT).show();
            //stoping the function execution further
            return;

        }
        if (TextUtils.isEmpty(password)){
            //Password Is empty
            Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        if (TextUtils.isEmpty(confermPassword)){
            //Conferm Password is Empty
            Toast.makeText(this, "Please Confirm Your Password", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        //if Validation are ok
        // we will show a Progressbar
        progressDialog.setMessage("Registering User....");
        progressDialog.show();
        
        firebaseAuth.createUserWithEmailAndPassword(emailAddress,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //user is successfully Registered
                            //we will start the Home Activity Here
                            finish();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                           // Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Registration Error..", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }
}
