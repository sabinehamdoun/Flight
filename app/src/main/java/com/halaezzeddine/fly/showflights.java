package com.halaezzeddine.fly;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class showflights extends AppCompatActivity {

    Button addFlight;  //upon "save" response in Create_Form, we will insert the flight to the db.
    ImageButton btnUpdate;
    FloatingActionButton fab;
    ActionBar actionBar;
    RecyclerView mRecyclerView;
    flyHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showflights);

        fab = findViewById(R.id.fab);

        actionBar = getSupportActionBar();
        actionBar.setTitle("All Information");

        mRecyclerView = findViewById(R.id.recyclerView);
        databaseHelper = new flyHelper(this);

        btnUpdate = findViewById(R.id.editbtn);

        showForm();

        //listeners
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(showflights.this, Create_Form.class);
                //startActivity(intent);

                Intent intent = new Intent(showflights.this, Create_Form.class);
                intent.putExtra("editMode", false);
                startActivity(intent);
            }
        });

    }

    private void showForm() {
        Adapter adapter = new Adapter(showflights.this, databaseHelper.getAllData(Constants.DEPARTURE));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        showForm();
    }
}