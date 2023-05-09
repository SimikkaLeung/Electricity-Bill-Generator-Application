package com.utility;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import com.model.Address;
import com.model.Admin;
import com.model.Bill;
import com.model.Customer;
import com.model.Rates;
import com.exceptions.*;
/*
 * Admin should able to perform : 
 * a.	Add Customer
 * b.	Display all customers
 * c.	Generate the bill 
 * d.	Display the bill of customers in specific month
 * e.	Display the all bills of the customers
 * f.	Configure the rate 
 */

public class AdminUtility {

	// Return true if the user is an admin
	public static boolean logInAsAnAdmin (Integer accountId, String accountPassword) {
		if (Admin.getAdminId() == accountId && Admin.getAdminPassword().equals(accountPassword)) {
			return true;
		} else
			return false;
	}
	
	public static Map<Customer, List<Bill>> addCustomer(Scanner keyboard, Map<Customer, List<Bill>> customersBillsMap) {
		System.out.println("Enter the following fields to create a new customer account.");
		String customerName = "";		// A String variable that holds the raw input.
		String customerNameCleaned = "";		// A String variable that holds the input after removing non alphabetical characters.
		do {
			System.out.print("Customer Name (Only alphabetical and space characters are accepted): ");
			try {
				customerName = keyboard.nextLine();
				//keyboard.nextLines() allows empty string, so I need to check if the input is empty.
				if (customerName.length()==0) {
					throw new EmptyInputException();
				}
				customerNameCleaned = customerName.replaceAll("[^A-Za-z]", " ");		// I only keep alphabets and space

				if (!customerName.equals(customerNameCleaned)) {		//  If true, it implies that the raw input has nothing other than alphabets and space.
					throw new NonAlphabeticInputException();
				}
			} catch (EmptyInputException emptyEx) {
				System.out.println("The input cannot be empty! Please try again.");	
			} catch (NonAlphabeticInputException ex) {
				System.out.println("The customer name is invalid. Only alphabetical and space characters are accepted.");
			}
		} while (!customerName.equals(customerNameCleaned));

		String customerPassword = "";

		do {
			System.out.print("Customer Password (8 characters minimum): ");
			try {
				customerPassword = keyboard.nextLine();
				//keyboard.nextLines() allows empty string, so I need to check if the input is empty.
				if (customerPassword.length()==0) {
					throw new EmptyInputException();
					
				} else if (customerPassword.length() < 8) {		// The password must have at leaset 8 characters.
					throw new StringTooShortException();
				}

			} catch (EmptyInputException emptyEx) {
				System.out.println("The input cannot be empty! Please try again.");	
			} catch (StringTooShortException ex) {
				System.out.println("The password is too short. It must contain at least 8 characters.");
			}

		} while (customerPassword.length() < 8);

		// String variables that hold raw input values as well as the versions after removing unwanted characters.
		String buildingName = "", buildingNameCleaned = "", strFlatNo = "", streetName = "", streetNameCleaned = "", city = "", cityCleaned = "";
		int flatNo = 0;

		System.out.println("Please provide the complete address.");
		do {
			System.out.print("Building Name (Only alphanumeric and space characters are accepted): ");

			try {
				buildingName = keyboard.nextLine();
				//keyboard.nextLines() allows empty string, so I need to check if the input is empty.
				if (buildingName.length()==0) {
					throw new EmptyInputException();
				}
				buildingNameCleaned = buildingName.replaceAll("[^A-Za-z0-9]", " ");		// I only keep alphabets, numbers and space.

				if (!buildingName.equals(buildingNameCleaned)) {	//  If true, it implies that the raw input has nothing other than alphabets, numbers and space.
					throw new NonAlphanumericInputException();
				}
			} catch (EmptyInputException emptyEx) {
				System.out.println("The input cannot be empty! Please try again.");	
			} catch (NonAlphanumericInputException ex) {
				System.out
						.println("The building name is invalid. Only alphanumeric and space characters are accepted.");
			}
		} while (!buildingName.equals(buildingNameCleaned));

		boolean isValid = false;	// a boolean variable that determines when to end the the while loop
		do {
			System.out.print("Flat Number (Only numbers are accepted. Enter 0 if it has no flat number.): ");
			
			try {
				strFlatNo = keyboard.nextLine();
				//keyboard.nextLines() allows empty string, so I need to check if the input is empty.
				if (strFlatNo.length()==0) {
					throw new EmptyInputException();
				}
				flatNo = Integer.parseInt(strFlatNo);
				// The input should not be a negative number.
				if (flatNo < 0) {
					throw new NegativeNumberException();
				}
				isValid = true;				
			} catch (EmptyInputException emptyEx) {
				System.out.println("The input cannot be empty! Please try again.");	
			} catch (NumberFormatException ex) {
				System.out.println("The flat number is invalid. Only numbers are accepted.");
			} catch (NegativeNumberException ex) {
				System.out.println("The flat number is invalid. Only positive numbers and zero are accepted.");
			}
		} while (!isValid);

		do {
			System.out.print("Street Name (Only alphanumeric and space characters are accepted): ");
			try {
				streetName = keyboard.nextLine();
				// keyboard.nextLines() allows empty string, so I need to check if the input is empty.
				if (streetName.length()==0) {
					throw new EmptyInputException();
				}				
				streetNameCleaned = streetName.replaceAll("[^A-Za-z0-9]", " ");		// I only keep alphabets, numbers and space
				if (!streetName.equals(streetNameCleaned)) {	//  If true, it implies that the raw input has nothing other than alphabets and space.
					throw new NonAlphanumericInputException();
				}
			} catch (EmptyInputException emptyEx) {
				System.out.println("The input cannot be empty! Please try again.");	
			} catch (NonAlphanumericInputException ex) {
				System.out.println("The street name is invalid. Only alphanumeric and space characters are accepted.");
			}
		} while (!streetName.equals(streetNameCleaned));

		do {
			System.out.print("City (Only alphabetical and space characters are accepted): ");
			try {
				city = keyboard.nextLine();
				// keyboard.nextLines() allows empty string, so I need to check if the input is empty.
				if (city.length()==0) {
					throw new EmptyInputException();
				}
				cityCleaned = city.replaceAll("[^A-Za-z]", " ");	// I only keep alphabets and space.
				if (!city.equals(cityCleaned)) {	//  If true, it implies that the raw input has nothing other than alphabets and space.
					throw new NonAlphabeticInputException();
				}
			} catch (EmptyInputException emptyEx) {
				System.out.println("The input cannot be empty! Please try again.");	
			} catch (NonAlphabeticInputException ex) {
				System.out.println("The city is invalid. Only alphabetical and space characters are accepted.");
			}
		} while (!city.equals(cityCleaned));

		// Create a new Customer object and put it in the tree map.
		Customer customer = CustomerUtility.createCustomer(customerName, customerPassword,
				new Address(buildingName, flatNo, streetName, city));
		List<Bill> billList = new ArrayList<Bill>();
		customersBillsMap.put(customer, billList);

		System.out.println("The customer account is created successfully. " + customer);

		return customersBillsMap;
	}

	public static void displayAllCustomers(Map<Customer, List<Bill>> customersBillsMap) {
		System.out.println("Printing all customers....");

		// Use a lambda expression instead of creating a new Comparator class
		// Sort by customer ids in an ascending order
		TreeSet<Customer> customerSet = new TreeSet<Customer>(
				(c1, c2) -> c1.getCustomerId().compareTo(c2.getCustomerId()));
		customerSet.addAll(customersBillsMap.keySet());

		// Display all customers
		for (Customer customer : customerSet) {
			System.out.println(customer);
		}

	}

	// A helper method that allows the admin to select a customer before generate a
	// bill or displaying the bill of this particular customer.
	public static Customer selectCustomer(Scanner keyboard, Map<Customer, List<Bill>> customersBillsMap) {

		boolean isFound = false;		// True when the program has found the target customer.
		String strTargetCustomerId;
		int targetCustomerId = 0;
		Customer targetCustomer = null;
		// We don't have to sort the set so we don't need to implement the TreeSet.
		// If we implement a TreeSet sorted by the customer id, it is possible to
		// get the customer object using an index, but since the admin may modify
		// the customer id or delete a customer account, it is not guaranteed that
		// the index of a customer in the TreeSet matches the customer id.
		//Set<Customer> customerSet = customersBillsMap.keySet();
		
		TreeSet<Customer> customerSet = new TreeSet<Customer>(
				(c1, c2) -> c1.getCustomerId().compareTo(c2.getCustomerId()));
		customerSet.addAll(customersBillsMap.keySet());
		
		do {
			try {
				System.out.println("Please enter the id of the customer to perform an action");
				strTargetCustomerId = keyboard.nextLine();
				// keyboard.nextLines() allows empty string, so I need to check if the input is empty.
				if (strTargetCustomerId.length()==0) {
					throw new EmptyInputException();
				}				
				targetCustomerId = Integer.parseInt(strTargetCustomerId);
				// Search for the customer id in the set.
				for (Customer customer : customerSet) {
					if (customer.getCustomerId() == targetCustomerId) {
						targetCustomer = customer;
						isFound = true;
						break; // Exit the for loop right away.
					}
				}

				if (!isFound) {
					throw new CustomerNotFoundException();
				}				
			} catch (EmptyInputException emptyEx) {
				System.out.println("The input cannot be empty! Please try again.");	
			} catch (CustomerNotFoundException customerEx) {
				System.out.println(
						"Customer Not Found! Please try another one.");
				isFound = false;
			} catch (NumberFormatException numberEx) {
				System.out.println("The customer id is invalid. Only numbers are accepted.");
				isFound = false;
			}
		} while (!isFound);

		return targetCustomer;

	}

	public static void generateBill(Scanner keyboard, Customer customer, Map<Customer, List<Bill>> customersBillsMap) {
		boolean isValid = false;
		LocalDate dateOfBill = LocalDate.now();
		String strDateOfBill = "";
		DateTimeFormatter format = DateTimeFormatter.ISO_DATE;
		do {
			System.out.print("Please enter the date (YYYY-MM-DD) of the bill: ");
			try {
				strDateOfBill = keyboard.nextLine();
				// keyboard.nextLines() allows empty string, so I need to check if the input is empty.
				if (strDateOfBill.length()==0) {
					throw new EmptyInputException();
				}
				dateOfBill = LocalDate.parse(strDateOfBill, format);
				isValid = true;
			} catch (EmptyInputException emptyEx) {
				System.out.println("The input cannot be empty! Please try again.");	
			} catch (DateTimeParseException ex) {
				System.out.println("The input cannot be parsed into a date. Please follow the format YYYY-MM-DD.");
				isValid = false;
			}
		} while (!isValid);

		isValid = false;

		String strUnitsConsumed = "";
		int unitsConsumed = 0;
		do {
			System.out.print("Please enter the unit of electricity consumed in this month: ");			
			try {
				strUnitsConsumed = keyboard.nextLine();
				// keyboard.nextLines() allows empty string, so I need to check if the input is empty.
				if (strUnitsConsumed.length()==0) {
					throw new EmptyInputException();
				}
				unitsConsumed = Integer.parseInt(strUnitsConsumed);
				isValid = true;
			} catch (EmptyInputException emptyEx) {
				System.out.println("The input cannot be empty! Please try again.");	
			} catch (NumberFormatException ex) {
				System.out.println("The input is invalid. Only positive numbers are accepted.");
				isValid = false;
			}
		} while (!isValid);

		customersBillsMap.get(customer).add(BillUtility.generateBill(customer, unitsConsumed, dateOfBill));	
	}

/*
	public static void displayBillInSpecificMonth(Map<Customer, List<Bill>> customersBillsMap, int yearValue, int monthValue) {
		// TODO Auto-generated method stub
		Set<Map.Entry<Customer, List<Bill>>> customersBillsEntrySet = customersBillsMap.entrySet();
		System.out.println("All bills of " + yearValue + "-" + monthValue + ": ");
		for (Entry<Customer, List<Bill>> entry : customersBillsEntrySet) {
			System.out.print(entry.getKey() + "\t\t");
			for (Bill monthlyBill : entry.getValue()) {
				
				if (monthlyBill.getYearOfBill() == yearValue && monthlyBill.getMonthValueOfBill() == monthValue) {
					System.out.println(monthlyBill);
				}
			}
		}
	}
*/
	
	public static void displayBillInSpecificMonth(Scanner keyboard, Map<Customer, List<Bill>> customersBillsMap) {
		// TODO Auto-generated method stub
		String strMonthOfBills = "";
		YearMonth monthOfBills = YearMonth.now();
		boolean isValid = false;
		do {
			System.out.print("Please enter the month (YYYY-MM) of the bills you want to view: ");
			
			try {
				// keyboard.nextLines() allows empty string, so I need to check if the input is empty.
				strMonthOfBills = keyboard.nextLine();	
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
		
		
		Set<Map.Entry<Customer, List<Bill>>> customersBillsEntrySet = customersBillsMap.entrySet();

		for (Entry<Customer, List<Bill>> entry : customersBillsEntrySet) {
			System.out.print(entry.getKey() + "\t\t");	// Print the customer info before the billing info
			for (Bill monthlyBill : entry.getValue()) {
				// Check if the billing month matches the inquiry
				if (monthlyBill.getYearOfBill() == monthOfBills.getYear() && monthlyBill.getMonthValueOfBill() == monthOfBills.getMonthValue()) {
					System.out.println(monthlyBill);
				}
			}
		}	// end of the outer for loop.
	}
	

	public static void displayBillInAllMonths(Map<Customer, List<Bill>> customersBillsMap) {
		// TODO Auto-generated method stub

		Set<Map.Entry<Customer, List<Bill>>> customersBillsEntrySet = customersBillsMap.entrySet();
		// Print all bills of all customers
		for (Entry<Customer, List<Bill>> entry : customersBillsEntrySet) {
			System.out.println(entry);
		}

	}

	public static void configureRate (Scanner keyboard) {
		System.out.println("Current " + Rates.getString());
		String yesOrNo = "";
		do {
			System.out.println("Do you want to update the rates? (Y/N): ");		
			try {
				// keyboard.nextLines() allows empty string, so I need to check if the input is empty.
				yesOrNo = keyboard.nextLine();
				if (yesOrNo.length()==0) {
					throw new EmptyInputException();
				}				
			} catch (EmptyInputException emptyEx) {
				System.out.println("The input cannot be empty! Please try again.");	
			}

			yesOrNo = yesOrNo.toUpperCase();
			if (!yesOrNo.equals("Y") && !yesOrNo.equals("N")) {
				System.out.println("Invalid response. Please enter Y or N only.");
			}			
		} while (!yesOrNo.equals("Y") & !yesOrNo.equals("N"));
		
		double rateTier1 = 0.0, rateTier2 = 0.0, rateTier3 = 0.0;
		int thresholdTier1 = 0, thresholdTier2 = 0;
		String strResponse = "";
		boolean isValid = false;
		
		if (yesOrNo.equals("Y")) {
			// Receive input for rateTier1
			do {
				System.out.println("Enter the tier 1 rate (must be greater than 0): ");
				try {
					// keyboard.nextLines() allows empty string, so I need to check if the input is empty.
					strResponse = keyboard.nextLine();
					if (strResponse.length()==0) {
						throw new EmptyInputException();
					}
					
					rateTier1 = Double.parseDouble(strResponse);
					if (rateTier1 <= 0.0) {
						throw new NegativeNumberException();
					} 
					isValid = true;
				} catch (EmptyInputException emptyEx) {
					System.out.println("The input cannot be empty! Please try again.");	
				} catch (NumberFormatException ex) {
					System.out.println("Invalid Input: The rate must be a number greater than 0.");
					isValid = false;
				} catch (NegativeNumberException ex) {
					System.out.println("Invalid Input: The rate must be greater than 0.");
					isValid = false;
				}		
			} while (!isValid);
			// Receive input for thresholdTier1
			isValid = false;
			do {
				System.out.println("Enter the number of units (must be greater than 0) for Tier 1: ");
				try {
					// keyboard.nextLines() allows empty string, so I need to check if the input is empty.
					strResponse = keyboard.nextLine();
					if (strResponse.length()==0) {
						throw new EmptyInputException();
					}
					thresholdTier1 = Integer.parseInt(strResponse);
					if (thresholdTier1 <= 0) {
						throw new NegativeNumberException();
					}
					isValid = true;
				} catch (EmptyInputException emptyEx) {
					System.out.println("The input cannot be empty! Please try again.");	
				} catch (NumberFormatException ex) {
					System.out.println("Invalid Input: The number of units must be an integer greater than 0.");
					isValid = false;
				} catch (NegativeNumberException ex) {
					System.out.println("Invalid Input: The number of units must be greater than 0.");
					isValid = false;
				}	
			} while (!isValid);
			// Receive input for rateTier2
			isValid = false;
			do {
				System.out.println("Enter the tier 2 rate (must be greater than 0): ");
				try {
					// keyboard.nextLines() allows empty string, so I need to check if the input is empty.
					strResponse = keyboard.nextLine();
					if (strResponse.length()==0) {
						throw new EmptyInputException();
					}
					rateTier2 = Double.parseDouble(strResponse);
					if (rateTier2 <= 0.0) {
						throw new NegativeNumberException();
					} 
					isValid = true;

				} catch (EmptyInputException emptyEx) {
					System.out.println("The input cannot be empty! Please try again.");	
				} catch (NumberFormatException ex) {
					System.out.println("Invalid Input: The rate must be a number greater than 0.");
					isValid = false;
				} catch (NegativeNumberException ex) {
					System.out.println("Invalid Input: The rate must be greater than 0.");
					isValid = false;
				}		
			} while (!isValid);
			// Receive input for thresholdTier2
			isValid = false;
			do {
				System.out.println("Enter the number of units (must be greater than 0) for Tier 2: ");
				try {
					strResponse = keyboard.nextLine();					
					// keyboard.nextLines() allows empty string, so I need to check if the input is empty.
					if (strResponse.length()==0) {
						throw new EmptyInputException();
					}
					thresholdTier2 = Integer.parseInt(strResponse);
					if (thresholdTier2 <= 0) {
						throw new NegativeNumberException();
					}					
					thresholdTier2 = thresholdTier2 + thresholdTier1;
					isValid = true;

				} catch (EmptyInputException emptyEx) {
					System.out.println("The input cannot be empty! Please try again.");	
				} catch (NumberFormatException ex) {
					System.out.println("Invalid Input: The number of units must be an integer greater than 0.");
					isValid = false;
				} catch (NegativeNumberException ex) {
					System.out.println("Invalid Input: The number of units must be greater than 0.");
					isValid = false;
				}	
			} while (!isValid);
			// Receive input for rateTier3
			isValid = false;
			do {
				System.out.println("Enter the tier 3 rate (must be greater than 0): ");
				try {
					strResponse = keyboard.nextLine();
					// keyboard.nextLines() allows empty string, so I need to check if the input is empty.
					if (strResponse.length()==0) {
						throw new EmptyInputException();
					}
					rateTier3 = Double.parseDouble(strResponse);
					if (rateTier3 <= 0.0) {
						throw new NegativeNumberException();
					} 
					isValid = true;

				} catch (EmptyInputException emptyEx) {
					System.out.println("The input cannot be empty! Please try again.");	
				} catch (NumberFormatException ex) {
					System.out.println("Invalid Input: The rate must be a number greater than 0.");
					isValid = false;
				} catch (NegativeNumberException ex) {
					System.out.println("Invalid Input: The rate must be greater than 0.");
					isValid = false;
				}		
			} while (!isValid);
			
			// Modify the Rates class variables.
			
			Rates.setRateTier1(rateTier1);
			Rates.setThresholdTier1(thresholdTier1);
			Rates.setRateTier2(rateTier2);
			Rates.setThresholdTier2(thresholdTier2);
			Rates.setRateTier3(rateTier3);
			
			System.out.println("The new rates are configured successfully.");
			System.out.println("Updated " + Rates.getString());
		}
		
	}

}
