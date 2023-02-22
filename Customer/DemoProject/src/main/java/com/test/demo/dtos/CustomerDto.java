package com.test.demo.dtos;

public class CustomerDto {

	private long customerId;
	private long customerCategory;
	private String customerName;
	private String mobileNumber;
	private String address;
	private String gender;
	private String status;

	public CustomerDto(long customerId, long customerCategory, String customerName, String mobileNumber, String address,
			String gender, String status) {
		super();
		this.customerId = customerId;
		this.customerCategory = customerCategory;
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.gender = gender;
		this.status = status;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getCustomerCategory() {
		return customerCategory;
	}

	public void setCustomerCategory(long customerCategory) {
		this.customerCategory = customerCategory;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerDto [customerId=" + customerId + ", customerCategory=" + customerCategory + ", customerName="
				+ customerName + ", mobileNumber=" + mobileNumber + ", address=" + address + ", gender=" + gender
				+ ", status=" + status + "]";
	}

}
