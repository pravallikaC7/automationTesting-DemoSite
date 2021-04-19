package loginSection;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
	
	/**
	 * create test data for testing the Login include set of username, password
	 * @return 2-D array of 4 set of Username and Password
	 */
	@DataProvider(name="LoginCredentialProvider")
    public static Object[][] LoginCredentialProvider(){
        return new Object[][] {
            { "mngr320209", "depehAb" },
            { "mngr320209", "wrongPassword" },
            { "wrongUsername", "depehAb" },
            { "wrongUsername", "wrongPassword"}
        };  
	}

}
