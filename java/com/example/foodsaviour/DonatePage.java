package com.example.foodsaviour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonatePage extends AppCompatActivity {

    Button btn_donatepage1;
    TextView food_question1, amt_question1;
    EditText et_food1, et_amt1, et_area1;
    RadioButton rbtn_schedule1, rbtn_packaging1;

    FirebaseDatabase db;
    DatabaseReference root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_page);

        food_question1 = findViewById(R.id.food_question);
        amt_question1 = findViewById(R.id.amt_question);

        et_food1 = findViewById(R.id.et_food);
        et_amt1 = findViewById(R.id.et_amt);
        et_area1 = findViewById(R.id.et_area);
        //rbtn_packaging1 = findViewById(R.id.rbtn_packaging);
        //rbtn_schedule1 = findViewById(R.id.rbtn_schedule);

        btn_donatepage1 = findViewById(R.id.btn_donatepage);

        btn_donatepage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseDatabase.getInstance("https://foodsaviour-a65ac-default-rtdb.asia-southeast1.firebasedatabase.app/");
                root = db.getReference().child("Donations");

                //root.setValue("Hello");

                String food1 = et_food1.getText().toString();
                String amt1 = et_amt1.getText().toString();
                String area1 = et_area1.getText().toString();
                //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                //DatabaseReference quizRef = rootRef.child("Donations");
                String key = root.push().getKey();

                DonationsHelperClass helperClass = new DonationsHelperClass(food1, amt1, area1);

                root.child(key).setValue(helperClass);

                food_question1.setText("");
                amt_question1.setText("");
                food_question1.requestFocus();
                Toast.makeText(DonatePage.this, "Your order will be picked up soon!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DonatePage.this, OrdersPage.class));
            }
        });

    }

}
