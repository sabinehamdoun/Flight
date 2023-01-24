package com.halaezzeddine.fly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //defining the navigation bar
        MaterialToolbar toolbar = findViewById(R.id.topAppbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        //navigation bar listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open the navigation drawer
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        //listener on clicking an item of the nav_bar
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                //highlight the selected item
                item.setChecked(true);

                //close the nav_bar after selecting an item
                drawerLayout.closeDrawer(GravityCompat.START);

                //showing a toast msg upon clicking any of the items and changing the fragment
                switch (id){
                    case R.id.nav_flights:
                        Toast.makeText(MainActivity.this, "My Flights", Toast.LENGTH_SHORT).show();
                        replaceFragment(new frag_1());break;

                    case R.id.bud1:
                        Toast.makeText(MainActivity.this, "250$", Toast.LENGTH_SHORT).show();
                        replaceFragment(new Fragment250Budget());break;
                        //Intent intent2 = new Intent(MainActivity.this, BudgetAct.class);
                        //startActivity(intent2);break;
                    case R.id.bud2:
                        Toast.makeText(MainActivity.this, "500$", Toast.LENGTH_SHORT).show();
                        replaceFragment(new Fragment500Budget());break;
                    case R.id.bud3:
                        Toast.makeText(MainActivity.this, "750$", Toast.LENGTH_SHORT).show();
                        replaceFragment(new Fragment750Budget());break;

                    default:
                        return true;
                }
                return true;
            }
        });




    }



    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }


}