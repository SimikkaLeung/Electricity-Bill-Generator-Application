package com.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Bill implements Serializable{
	private int billId, customerId, unitsConsumed;
	private double totalBill;
	private LocalDate dateOfBill;
	

	public Bill(int billId, int customerId, int unitsConsumed, LocalDate dateOfBill) {
		super();
		this.billId = billId;
		this.customerId = customerId;
		this.unitsConsumed = unitsConsumed;
		this.totalBill = Rates.calculateCost(unitsConsumed);
		this.dateOfBill = dateOfBill;
	}


	public int getBillId() {
		return billId;
	}


	public void setBillId(int billId) {
		this.billId = billId;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public int getUnitsConsumed() {
		return unitsConsumed;
	}


	public void setUnitsConsumed(int unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}


	public double getTotalBill() {
		totalBill = Rates.calculateCost(unitsConsumed);
		return totalBill;
	}

	public LocalDate getDateOfBill() {
		return dateOfBill;
	}

	public int getMonthValueOfBill() {
		return dateOfBill.getMonthValue();
	}

	public int getYearOfBill() {
		return dateOfBill.getYear();
	}

	public void setDateOfBill(LocalDate dateOfBill) {
		this.dateOfBill = dateOfBill;
	}

	
	

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", customerId=" + customerId + ", unitsConsumed=" + unitsConsumed
				+ ", totalBill=" + totalBill + ", dateOfBill=" + dateOfBill + "]";
	}
	
	
	
}
