package com.jdbc.userLoginsignup;

import java.io.Serializable;

public class UserBean implements Serializable
{
	private String uName,uPass,Fname,lName,city,eMid;
	private long phno;
	public UserBean() {}
	public String getuName() {
		return uName;
	}
	public String getuPass() {
		return uPass;
	}
	public String getFname() {
		return Fname;
	}
	public String getlName() {
		return lName;
	}
	public String getCity() {
		return city;
	}
	public String geteMid() {
		return eMid;
	}
	public long getPhno() {
		return phno;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public void setuPass(String uPass) {
		this.uPass = uPass;
	}
	public void setFname(String fname) {
		Fname = fname;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void seteMid(String eMid) {
		this.eMid = eMid;
	}
	public void setPhno(long phno) {
		this.phno = phno;
	}
	
	

}
