package com.amirmohammed.hti22android.ui.auth.login.model;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("gender")
	private String gender;

	@SerializedName("phone")
	private String phone;

	@SerializedName("imageUrl")
	private String imageUrl;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public User() {
	}

	public User(String gender, String phone, String imageUrl, String email, String username) {
		this.gender = gender;
		this.phone = phone;
		this.imageUrl = imageUrl;
		this.email = email;
		this.username = username;
	}


	public User(String gender, String phone, String email, String username) {
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.username = username;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"gender = '" + gender + '\'' + 
			",phone = '" + phone + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}