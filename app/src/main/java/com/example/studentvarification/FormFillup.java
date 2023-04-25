package com.example.studentvarification;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FormFillup extends AppCompatActivity {

    String[] spiner = {"Urban", "Rural"};
    String[] admission = {"Diploma", "BE", "Bcom", "BCA", "BTech"};
    String[] rel = {"Hindu", "Muslim", "Christi", "Judaism", "Sikhism", "Shuddhism"};
    String[] genders = {"Male", "Female", "other"};
    String item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_fillup);

        ArrayAdapter<String> genderadapterItems;

        AutoCompleteTextView gender_auto = findViewById(R.id.gender_auto_complete_txt);
        AutoCompleteTextView citizen_auto = findViewById(R.id.citizen_auto_complete_txt);
        AutoCompleteTextView admission_auto = findViewById(R.id.admission_auto_complete_txt);
        AutoCompleteTextView religion_auto = findViewById(R.id.rel_auto_complete_txt);

        EditText datePickerob = findViewById(R.id.dob);
        Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }

            private void updateDate() {
                String myFormat = "dd/MM/yy"; //put your date format in which you need to display
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

                datePickerob.setText(sdf.format(myCalendar.getTime()));
            }
        };
        datePickerob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FormFillup.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        genderadapterItems = new ArrayAdapter<String>(FormFillup.this, R.layout.list_item, genders);
        gender_auto.setAdapter(genderadapterItems);
        gender_auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //item contain dropdown list data
                item = parent.getItemAtPosition(position).toString();

            }
        });

        genderadapterItems = new ArrayAdapter<String>(FormFillup.this, R.layout.list_item, spiner);
        citizen_auto.setAdapter(genderadapterItems);
        citizen_auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //item contain dropdown list data
                item = parent.getItemAtPosition(position).toString();

            }
        });

        genderadapterItems = new ArrayAdapter<String>(FormFillup.this, R.layout.list_item, admission);
        admission_auto.setAdapter(genderadapterItems);
        admission_auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //item contain dropdown list data
                item = parent.getItemAtPosition(position).toString();

            }
        });

        genderadapterItems = new ArrayAdapter<String>(FormFillup.this, R.layout.list_item, rel);
        religion_auto.setAdapter(genderadapterItems);
        religion_auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //item contain dropdown list data
                item = parent.getItemAtPosition(position).toString();

            }
        });
    }
}