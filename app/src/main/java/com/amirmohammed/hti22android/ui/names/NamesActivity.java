package com.amirmohammed.hti22android.ui.names;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.amirmohammed.hti22android.R;

import java.util.ArrayList;

// Data : ArrayList
// CustomItem -> layout
// Adapter -> bind data on ui
// NamesActivity -> java code , ui
public class NamesActivity extends AppCompatActivity {

    ArrayList<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);

        names.add("AMIR");
        names.add("ALI");
        names.add("MOHAMED");
        names.add("AHMED");
        names.add("SAYED");
        names.add("MAHMOUD");
        names.add("AMIR");
        names.add("ALI");
        names.add("MOHAMED");
        names.add("AHMED");
        names.add("SAYED");
        names.add("MAHMOUD");

        RecyclerView recyclerView = findViewById(R.id.rvNames);

        NamesAdapter namesAdapter = new NamesAdapter(names);

        recyclerView.setAdapter(namesAdapter);

    }
}