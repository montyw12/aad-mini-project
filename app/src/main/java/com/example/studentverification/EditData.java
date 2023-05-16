package com.example.studentverification;

import android.app.DatePickerDialog;
import android.content.Intent;
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

public class EditData extends AppCompatActivity {
    /*
     * References for inserting data in database
     */

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextInputEditText idStrudentName, idFatherName, idMotherName, idStudentAadharNumber, idFatherMobileNumber, idPermanentAddress, idParentsAnnualIncome, idtenthMeritRank, idEmailId, idStudentWhatsappNumber, idBirthDate;
    AutoCompleteTextView idCitizen, idGender, idCategoryOfAdmission, idReligion;
    Button idUpdate, idDelete;
    Student student;
    String submitDateTime, studentName, fatherName, motherName, studentAadharNumber, fatherMobileNumber, permanentAddress, emailId, whatsappNumber, birthDate, citizen, gender, categoryOfAdmission, religion;
    Double parentsAanualIncome;
    Integer tenthMeritRank;


    /*
     * Variable for dropdowns and datepicker
     */ String[] spiner = {"Urban", "Rural"};
    String[] admission = {"Diploma", "BE", "Bcom", "BCA", "BTech"};
    String[] rel = {"Hindu", "Muslim", "Christi", "Judaism", "Sikhism", "Shuddhism"};
    String[] genders = {"Male", "Female", "other"};
    String item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        // Initializing objects of all input fields
        idUpdate = findViewById(R.id.idupdate);
        idDelete = findViewById(R.id.iddelete);
        idStrudentName = findViewById(R.id.idstudentname);
        idFatherName = findViewById(R.id.idfathername);
        idMotherName = findViewById(R.id.idmothername);
        idStudentAadharNumber = findViewById(R.id.idstudentaadharnumber);
        idFatherMobileNumber = findViewById(R.id.idfathermobilenumber);
        idPermanentAddress = findViewById(R.id.idpermaneataddress);
        idParentsAnnualIncome = findViewById(R.id.idparentannualincome);
        idtenthMeritRank = findViewById(R.id.idtenthmeritrank);
        idEmailId = findViewById(R.id.idemail);
        idStudentWhatsappNumber = findViewById(R.id.idstudentwhatsappnumber);
        idBirthDate = findViewById(R.id.iddateofbirth);
        idCitizen = findViewById(R.id.idcitizen);
        idGender = findViewById(R.id.idgender);
        idCategoryOfAdmission = findViewById(R.id.idcategoryofadmission);
        idReligion = findViewById(R.id.idreligion);

        // Code to get Intent values and set in input fileds
        Intent i = getIntent();
        studentName = i.getStringExtra("student_name");
        fatherName = i.getStringExtra("father_name");
        motherName = i.getStringExtra("mother_name");
        studentAadharNumber = i.getStringExtra("student_aadhar_number");
        fatherMobileNumber = i.getStringExtra("father_mobile_number");
        permanentAddress = i.getStringExtra("permanent_address");
        citizen = i.getStringExtra("citizen");
        gender = i.getStringExtra("gender");
        categoryOfAdmission = i.getStringExtra("category_of_admission");
        parentsAanualIncome = i.getDoubleExtra("parents_annual_income", 654789);
        tenthMeritRank = i.getIntExtra("tenth_merit_rank", 987);
        emailId = i.getStringExtra("email_id");
        whatsappNumber = i.getStringExtra("student_whatsapp_number");
        birthDate = i.getStringExtra("birth_date");
        religion = i.getStringExtra("religion");
        submitDateTime = i.getStringExtra("sId");

        idStrudentName.setText(studentName);
        idFatherName.setText(fatherName);
        idMotherName.setText(motherName);
        idStudentAadharNumber.setText(studentAadharNumber);
        idFatherMobileNumber.setText(fatherMobileNumber);
        idPermanentAddress.setText(permanentAddress);
        idParentsAnnualIncome.setText(parentsAanualIncome.toString());
        idtenthMeritRank.setText(tenthMeritRank.toString());
        idEmailId.setText(emailId);
        idStudentWhatsappNumber.setText(whatsappNumber);
        idBirthDate.setText(birthDate);
        idCitizen.setText(citizen);
        idGender.setText(gender);
        idCategoryOfAdmission.setText(categoryOfAdmission);
        idReligion.setText(religion);

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
                String myFormat = "yyyy-MM-dd"; //put your date format in which you need to display
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

                datePickerob.setText(sdf.format(myCalendar.getTime()));
            }
        };
        datePickerob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditData.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        genderadapterItems = new ArrayAdapter<String>(EditData.this, R.layout.list_item, genders);
        gender_auto.setAdapter(genderadapterItems);
        gender_auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //item contain dropdown list data
                item = parent.getItemAtPosition(position).toString();

            }
        });

        genderadapterItems = new ArrayAdapter<String>(EditData.this, R.layout.list_item, spiner);
        citizen_auto.setAdapter(genderadapterItems);
        citizen_auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //item contain dropdown list data
                item = parent.getItemAtPosition(position).toString();

            }
        });

        genderadapterItems = new ArrayAdapter<String>(EditData.this, R.layout.list_item, admission);
        admission_auto.setAdapter(genderadapterItems);
        admission_auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //item contain dropdown list data
                item = parent.getItemAtPosition(position).toString();

            }
        });

        genderadapterItems = new ArrayAdapter<String>(EditData.this, R.layout.list_item, rel);
        religion_auto.setAdapter(genderadapterItems);
        religion_auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //item contain dropdown list data
                item = parent.getItemAtPosition(position).toString();

            }
        });

        /*
         *  Code to update data in database.
         */

        // Initializing objects of firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Students");

        // Set button's onClick event
        idUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Take inputted values from input fields
                studentName = idStrudentName.getText().toString();
                fatherName = idFatherName.getText().toString();
                motherName = idMotherName.getText().toString();
                studentAadharNumber = idStudentAadharNumber.getText().toString();
                fatherMobileNumber = idFatherMobileNumber.getText().toString();
                permanentAddress = idPermanentAddress.getText().toString();
                parentsAanualIncome = Double.parseDouble(idParentsAnnualIncome.getText().toString());
                tenthMeritRank = Integer.parseInt(idtenthMeritRank.getText().toString());
                emailId = idEmailId.getText().toString();
                whatsappNumber = idStudentWhatsappNumber.getText().toString();
                birthDate = idBirthDate.getText().toString();
                citizen = idCitizen.getText().toString();
                gender = idGender.getText().toString();
                categoryOfAdmission = idCategoryOfAdmission.getText().toString();
                religion = idReligion.getText().toString();

                // Initializing student object
                student = new Student(studentName, fatherName, motherName, studentAadharNumber, fatherMobileNumber, permanentAddress, citizen, gender, categoryOfAdmission, emailId, whatsappNumber, birthDate, religion, parentsAanualIncome, tenthMeritRank);

                // Call function that insert data in firebase and Mysql database
                insertDataInFirebase();
                Toast.makeText(EditData.this, "Student data updated", Toast.LENGTH_LONG).show();
            }
        });

        idDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteDataInFirebase();
                Toast.makeText(EditData.this, "Student data deleted", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void insertDataInFirebase() {
        //submitDateTime = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-S").format(Calendar.getInstance().getTime());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child(submitDateTime).setValue(student);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EditData.this, "ERROR: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteDataInFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child(submitDateTime).setValue(null);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EditData.this, "ERROR: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}