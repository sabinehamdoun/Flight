package com.halaezzeddine.fly;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class Create_Form extends AppCompatActivity {

    private DatePickerDialog mDatePickerDialog;
    private DatePickerDialog mDatePickerDialog2;
    public Button dateButton;
    public Button dateButton2;
    public EditText source;
    public EditText dest;
    public Spinner travelClass;
    public Button save;
    private long id;

    private String from, to, dep, arr, tclass;
    private flyHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_form);

        //getting the data to save them
        source = findViewById(R.id.source);
        dest = findViewById(R.id.destination);
        travelClass = findViewById(R.id.spinner);
        save = findViewById(R.id.Sbutton);




        //2 Calendars
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton1);
        dateButton.setText(getTodaysDate());
        initDatePicker2();
        dateButton2 = findViewById(R.id.datePickerButton2);
        dateButton2.setText(getTodaysDate2());


        //initializing db object in main function
        dbHelper = new flyHelper(this);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //error msg if not all input was filled
                if(source.getText().toString().length()<=0 || dest.getText().toString().length()<=0){
                    Toast.makeText(Create_Form.this, "Enter All Data!", Toast.LENGTH_SHORT).show();
                }

                ///if input was filled correctly,save the form to db
                else{
                    Form form = new Form(source.getText().toString().trim(),
                            dest.getText().toString().trim(), dateButton.getText().toString().trim(),
                            dateButton2.getText().toString().trim(), travelClass.getSelectedItem().toString());

                    try{
                        //getData();
                        id=dbHelper.addForm(form);
                        Toast.makeText(Create_Form.this, "Flight Saved! ID= "+id, Toast.LENGTH_SHORT).show();

                        finish();
                        startActivity(new Intent(Create_Form.this, showflights.class));
                    }
                    catch (Exception ex){
                        Toast.makeText(Create_Form.this, "Operation Failed :(", Toast.LENGTH_SHORT).show();

                    }

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