package com.jswiente.phd.prototype.domain;

public class Customer {
	
	private long customerNo;
	private String name;
	private String surname;
	private Address address;
	
	public long getCustomerNo() {
		return customerNo;
	}
	
	public void setCustomerNo(long customerNo) {
		this.customerNo = customerNo;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
}
