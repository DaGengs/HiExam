package cn.iruier.entity;

public class Student {
    private String stu_no;
    private String stu_idNumber;
    private String stu_name;
    private String stu_gender;
    private String stu_phone;
    private int class_no;
    private int status;
    private String class_name;

    public String getStu_no() {
        return stu_no;
    }

    public void setStu_no(String stu_no) {
        this.stu_no = stu_no;
    }

    public String getStu_idNumber() {
        return stu_idNumber;
    }

    public void setStu_idNumber(String stu_idNumber) {
        this.stu_idNumber = stu_idNumber;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_gender() {
        return stu_gender;
    }

    public void setStu_gender(String stu_gender) {
        this.stu_gender = stu_gender;
    }

    public String getStu_phone() {
        return stu_phone;
    }

    public void setStu_phone(String stu_phone) {
        this.stu_phone = stu_phone;
    }

    public int getClass_no() {
        return class_no;
    }

    public void setClass_no(int class_no) {
        this.class_no = class_no;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
}
