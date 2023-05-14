package com.example.studentverification;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Student implements Parcelable {
    private String studentName, fatherName, motherName, studentAadahrNumber, fatherMobileNumber, permanentAddress, citizen, gender, categoryOfAdmission, emailId, studentWhatsappNumber, birthDate, religion;
    private Double parentsAnnualIncome;
    private Integer tenthMeritRank;

    public Student(String studentName, String fatherName, String motherName, String studentAadahrNumber, String fatherMobileNumber, String permanentAddress, String citizen, String gender, String categoryOfAdmission, String emailId, String studentWhatsappNumber, String birthDate, String religion, Double parentsAnnualIncome, Integer tenthMeritRank) {
        this.studentName = studentName;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.studentAadahrNumber = studentAadahrNumber;
        this.fatherMobileNumber = fatherMobileNumber;
        this.permanentAddress = permanentAddress;
        this.citizen = citizen;
        this.gender = gender;
        this.categoryOfAdmission = categoryOfAdmission;
        this.emailId = emailId;
        this.studentWhatsappNumber = studentWhatsappNumber;
        this.birthDate = birthDate;
        this.religion = religion;
        this.parentsAnnualIncome = parentsAnnualIncome;
        this.tenthMeritRank = tenthMeritRank;
    }

    protected Student(Parcel in) {
        studentName = in.readString();
        fatherName = in.readString();
        motherName = in.readString();
        studentAadahrNumber = in.readString();
        fatherMobileNumber = in.readString();
        permanentAddress = in.readString();
        citizen = in.readString();
        gender = in.readString();
        categoryOfAdmission = in.readString();
        emailId = in.readString();
        studentWhatsappNumber = in.readString();
        birthDate = in.readString();
        religion = in.readString();
        if (in.readByte() == 0) {
            parentsAnnualIncome = null;
        } else {
            parentsAnnualIncome = in.readDouble();
        }
        if (in.readByte() == 0) {
            tenthMeritRank = null;
        } else {
            tenthMeritRank = in.readInt();
        }
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getStudentAadahrNumber() {
        return studentAadahrNumber;
    }

    public void setStudentAadahrNumber(String studentAadahrNumber) {
        this.studentAadahrNumber = studentAadahrNumber;
    }

    public String getFatherMobileNumber() {
        return fatherMobileNumber;
    }

    public void setFatherMobileNumber(String fatherMobileNumber) {
        this.fatherMobileNumber = fatherMobileNumber;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getCitizen() {
        return citizen;
    }

    public void setCitizen(String citizen) {
        this.citizen = citizen;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategoryOfAdmission() {
        return categoryOfAdmission;
    }

    public void setCategoryOfAdmission(String categoryOfAdmission) {
        this.categoryOfAdmission = categoryOfAdmission;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getStudentWhatsappNumber() {
        return studentWhatsappNumber;
    }

    public void setStudentWhatsappNumber(String studentWhatsappNumber) {
        this.studentWhatsappNumber = studentWhatsappNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public Double getParentsAnnualIncome() {
        return parentsAnnualIncome;
    }

    public void setParentsAnnualIncome(Double parentsAnnualIncome) {
        this.parentsAnnualIncome = parentsAnnualIncome;
    }

    public Integer getTenthMeritRank() {
        return tenthMeritRank;
    }

    public void setTenthMeritRank(Integer tenthMeritRank) {
        this.tenthMeritRank = tenthMeritRank;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(studentName);
        parcel.writeString(fatherName);
        parcel.writeString(motherName);
        parcel.writeString(studentAadahrNumber);
        parcel.writeString(fatherMobileNumber);
        parcel.writeString(permanentAddress);
        parcel.writeString(citizen);
        parcel.writeString(gender);
        parcel.writeString(categoryOfAdmission);
        parcel.writeString(emailId);
        parcel.writeString(studentWhatsappNumber);
        parcel.writeString(birthDate);
        parcel.writeString(religion);
        if (parentsAnnualIncome == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(parentsAnnualIncome);
        }
        if (tenthMeritRank == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(tenthMeritRank);
        }
    }
}
