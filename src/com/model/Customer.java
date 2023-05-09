package com.model;

import java.io.Serializable;

public class Customer implements Serializable{
	private Integer customerId;
	private String customerName;
	private String customerPassword;
	private Address address;
	public Customer(Integer customerId, String customerName, String customerPassword, Address address) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.address = address;
	}
	public Customer(Integer customerId, String customerName, String customerPassword) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	public int  getCustomerIdInt() {
		return (int) customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerPassword="
				+ customerPassword + ", address=" + address + "]";
	}
	
	

}
