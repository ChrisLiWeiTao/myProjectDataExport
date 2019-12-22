package com.lwt.dataexport.entity;

import lombok.Data;

@Data
public class Student {

    private int dept;
    private String name;
    private char sex;
    private String address;
    private String phone;

    public Student() {
    }

    public Student(int dept, String name, char sex, String address, String phone) {
        this.dept = dept;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }
}
