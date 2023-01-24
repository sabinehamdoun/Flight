package com.halaezzeddine.fly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class BudgetAct extends AppCompatActivity {


    //followed https://www.youtube.com/watch?v=jXSNobmB7u4 for all the dropdown list menu
    String[] budget = {"100$", "250$", "500$", "750$"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        adapterItem = new ArrayAdapter<String>(this, R.layout.list_layout, budget);

        autoCompleteTextView.setAdapter(adapterItem);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                String item = adapterView.getItemAtPosition(i).toString();
                switch (item) {
                    case "100$":
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragmentContainerView3, BudgetFragment.class, null)
                                .setReorderingAllowed(true).addToBackStack("100").commit();
                        break;
                    case "250$":
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragmentContainerView4, BudgetFragment.class, null)
                                .setReorderingAllowed(true).addToBackStack("250").commit();
                        break;
                    case "500$":
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragmentContainerView5, BudgetFragment.class, null)
                                .setReorderingAllowed(true).addToBackStack("500").commit();
                        break;
                    case "750$":
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragmentContainerView6, BudgetFragment.class, null)
                                .setReorderingAllowed(true).addToBackStack("750").commit();
                        break;
                }
            }
        });
    }
}