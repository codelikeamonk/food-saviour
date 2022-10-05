package com.example.foodsaviour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText name1, number1, pw1, email1;
    Button btn_createAccount, btn_loginPage;
    FirebaseAuth mAuth;
    Intent nextscreen;

    FirebaseDatabase db;
    DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name1 = findViewById(R.id.name);
        number1 = findViewById(R.id.number);
        email1 = findViewById(R.id.email);
        pw1 = findViewById(R.id.pw);

        btn_createAccount = findViewById(R.id.createAccount);
        btn_loginPage = findViewById(R.id.loginPage);

        btn_loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    } //end of onCreate

    public void signUp(View view) {
        String nm = name1.getText().toString();
        String num = number1.getText().toString();
        String email = email1.getText().toString();
        String password = pw1.getText().toString();
        //nm = nm.trim();
        email = email.trim();
        password = password.trim();

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

        mAuth = FirebaseAuth.getInstance();
        String finalEmail = email;
        String finalPassword = password;
        String[] arr = finalEmail.split("@");
        String key = arr[0];
        String cleanKey = key.replace('.', ',');

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            db = FirebaseDatabase.getInstance("https://foodsaviour-a65ac-default-rtdb.asia-southeast1.firebasedatabase.app/");
                            root = db.getReference().child("users");
                            //String key1 = root.push().getKey();

                            SignUpHelperClass helperClass1 = new SignUpHelperClass(nm, num, finalEmail, finalPassword);

                            root.child(cleanKey).setValue(helperClass1);
                            Toast.makeText(getApplicationContext(), "Registration Completed", Toast.LENGTH_SHORT).show();
                            nextscreen = new Intent(SignUp.this, OrdersPage.class);
                            nextscreen.putExtra("account", cleanKey);
                            startActivity(nextscreen);
                            name1.setText("");
                            number1.setText("");
                            email1.setText("");
                            pw1.setText("");
                            name1.requestFocus();
                            finish();
                        }

                        else
                            Toast.makeText(getApplicationContext(), "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}




