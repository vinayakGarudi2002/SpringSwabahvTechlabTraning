package com.techlab.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {
	
	@Column(name="customerId")
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int customerId;
	@Column(name="firstName")
	private String firstName;
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", emailId=" + emailId + ", mobileNumber=" + mobileNumber + "]";
	}
	@Column(name="lastName")
	private String lastName;
	@Column(name="dateOfBirth")
	private Date dateOfBirth;
	@Column(name="emailId")
	private String emailId;
	@Column(name="mobileNumber")
	private long mobileNumber;
	public Customer(int customerId, String firstName, String lastName, Date dateOfBirth, String emailId,
			long mobileNumber) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
	}
	public Customer() {
		super();
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	

}
