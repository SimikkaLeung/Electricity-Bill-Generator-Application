package com.model;
// 


public class Admin {
	// Assuming there is only one admin account
	private static Integer adminId = 0;
	private static String adminName = "Administrator";
	private static String adminPassword = "12345678";

	
	public Admin(Integer id, String name, String password) {
		super();
		adminId = id;
		adminName = name;
		adminPassword = password;
	}
	public static Integer getAdminId() {
		return adminId;
	}
	public static void setAdminId(Integer id) {
		adminId = id;
	}
	public static String getAdminName() {
		return adminName;
	}
	public static void setAdminName(String name) {
		adminName = name;
	}
	public static String getAdminPassword() {
		return adminPassword;
	}
	public static void setAdminPassword(String password) {
		adminPassword = password;
	}
	// cannot use a static method to override the toString() method in the Object class.
	public static String getString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminPassword=" + adminPassword + "]";
	}
	
	
	
}
