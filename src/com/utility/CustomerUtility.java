package com.utility;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import com.exceptions.CustomerNotFoundException;
import com.exceptions.EmptyInputException;
import com.model.Address;
import com.model.Admin;
import com.model.Bill;
import com.model.Customer;

public class CustomerUtility {
	private static Integer customerIdCounter=0;		
	
	public static Integer getCustomerIdCounter() {
		return customerIdCounter;
	}

	public static void setCustomerIdCounter(Integer idCounter) {
		customerIdCounter = idCounter;
	}

	public static Customer logInAsACustomer (Integer accountId, String accountPassword, Map<Customer, List<Bill>> customersBillsMap) throws CustomerNotFoundException {
		Set<Customer> customerSet = customersBillsMap.keySet();
		
		for (Customer customer: customerSet) {			
			if (customer.getCustomerId().equals(accountId) && customer.getCustomerPassword().equals(accountPassword)) {
				return customer;
			} 
		}
		throw new CustomerNotFoundException();		// this exception will be caught and handled in the main method.
	
	}
	
	public static Customer createCustomer(String customerName, String customerPassword, Address address) {
		// ++customerIdCounter because accountId 0 is reserved for the admin.
		
		Customer customer = new Customer(++customerIdCounter, customerName, customerPassword, address);
		return customer;
	}


	public static void displayBillInSpecificMonth(Scanner keyboard, List<Bill> currentCustomerBiilList) {
		// TODO Auto-generated method stub
				
		String strMonthOfBills = "";
		YearMonth monthOfBills = YearMonth.now();
		
		boolean isValid = false;
		do {
			System.out.print("Please enter the month (YYYY-MM) of the bills you want to view: ");
			try {
				// keyboard.nextLines() allows empty string, so I need to check if the input is empty.
				strMonthOfBills = keyboard.nextLine();	// Discard the new line character
				if (strMonthOfBills.length()==0) {
					throw new EmptyInputException();
				}
				monthOfBills = YearMonth.parse(strMonthOfBills);
				isValid = true;

			} catch (EmptyInputException emptyEx) {
				System.out.println("The input cannot be empty! Please try again.");	
			} catch (DateTimeParseException ex) {
				System.out.println("The input cannot be parsed into a month. Please follow the format YYYY-MM.");
				isValid = false;
			}
		} while (!isValid);
		
		
		for (Bill monthlyBill: currentCustomerBiilList) {		
			// Check if the billing month matches the inquiry.
			if (monthlyBill.getYearOfBill() == monthOfBills.getYear() && monthlyBill.getMonthValueOfBill() == monthOfBills.getMonthValue()) {
				System.out.println(monthlyBill);
			}			
		}

	}
	
	public static void displayBillInAllMonths(List<Bill> currentCustomerBiilList) {
		// TODO Auto-generated method stub		
			System.out.println("Your bill of all months");	
			// Print all bills of the customer.
			for (Bill monthlyBill : currentCustomerBiilList) {
				System.out.println(monthlyBill);
			}
	}
	

}
