package com.vokot.productionmanagement.model;

import java.io.Serializable;
import java.util.Base64;

public class Login implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

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
		this.password = Base64.getEncoder().encodeToString(password.getBytes());;
	}
}
