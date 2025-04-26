package com.example.messmanagementproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4User extends AppCompatActivity {
    LinearLayout container;
    Mydatabasehelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_activity4_user);

        container = findViewById(R.id.container);
        dbHelper = new Mydatabasehelper(this);

        loadUnbookedData();
    }

    private void loadUnbookedData() {
        Cursor cursor = dbHelper.getUnbookedInfo();
        container.removeAllViews();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String info = cursor.getString(1);

            // TextView for displaying the info
            TextView textView = new TextView(this);
            textView.setText(info);
            textView.setTextSize(18);
            textView.setTextColor(getResources().getColor(android.R.color.black));
            textView.setPadding(10, 10, 10, 10);

            // Button to book the item
            Button bookBtn = new Button(this);
            bookBtn.setText("Book");
            bookBtn.setBackgroundColor(Color.parseColor("#331212")); // Custom color
            bookBtn.setTextColor(getResources().getColor(android.R.color.white));

            // Layout to group TextView and Button
            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.setPadding(20, 20, 20, 20);
            itemLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
            itemLayout.setElevation(5); // Optional: Adds subtle shadow

            itemLayout.addView(textView);
            itemLayout.addView(bookBtn);

            // Add to the container
            container.addView(itemLayout);

            // Button click logic
            bookBtn.setOnClickListener(v -> {
                dbHelper.bookInfo(id); // Mark as booked in DB

                // Disable and update the button
                bookBtn.setEnabled(false);
                bookBtn.setText("Booked");
                bookBtn.setBackgroundColor(Color.GRAY);

                // Show toast
                Toast.makeText(MainActivity4User.this, "Booking Confirmed!", Toast.LENGTH_SHORT).show();
                Intent il=new Intent(MainActivity4User.this , MainActivity5.class);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        startActivity(il);
                        finish();
                    }
                }, 2000);


            });
        }

        cursor.close();
    }
}
