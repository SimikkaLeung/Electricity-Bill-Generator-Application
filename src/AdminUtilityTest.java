

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Admin;
import com.model.Bill;
import com.model.Customer;
import com.utility.AdminUtility;


class AdminUtilityTest {
	Map<Customer, List<Bill>> customersBillsMap;
	@BeforeEach
	void setUp() throws ClassNotFoundException, IOException {
		customersBillsMap = new TreeMap<Customer, List<Bill>> ((c1, c2) -> {return c1.getCustomerId().compareTo(c2.getCustomerId());});
		ElectricityBillingApplication.readDataFromFiles(customersBillsMap);
	}
	
	@Test
	void testLogInAsAnAdmin() throws ClassNotFoundException, IOException {
		assertTrue(AdminUtility.logInAsAnAdmin(0, "12345678"));

	}
	
	

}
