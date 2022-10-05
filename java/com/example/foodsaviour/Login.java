package com.example.foodsaviour;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button btn_submit, btn_createPage;
    EditText txt_email1;
    FirebaseAuth mAuth;
    TextInputEditText txt_pass1;
    Intent nextscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        btn_submit = findViewById(R.id.submit);
        btn_createPage = findViewById(R.id.createPage);

        txt_email1 = findViewById(R.id.txt_email);
        txt_pass1 = findViewById(R.id.txt_pass);

        btn_createPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });
    }

    public void loginUserAccount()
    {

        // show the visibility of progress bar to show loading

        // Take the value of two edit texts in Strings
        String email, password;
        email = txt_email1.getText().toString();
        password = txt_pass1.getText().toString();

        email = email.trim();

        // validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // signin existing user

        String finalEmail = email;
        String[] arr = finalEmail.split("@");
        String key = arr[0];
        String cleanKey = key.replace('.', ',');

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Login successful!!", Toast.LENGTH_SHORT).show();

                    // if sign-in is successful
                    // intent to home activity
                    //Intent intent = new Intent(Login.this, OrdersPage.class);
                    nextscreen = new Intent(Login.this, OrdersPage.class);
                    nextscreen.putExtra("account", cleanKey);
                    startActivity(nextscreen);
                    txt_email1.setText("");
                    txt_pass1.setText("");
                    txt_email1.requestFocus();
                    finish();
                }

                else {
                    // sign-in failed
                    Toast.makeText(getApplicationContext(), "Login failed!!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
