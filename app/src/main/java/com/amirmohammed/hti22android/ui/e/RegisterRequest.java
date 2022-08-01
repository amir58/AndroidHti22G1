package com.amirmohammed.hti22android.ui.e;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest{

	@SerializedName("email")
	private String email;

	@SerializedName("password")
	private String password;

	@SerializedName("name")
	private String name;

	@SerializedName("phone")
	private String phone;

	public RegisterRequest(String email, String password, String name, String phone) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"RegisterRequest{" + 
			"password = '" + password + '\'' + 
			",phone = '" + phone + '\'' + 
			",name = '" + name + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}