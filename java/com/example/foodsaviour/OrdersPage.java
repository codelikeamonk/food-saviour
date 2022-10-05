package com.example.foodsaviour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class OrdersPage extends AppCompatActivity {

    Button btn_active1, btn_finished1, btn_donate1, btn_receive1, btn_account1, btn_logout1;
    FirebaseAuth mAuth;
    Intent nextscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_page);

        Intent in = getIntent();
        String profileKey = in.getStringExtra("account");

        btn_active1 = findViewById(R.id.btn_active);
        btn_finished1 = findViewById(R.id.btn_finished);
        btn_donate1 = findViewById(R.id.btn_donate);
        btn_receive1 = findViewById(R.id.btn_receive);
        btn_account1 = findViewById(R.id.btn_account);
        btn_logout1 = findViewById(R.id.btn_logout);

        btn_active1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OrdersPage.this, "You have 1 active order!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_finished1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OrdersPage.this, "You have 10 finished orders!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_donate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (OrdersPage.this, DonatePage.class));
            }
        });

        btn_receive1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrdersPage.this, ReceivePage.class));
            }
        });

        btn_account1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(OrdersPage.this, "Account email ID: "+email_id1, Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(OrdersPage.this, ProfilePage.class));
                nextscreen = new Intent(OrdersPage.this, ProfilePage.class);
                nextscreen.putExtra("profile", profileKey);
                startActivity(nextscreen);
            }
        });

        btn_logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                startActivity(new Intent(OrdersPage.this, FirstPage.class));
                finish();
            }
        });

    }
}