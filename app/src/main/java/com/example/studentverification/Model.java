package com.example.studentverification;

public class Model {

    private String studentName, fatherName, motherName, studentAadahrNumber, fatherMobileNumber, permanentAddress, citizen, gender, categoryOfAdmission, emailId, studentWhatsappNumber, birthDate, religion;
    private Double parentsAnnualIncome;
    private Integer tenthMeritRank;

    private String sId;

    /*public Model(String studentName, String fatherName, String motherName, String studentAadahrNumber, String fatherMobileNumber, String permanentAddress, String citizen, String gender, String categoryOfAdmission, String emailId, String studentWhatsappNumber, String birthDate, String religion, Double parentsAnnualIncome, Integer tenthMeritRank) {
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
    }*/

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

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }
}
