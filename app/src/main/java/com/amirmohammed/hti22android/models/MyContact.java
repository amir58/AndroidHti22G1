package com.amirmohammed.hti22android.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MyContact {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "company")
    private Company company;

    public MyContact(String name, String phone, Company company) {
        this.name = name;
        this.phone = phone;
        this.company = company;
    }

    public MyContact() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "MyContact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public void printContactData() {
        String output = "MyContact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
        System.out.println(output);
    }
}
