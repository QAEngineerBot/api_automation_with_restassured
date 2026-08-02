package com.data.beans;

import com.opencsv.bean.CsvBindByName;
import com.poiji.annotation.ExcelCellName;

public class LoginRequestBean {

	@ExcelCellName("username")
	@CsvBindByName(column = "username")
	private String username;
	
	@ExcelCellName("password")
	@CsvBindByName(column = "password")
	private String password;

	public LoginRequestBean() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequestBean [username=" + username + ", password=" + password + "]";
	}

}
