package com.example.messmanagementproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity3User extends AppCompatActivity {

    ArrayList<ControlModel> arrContact = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_activity3_user); // ✅ Use correct layout file here

        RecyclerView recyclerView = findViewById(R.id.recyclerAdmin); // ✅ Make sure this ID exists in activity_main3_user.xml
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        arrContact.add(new ControlModel(R.drawable.img, "RAJMATA MESS", "Address: 3296 Raymonde Trace, South Vaughnside, Pimpri"));
        arrContact.add(new ControlModel(R.drawable.img_1, "ANNAPURNA MESS", "Address: Jl. Gatot Soebroto No. 62, Timur, Pimpri"));
        arrContact.add(new ControlModel(R.drawable.img_2, "SIDDHESWAR", "Address: Am Lindenfeld 56b, Nord Leniaberg, Pimpri"));
        arrContact.add(new ControlModel(R.drawable.img_3, "ADITYA MESS", "Address: 35166 Denesik Crossing, Sterlingview, Pimpri"));
        arrContact.add(new ControlModel(R.drawable.img_4, "PANDA MESS", "Address: 358 Shivaji Maharaj Nagar, Pimpri"));
        arrContact.add(new ControlModel(R.drawable.img_5, "LAXMI MESS", "Address: Esc. 953 Pasaje Caridad, Vitoria, Pimpri"));
        arrContact.add(new ControlModel(R.drawable.img_6, "ADITI MESS", "Address: 3296 MaxWell Trace, Nehrunagar, Pimpri"));

        RecyclerAdapterUser adapter = new RecyclerAdapterUser(this, arrContact);
        recyclerView.setAdapter(adapter);
    }
}
