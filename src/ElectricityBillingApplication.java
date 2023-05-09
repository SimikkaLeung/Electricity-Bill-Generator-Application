
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


import com.model.Customer;
import com.model.Rates;
import com.utility.AdminUtility;
import com.utility.BillUtility;
import com.utility.CustomerUtility;
import com.exceptions.*;
import com.model.Admin;
import com.model.Bill;


// This entity class is only for reading and writing objects between the program and the file.
class CustomerBillEntity implements Serializable {
	private Customer customer;
	private List<Bill> billList;
	
	public CustomerBillEntity(Customer customer, List<Bill> billList) {
		this.customer = customer;
		this.billList = billList;		
	}
	public Customer getCustomer() {
		return customer;		
	}
	public List<Bill> getBillList() {
		return billList;		
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;	
	}
	public void setBillList(List<Bill> billList) {
		this.billList = billList;	
	}
}

public class ElectricityBillingApplication {
	public static void writeDataToFiles (Map<Customer, List<Bill>> customersBillsMap) throws IOException {
		// Serialize objects from a map to a file
		FileOutputStream fos = new FileOutputStream("CustomerInfo.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		for (Entry<Customer, List<Bill>> entry: customersBillsMap.entrySet()) {
			CustomerBillEntity entity = new CustomerBillEntity(entry.getKey(),entry.getValue());
			oos.writeObject(entity);		
		}
		oos.close();
		fos.close();

		// Update the existing admin info
		BufferedWriter bw = new BufferedWriter(new FileWriter("AdminInfo.txt"));
		bw.write(Admin.getAdminId().toString());
		bw.newLine();
		bw.write(Admin.getAdminName());
		bw.newLine();
		bw.write(Admin.getAdminPassword());
		bw.close();
		
		// Update the existing rate info
		bw = new BufferedWriter(new FileWriter("RateInfo.txt"));
		bw.write(Rates.getRateTier1().toString());
		bw.newLine();
		bw.write(Rates.getRateTier2().toString());
		bw.newLine();
		bw.write(Rates.getRateTier3().toString());
		bw.newLine();
		bw.write(Rates.getThresholdTier1().toString());
		bw.newLine();
		bw.write(Rates.getThresholdTier2().toString());		
		bw.close();
		
		// Update the existing bill info
		bw = new BufferedWriter(new FileWriter("CounterInfo.txt"));
		bw.write(BillUtility.getBillIdCounter().toString());
		bw.newLine();
		bw.write(CustomerUtility.getCustomerIdCounter().toString());
		bw.close();
		
	}
	
	public static void readDataFromFiles(Map<Customer, List<Bill>> customersBillsMap) throws IOException, ClassNotFoundException {
		// File I/O
		boolean fileNotFound = false;
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("CustomerInfo.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			fileNotFound = true;
		}
		//Deserialize objects from a file to a map
		
		if (!fileNotFound) {

			
			try {
				ObjectInputStream ois = new ObjectInputStream(fis);

			
			while (true) {
				try {
					CustomerBillEntity entity = (CustomerBillEntity) ois.readObject();
					customersBillsMap.put(entity.getCustomer(), entity.getBillList());
				} catch (EOFException ex) {
					break;
				}
			}
				ois.close();
				fis.close();
			} catch (EOFException ex) {
			}
		}
		
		// Retrieve the existing admin info
		
		FileReader fr = null;
		BufferedReader br;
		fileNotFound = false;
		try {
			fr = new FileReader("AdminInfo.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			fileNotFound = true;
		}
		
		if (!fileNotFound) {
			br = new BufferedReader(fr);
			Admin.setAdminId(Integer.parseInt(br.readLine()));
			Admin.setAdminName(br.readLine());
			Admin.setAdminPassword(br.readLine());
			br.close();
		}
	
		
		// Retrieve the existing rate info
		fileNotFound = false;
		try {
			fr = new FileReader("RateInfo.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			fileNotFound = true;
		}
		if (!fileNotFound) {
			br = new BufferedReader(fr);
			Rates.setRateTier1(Double.parseDouble(br.readLine()));
			Rates.setRateTier2(Double.parseDouble(br.readLine()));
			Rates.setRateTier3(Double.parseDouble(br.readLine()));
			Rates.setThresholdTier1(Integer.parseInt(br.readLine()));
			Rates.setThresholdTier2(Integer.parseInt(br.readLine()));
			br.close();
		}
	
		// Retrieve the existing bill info
		fileNotFound = false;
		try {
			fr = new FileReader("CounterInfo.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			fileNotFound = true;
		}
		if (!fileNotFound) {
			br = new BufferedReader(fr);
			BillUtility.setBillIdCounter(Integer.parseInt(br.readLine()));
			CustomerUtility.setCustomerIdCounter(Integer.parseInt(br.readLine()));
			br.close();
		}
		
		//System.out.println("Bill id: "+ BillUtility.getBillIdCounter());
		
	}
	
	public static String displayNameOnMenu (int width, String accountName) {
		String strDisplayName = "Hello, " + accountName+"!";
		String strDisplayNameWithSpace = "";
		for (int i = 1; i <= (width-strDisplayName.length())/2; i++) {
			strDisplayNameWithSpace += " ";
		}
		strDisplayNameWithSpace += strDisplayName;
		for (int j = 1; j <= width - ((width-strDisplayName.length())/2 + strDisplayName.length()); j++) {
			strDisplayNameWithSpace += " ";
		}
		return strDisplayNameWithSpace;
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

		
		// I choose the TreeMap data type because we want a sorted collection and each customer only has one corresponding list of bills.
		Map<Customer, List<Bill>> customersBillsMap = new TreeMap<Customer, List<Bill>> ((c1, c2) -> {return c1.getCustomerId().compareTo(c2.getCustomerId());});


		readDataFromFiles(customersBillsMap);
		
		
		
		Scanner keyboard = new Scanner(System.in);
		
		
		System.out.println("========== Welcome to the Electricity Billing Centre ==========");
		System.out.println("==========         Please log in to proceed.         ==========");
		System.out.println();
		
		boolean isValid = false;
		boolean isAdmin = false;
		boolean isCustomer = false;
		boolean toLogOut = false;
		String strUserId = "", strPassword = "";
		Customer currentCustomer = null;
		List<Bill> currentCustomerBiilList = null;
		Integer userId = -1;
		String strDisplayNameWithSpace = "";
		
		//Set<Entry<Customer, List<Bill>>> customersBillsEntrySet;
		
		
		do {
			System.out.print("Enter your user/customer id: ");
			try {
				strUserId = keyboard.nextLine();
				
				if (strUserId.length()==0) {
					throw new EmptyInputException();
				}
				userId = Integer.parseInt(strUserId);
			} catch (EmptyInputException emptyEx) {
				System.out.println("The input cannot be empty! Please try again.");	
			} catch (NumberFormatException ex) {
				System.out.println("Invalid Input: The id must be an integer.");
			}
			System.out.print("Enter your password: ");
				strPassword = keyboard.nextLine();
				try {
					if (strPassword.length()==0) {
						throw new EmptyInputException();
					}
				} catch (EmptyInputException emptyEx) {
					System.out.println("The input cannot be empty! Please try again.");	
				}
				

				if ( AdminUtility.logInAsAnAdmin(userId, strPassword) ) {
					isValid = true;
					isAdmin = true;
					strDisplayNameWithSpace = displayNameOnMenu (59,Admin.getAdminName());
				} else {
					try { currentCustomer = CustomerUtility.logInAsACustomer(userId, strPassword, customersBillsMap);
					isValid = true;
					isCustomer = true;
					currentCustomerBiilList = customersBillsMap.get(currentCustomer);
					strDisplayNameWithSpace = displayNameOnMenu (43,currentCustomer.getCustomerName());
					} catch (CustomerNotFoundException ex) {
						System.out.println("Account Not Found! Please try again.");
					}
				}
		} while (!isValid);
		
		// Display a menu
		while (isAdmin && !toLogOut) {
			/*1.	Admin login (use static username/password combination) 
				Admin should able to perform : 
				a.	Add Customer
				b.	Display all customers
				c.	Generate the bill 
				d.	Display the bill of customers in specific month
				e.	Display the all bills of the customers
				f.	Configure the rate 
				g.	logout

			 * 
			 */
			
			
			
			System.out.println("===============================================================================");
			System.out.println("===============================================================================");
			System.out.println("==========" + strDisplayNameWithSpace + "==========");
			System.out.println("==========                       Admin Menu                          ==========");
			System.out.println("==========  1.	Add a new customer                                   ==========");
			System.out.println("==========  2.	Display all customers                                ==========");
			System.out.println("==========  3.	Generate the bill                                    ==========");
			System.out.println("==========  4.	Display the bill of all customers in specific month  ==========");
			System.out.println("==========  5.	Display all bills of all customers                   ==========");
			System.out.println("==========  6.	Configure the rate                                   ==========");
			System.out.println("==========  7.	Logout                                               ==========");
			System.out.println("===============================================================================");
			System.out.print("Please enter a number (1 - 7) to perform an action: ");
			
			isValid = false;
			String strOption = "";
			int option = 0;
			do {
				try { 
					strOption = keyboard.next();		// Force the user to input at least one character
					keyboard.nextLine();	// Discard the new line character
					option = Integer.parseInt(strOption);
					if ( option<1 || option > 7) {
						throw new InputOutOfRangeException();
					}
					isValid = true;
				} catch (NumberFormatException ex) {
					System.out.println("The response is invalid. Only 1-7 is accepted.");
				} catch (InputOutOfRangeException ex) {
					System.out.println("The response is invalid. Only 1-7 is accepted.");
				}
			} while (!isValid);
			
			
			switch (option) {
			case 1: customersBillsMap = AdminUtility.addCustomer(keyboard,customersBillsMap);
					System.out.println();
					break;
			case 2: AdminUtility.displayAllCustomers(customersBillsMap);
					System.out.println();
					break;
			case 3: if (customersBillsMap.size() > 0) {
						AdminUtility.generateBill(keyboard, AdminUtility.selectCustomer(keyboard, customersBillsMap), customersBillsMap);
					} else {
						System.out.println("There is no customer in the system to generate a bill.");
					}
					System.out.println();
					break;
			case 4: AdminUtility.displayBillInSpecificMonth(keyboard, customersBillsMap);
					System.out.println();
					break;			
			case 5: AdminUtility.displayBillInAllMonths(customersBillsMap);
					System.out.println();
					break;
			case 6: AdminUtility.configureRate (keyboard);
					System.out.println();
					break;
			case 7: toLogOut = true;
					System.out.println("You are logged out. Goodbye!");
					System.out.println();
					break;
					
			}
		}
		
		
		
		while (isCustomer && !toLogOut) {
			System.out.println("===============================================================");
			System.out.println("==========" + strDisplayNameWithSpace + "==========");
			System.out.println("==========               Customer Menu               ==========");
			System.out.println("==========  1.	Display your bill in specific month  ==========");
			System.out.println("==========  2.	Display all of your bills            ==========");
			System.out.println("==========  3.	Logout                               ==========");
			System.out.println("===============================================================");
			System.out.print("Please enter a number (1 - 3) to perform an action: ");
			
			
			isValid = false;
			String strOption = "";
			int option = 0;
			do {
				try { 
					strOption = keyboard.next();		// Force the user to input at least one character
					keyboard.nextLine();	// Discard the new line character
					option = Integer.parseInt(strOption);
					if ( option<1 || option > 3) {
						throw new InputOutOfRangeException();
					}
					isValid = true;
				} catch (NumberFormatException ex) {
					System.out.println("The response is invalid. Only 1-3 is accepted.");
				} catch (InputOutOfRangeException ex) {
					System.out.println("The response is invalid. Only 1-3 is accepted.");
				}
			} while (!isValid);
			
			
			switch (option) {

			case 1: CustomerUtility.displayBillInSpecificMonth(keyboard, currentCustomerBiilList);
					break;			
			case 2: CustomerUtility.displayBillInAllMonths(currentCustomerBiilList);
					break;
			case 3: toLogOut = true;
					System.out.println("You are logged out. Goodbye!");
					break;
			}			
		}
		
		keyboard.close();
		
		writeDataToFiles(customersBillsMap);
	}

	
	
}

