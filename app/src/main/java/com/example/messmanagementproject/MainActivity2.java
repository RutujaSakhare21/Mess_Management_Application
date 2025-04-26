package com.example.messmanagementproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    Button finalR_btn;
    DBHelper DB;
    EditText rusername, rpassword, cpassword, identity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        finalR_btn = findViewById(R.id.finalR_btn);
        rusername = findViewById(R.id.rusername);
        rpassword = findViewById(R.id.rpassword);
        cpassword = findViewById(R.id.cpassword);
        identity = findViewById(R.id.identity);
        DB = new DBHelper(this);

        finalR_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ruser = rusername.getText().toString().trim();
                String rpass = rpassword.getText().toString().trim();
                String cpass = cpassword.getText().toString().trim();
                String rid = identity.getText().toString().trim();

                if (ruser.isEmpty() || rpass.isEmpty() || cpass.isEmpty() || rid.isEmpty()) {
                    Toast.makeText(MainActivity2.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (rpass.equals(cpass)) {
                        Boolean checkuser = DB.checkUsername(ruser);
                        if (!checkuser) {
                            Boolean insert = DB.insertData(ruser, rpass, rid);
                            if (insert) {
                                Toast.makeText(MainActivity2.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent i1 = new Intent(MainActivity2.this, MainActivity1.class);
                                startActivity(i1);
                                finish(); // Optional: prevent going back to registration
                            } else {
                                Toast.makeText(MainActivity2.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity2.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity2.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
