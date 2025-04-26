package com.example.messmanagementproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4Admin extends AppCompatActivity {

    EditText editText;
    Button saveBtn, clr2, Done1;
    TextView countText;
    Mydatabasehelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_activity4_admin);

        // Initialize Views
        editText = findViewById(R.id.editTextInfo);

        saveBtn = findViewById(R.id.buttonSave);

        clr2 = findViewById(R.id.clr2);
        Done1 = findViewById(R.id.Done1);
        countText = findViewById(R.id.textViewCount);


        dbHelper = new Mydatabasehelper(this);

        // Save Button for editText
        saveBtn.setOnClickListener(v -> saveInfo(editText));

        // Save Button for editText1




        clr2.setOnClickListener(v -> {
            dbHelper.clearAllData();
            Toast.makeText(this, "Database cleared!", Toast.LENGTH_SHORT).show();
            updateCounts();
        });

        // Done Button → Go to MainActivity5
        Done1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity4Admin.this, MainActivity5.class);
            startActivity(intent);
        });

        // Load booking counts initially
        updateCounts();
    }

    // Helper method to save info from any EditText
    private void saveInfo(EditText inputField) {
        String info = inputField.getText().toString().trim();
        if (!info.isEmpty()) {
            dbHelper.addInfo(info);
            Toast.makeText(this, "Info saved!", Toast.LENGTH_SHORT).show();
            updateCounts(); // Refresh counts
        } else {
            Toast.makeText(this, "Please enter some info", Toast.LENGTH_SHORT).show();
        }
    }

    // Helper method to update both counters
    private void updateCounts() {
        int count = dbHelper.getBookingCount();
        countText.setText("Total Bookings: " + count);

    }
}
