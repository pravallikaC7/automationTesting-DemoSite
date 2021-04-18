package loginSection;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
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
