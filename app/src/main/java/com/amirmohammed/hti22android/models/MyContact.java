package com.amirmohammed.hti22android.models;

public class MyContact {

    private String name;
    private String phone;

    public MyContact(String name, String phone) {
        this.name = name;
        this.phone = phone;
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
