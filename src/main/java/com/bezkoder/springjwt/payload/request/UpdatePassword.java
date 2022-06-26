package com.bezkoder.springjwt.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdatePassword {

	@NotBlank
	@Size(min = 6, max = 40)
	private String oldPassword;

	@NotBlank
	@Size(min = 6, max = 40)
	private String newPassword;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}