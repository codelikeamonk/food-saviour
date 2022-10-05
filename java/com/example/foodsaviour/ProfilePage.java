package com.example.foodsaviour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilePage extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference root;
    TextView name1, email1, num1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        Intent in = getIntent();
        String accountKey = in.getStringExtra("profile");

        /*if (accountKey == null)
            accountKey = "hello";*/

        name1 = findViewById(R.id.tv_name);
        email1 = findViewById(R.id.tv_email);
        num1 = findViewById(R.id.tv_num);

        db = FirebaseDatabase.getInstance("https://foodsaviour-a65ac-default-rtdb.asia-southeast1.firebasedatabase.app/");
        root = db.getReference().child("users");
        DatabaseReference root1 = root.child(accountKey);

        root1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String email = snapshot.child("email").getValue(String.class);
                String name = snapshot.child("name").getValue(String.class);
                String number = snapshot.child("number").getValue(String.class);

                name1.setText("Name: "+name);
                email1.setText("Email ID: "+email);
                num1.setText("Phone Number: "+number);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}