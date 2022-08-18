package com.sanjeet.ahagurureducation.model;

public class Student {

    private int stuRollNo;
    private String stuName;
    private String stuEmail;
    private String stuClass;

    public Student() {
    }

    public Student(int stuRollNo, String stuName, String stuEmail, String stuClass) {
        this.stuRollNo = stuRollNo;
        this.stuName = stuName;
        this.stuEmail = stuEmail;
        this.stuClass = stuClass;
    }

    public int getStuRollNo() {
        return stuRollNo;
    }

    public void setStuRollNo(int stuRollNo) {
        this.stuRollNo = stuRollNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }
}
