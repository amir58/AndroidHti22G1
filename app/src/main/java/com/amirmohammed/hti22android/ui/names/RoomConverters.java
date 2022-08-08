package com.amirmohammed.hti22android.ui.names;

import androidx.room.TypeConverter;

import com.amirmohammed.hti22android.models.Company;
import com.google.gson.Gson;

public class RoomConverters {

    @TypeConverter
    public String companyToJson(Company company) {
        Gson gson = new Gson();
        return gson.toJson(company);
    }

    @TypeConverter
    public Company jsonToCompany(String json){
        Gson gson = new Gson();
        Company company = gson.fromJson(json, Company.class);
        return company;
    }

}
