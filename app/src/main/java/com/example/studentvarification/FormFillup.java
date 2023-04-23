package com.example.studentvarification;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FormFillup extends AppCompatActivity {

    String[] spiner = {"choose", "Urban", "Rural"};
    String[] admission = {"choose", "Diploma", "BE", "Bcom", "BCA", "BTech"};
    String[] rel = {"choose", "Hindu", "Muslim", "Christi", "Judaism", "Sikhism", "Shuddhism"};
    String item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_fillup);

        // AutoCompleteTextView autoCompleteTextView;
        // ArrayAdapter<String> adapteritems;
        // autoCompleteTextView = findViewById(R.id.rel);
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
        // adapteritems =new ArrayAdapter<String>(this,R.layout.list_item,rel);
        // autoCompleteTextView.setAdapter(adapteritems);
        // autoCompleteTextView.setThreshold(2);
        // autoCompleteTextView.setAdapter(adapteritems);
    }
}