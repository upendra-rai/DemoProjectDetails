package com.test.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_customer_category")
public class CustomerCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerCategoryId;
	private String customerCategoryName;
	private String status;

	public CustomerCategory() {
		super();
	}

	public CustomerCategory(long customerCategoryId, String customerCategoryName, String status) {
		super();
		this.customerCategoryId = customerCategoryId;
		this.customerCategoryName = customerCategoryName;
		this.status = status;
	}

	public long getCustomerCategoryId() {
		return customerCategoryId;
	}

	public void setCustomerCategoryId(long customerCategoryId) {
		this.customerCategoryId = customerCategoryId;
	}

	public String getCustomerCategoryName() {
		return customerCategoryName;
	}

	public void setCustomerCategoryName(String customerCategoryName) {
		this.customerCategoryName = customerCategoryName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerCategory [customerCategoryId=" + customerCategoryId + ", customerCategoryName="
				+ customerCategoryName + ", status=" + status + "]";
	}

}
