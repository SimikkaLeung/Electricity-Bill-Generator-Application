package com.utility;

import java.time.LocalDate;

import com.model.Bill;
import com.model.Customer;

public class BillUtility {
		private static Integer billIdCounter = 0;
		
		public static Bill generateBill (Customer customer, int unitsConsumed) {
			// ++billIdCounter because the first bill id should be 1.
			Bill bill = new Bill(++billIdCounter,customer.getCustomerId(),unitsConsumed,LocalDate.now());
			return bill;
		
		}
		public static Bill generateBill (Customer customer, int unitsConsumed, LocalDate dateOfBill) {
			Bill bill = new Bill(++billIdCounter,customer.getCustomerId(),unitsConsumed,dateOfBill);
			return bill;
		}
		
		public static Integer getBillIdCounter() {
			return billIdCounter;
		}
		
		public static void setBillIdCounter(Integer idCounter) {
			billIdCounter = idCounter;
		}
		
}
