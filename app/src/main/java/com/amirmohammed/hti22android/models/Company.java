package com.amirmohammed.hti22android.models;

public class Company {

    private String companyName;

    private String companyPhone;


    public Company(String companyName, String companyPhone) {
        this.companyName = companyName;
        this.companyPhone = companyPhone;
    }

    public Company() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }
}
