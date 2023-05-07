package com.example.studentverification;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FormFillup extends AppCompatActivity {


    /*
    * References for inserting data in database
    */

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextInputEditText idStrudentName, idFatherName, idMotherName;
    Button idSubmit;
    String currentTime;

    /*
    * Variable for dropdowns and datepicker
    */
    String[] spiner = {"Urban", "Rural"};
    String[] admission = {"Diploma", "BE", "Bcom", "BCA", "BTech"};
    String[] rel = {"Hindu", "Muslim", "Christi", "Judaism", "Sikhism", "Shuddhism"};
    String[] genders = {"Male", "Female", "other"};
    String item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_fillup);

        /*
        *  Code to set idGender, idCitizen, idCategoryOfAdmission, idReligion dorpdowns,
        *  and idDateOfBirth datpicker.
        */
        ArrayAdapter<String> genderadapterItems;

        AutoCompleteTextView gender_auto = findViewById(R.id.idgender);
        AutoCompleteTextView citizen_auto = findViewById(R.id.idcitizen);
        AutoCompleteTextView admission_auto = findViewById(R.id.idcategoryofadmission);
        AutoCompleteTextView religion_auto = findViewById(R.id.idreligion);

        EditText datePickerob = findViewById(R.id.iddateofbirth);
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

        /*
        *  Code to insert data in database.
        */
        idSubmit = findViewById(R.id.idsubmit);
        idStrudentName = findViewById(R.id.idstudentname);
        idFatherName = findViewById(R.id.idfathername);
        idMotherName = findViewById(R.id.idmothername);
        currentTime = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:S").format(Calendar.getInstance().getTime());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Students");

        idSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(currentTime).setValue(new Student(idStrudentName.getText().toString(),idFatherName.getText().toString(),idMotherName.getText().toString()));
                        Toast.makeText(FormFillup.this, "Student data inserted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(FormFillup.this, "ERROR: " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}