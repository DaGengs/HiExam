package cn.iruier.entity;

public class Teacher {
    private String tch_no;
    private String tch_idNumber;
    private String tch_name;
    private String tch_gender;
    private String tch_phone;
    private int class_no;
    private int status;
    private String class_name;

    public String getTch_no() {
        return tch_no;
    }

    public void setTch_no(String tch_no) {
        this.tch_no = tch_no;
    }

    public String getTch_idNumber() {
        return tch_idNumber;
    }

    public void setTch_idNumber(String tch_idNumber) {
        this.tch_idNumber = tch_idNumber;
    }

    public String getTch_name() {
        return tch_name;
    }

    public void setTch_name(String tch_name) {
        this.tch_name = tch_name;
    }

    public String getTch_gender() {
        return tch_gender;
    }

    public void setTch_gender(String tch_gender) {
        this.tch_gender = tch_gender;
    }

    public String getTch_phone() {
        return tch_phone;
    }

    public void setTch_phone(String tch_phone) {
        this.tch_phone = tch_phone;
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
