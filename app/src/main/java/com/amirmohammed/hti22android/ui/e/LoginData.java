package com.amirmohammed.hti22android.ui.e;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("image")
	private String image;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("credit")
	private int credit;

	@SerializedName("email")
	private String email;

	@SerializedName("points")
	private int points;

	@SerializedName("token")
	private String token;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
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

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCredit(int credit){
		this.credit = credit;
	}

	public int getCredit(){
		return credit;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setPoints(int points){
		this.points = points;
	}

	public int getPoints(){
		return points;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"image = '" + image + '\'' + 
			",phone = '" + phone + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",credit = '" + credit + '\'' + 
			",email = '" + email + '\'' + 
			",points = '" + points + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}