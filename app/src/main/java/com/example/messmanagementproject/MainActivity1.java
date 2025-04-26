package com.example.messmanagementproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity1 extends AppCompatActivity {

    Button register_btn, login_btn;
    EditText username, password, lidentity;
    DBHelper DB;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main1);

        register_btn = findViewById(R.id.register_btn);
        login_btn = findViewById(R.id.login_btn); // ✅ initialize login button!
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        lidentity = findViewById(R.id.lidentity);
        DB = new DBHelper(this);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fnext = new Intent(MainActivity1.this, MainActivity2.class);
                startActivity(fnext);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String lid = lidentity.getText().toString().trim();

                if (user.isEmpty() || pass.isEmpty() || lid.isEmpty()) {
                    Toast.makeText(MainActivity1.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = DB.checkUsernamePassword(user, pass, lid);
                    if (check) {
                        Toast.makeText(MainActivity1.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        if (lid.equalsIgnoreCase("User")) {
                            Intent i22 = new Intent(MainActivity1.this, MainActivity3User.class);
                            startActivity(i22);
                        } else {
                            Intent i5 = new Intent(MainActivity1.this, MainActivity3Admin.class);
                            startActivity(i5);
                        }
                    } else {
                        Toast.makeText(MainActivity1.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
