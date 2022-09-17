package org.coding.model;

import java.util.ArrayList;

public class MemberVo {
	

	private String id;
	

	private String password;
	

	private String name;
	
	
	private String email;
	

	private String profile;
	

	// UploadFileVO (파일 업로드 관련 model)
	private ArrayList<UploadFileVO> upfilevo2;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public ArrayList<UploadFileVO> getUpfilevo2() {
		return upfilevo2;
	}

	public void setUpfilevo2(ArrayList<UploadFileVO> upfilevo2) {
		this.upfilevo2 = upfilevo2;
	}

	@Override
	public String toString() {
		return "MemberVo [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", profile="
				+ profile + ", upfilevo2=" + upfilevo2 + "]";
	}

	
}

