package com.halaezzeddine.fly;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class editForm extends AppCompatActivity {

    private DatePickerDialog mDatePickerDialog;
    private DatePickerDialog mDatePickerDialog2;
    public Button dateButton;
    public Button dateButton2;
    public EditText source;
    public EditText dest;
    public Spinner travelClass;
    public Button save;
    private long id;
    ActionBar actionBar;
    ImageButton editBtn;
    RecyclerView mRecyclerView;

    private String id1, from, to, dep, arr, tclass;
    private boolean editMode = false;
    private flyHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_form);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //getting the data to save them
        source = findViewById(R.id.source);
        dest = findViewById(R.id.destination);
        travelClass = findViewById(R.id.spinner);
        save = findViewById(R.id.Sbutton);
        mRecyclerView = findViewById(R.id.recyclerView);
        editBtn = mRecyclerView.findViewById(R.id.editbtn);
        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("editMode", editMode);
        id = Long.parseLong(intent.getStringExtra("ID"));
        from = intent.getStringExtra("SOURCE");
        to = intent.getStringExtra("DESTINATION");
        dep = intent.getStringExtra("DEPARTURE");
        arr = intent.getStringExtra("ARRIVAL");
        tclass = intent.getStringExtra("TRAVELCLASS");

        //2 Calendars
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton1);
        dateButton.setText(getTodaysDate());
        initDatePicker2();
        dateButton2 = findViewById(R.id.datePickerButton2);
        dateButton2.setText(getTodaysDate2());

        if(editMode){
            actionBar.setTitle("Update Information");

            editMode = intent.getBooleanExtra("editMode", editMode);
            id = Long.parseLong(intent.getStringExtra("ID"));
            from = intent.getStringExtra("SOURCE");
            to = intent.getStringExtra("DESTINATION");
            dep = intent.getStringExtra("DEPARTURE");
            arr = intent.getStringExtra("ARRIVAL");
            tclass = intent.getStringExtra("TRAVELCLASS");

            source.setText(from);
            dest.setText(to);
            dateButton.setText(dep);
            dateButton2.setText(arr);

            String compareValue = tclass;
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_values, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            travelClass.setAdapter(adapter);
            int spinnerPosition = adapter.getPosition(compareValue);
            travelClass.setSelection(spinnerPosition);

        }

        else{
            actionBar.setTitle("Add Information");

        }


        //initializing db object in main function
        dbHelper = new flyHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //error msg if not all input was filled
                if(source.getText().toString().length()<=0 || dest.getText().toString().length()<=0){
                    Toast.makeText(editForm.this, "Enter All Data!", Toast.LENGTH_SHORT).show();
                }

                ///if input was filled correctly,save the form to db
                else{
                    Form form = new Form(source.getText().toString().trim(),
                            dest.getText().toString().trim(), dateButton.getText().toString().trim(),
                            dateButton2.getText().toString().trim(), travelClass.getSelectedItem().toString());

                    if(editMode) {
                        try {
                            dbHelper.updateForm("" + id, form);
                        }
                        catch (Exception ex){
                            Toast.makeText(editForm.this, "Update Failed :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        try {
                            dbHelper.addForm(form);
                        }
                        catch (Exception ex){
                            Toast.makeText(editForm.this, "Save Failed :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                    startActivity(new Intent(editForm.this, showflights.class));
                    Toast.makeText(editForm.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();

                    }

                }

        });



    }


    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);


        mDatePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {

        return month+" "+day+" "+year;
    }

    public void openDatePicker(View view){
        mDatePickerDialog.show();
    }

    //////////////second calendar
    private String getTodaysDate2() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker2(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
                String date = makeDateString(day, month, year);
                dateButton2.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);


        mDatePickerDialog2 = new DatePickerDialog(this, dateSetListener, year, month, day);
    }

    private String makeDateString2(int day, int month, int year) {

        return month+" "+day+" "+year;
    }

    public void openDatePicker2(View view){
        mDatePickerDialog2.show();
    }



    public boolean onSupportNavigateUp(){
        //moves back to main activity when back button is pressed
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}