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
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FormFillup extends AppCompatActivity {


    /*
     * References for inserting data in database
     */

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextInputEditText idStrudentName, idFatherName, idMotherName, idStudentAadharNumber, idFatherMobileNumber, idPermanentAddress, idParentsAnnualIncome, idtenthMeritRank, idEmailId, idStudentWhatsappNumber, idBirthDate;
    AutoCompleteTextView idCitizen, idGender, idCategoryOfAdmission, idReligion;
    Button idSubmit;
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
                String myFormat = "yyyy-MM-dd"; //put your date format in which you need to display
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


        // Initializing objects of all input fields
        idSubmit = findViewById(R.id.idsubmit);
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

        // Initializing objects of firebase database
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Students");

        // Set button's onClick event
        idSubmit.setOnClickListener(new View.OnClickListener() {
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
                insertDataInMySQL();
                Toast.makeText(FormFillup.this, "Student form submitted", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void insertDataInFirebase() {
        submitDateTime = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-S").format(Calendar.getInstance().getTime());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child(submitDateTime).setValue(student);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FormFillup.this, "ERROR: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertDataInMySQL() {
        try {
            String queryString;
            queryString = "submit=submit";
            queryString += "&student_name=" + URLEncoder.encode(studentName, "UTF-8");
            queryString += "&father_name=" + URLEncoder.encode(fatherName, "UTF-8");
            queryString += "&mother_name=" + URLEncoder.encode(motherName, "UTF-8");
            queryString += "&student_aadhar_number=" + URLEncoder.encode(studentAadharNumber, "UTF-8");
            queryString += "&father_mobile_number=" + URLEncoder.encode(fatherMobileNumber, "UTF-8");
            queryString += "&permanent_address=" + URLEncoder.encode(permanentAddress, "UTF-8");
            queryString += "&citizen=" + URLEncoder.encode(citizen, "UTF-8");
            queryString += "&gender=" + URLEncoder.encode(String.valueOf(gender.charAt(0)), "UTF-8");
            queryString += "&category_of_admission=" + URLEncoder.encode(categoryOfAdmission, "UTF-8");
            queryString += "&parents_annual_income=" + URLEncoder.encode(parentsAanualIncome.toString(), "UTF-8");
            queryString += "&tenth_merit_rank=" + URLEncoder.encode(tenthMeritRank.toString(), "UTF-8");
            queryString += "&email_id=" + URLEncoder.encode(emailId, "UTF-8");
            queryString += "&student_whatsapp_number=" + URLEncoder.encode(whatsappNumber, "UTF-8");
            queryString += "&birth_date=" + URLEncoder.encode(birthDate, "UTF-8");
            queryString += "&religion=" + URLEncoder.encode(religion, "UTF-8");
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://aadajp.000webhostapp.com/java/insert.php?" + queryString)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request call, IOException e) {
                    e.getMessage();
                }

                @Override
                public void onResponse(final Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Error Message: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}