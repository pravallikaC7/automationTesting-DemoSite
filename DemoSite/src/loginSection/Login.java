/*
 * Test script for testing Website Guru99 Bank
 * The test scripts is developed using Selenium Framework
 *
 */
package loginSection;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
//import java.io.IOException;


import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//import org.testng.annotations.DataProvider;


/*
 * Verify the Login Section of Demo Site 
 * The script uses TestNG
 */
public class Login {

	//public static WebDriver driver;	
	 public String username = "YOUR_USERNAME";
	    public String accesskey = "YOUR_ACCESS_KEY";
	    public static RemoteWebDriver driver;
	    public String gridURL = "@hub.lambdatest.com/wd/hub";
	    
	    @BeforeTest
	    public void setUp() throws Exception {
	       DesiredCapabilities capabilities = new DesiredCapabilities();
	       capabilities.setCapability("browserName", "Internet Explorer");
	   	   capabilities.setCapability("version","11.0");
	   	   capabilities.setCapability("ie.compatibility",11001);
	        capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get the any available one
	        capabilities.setCapability("build", "SampleApp");
	        capabilities.setCapability("name", "Login");
	       
	        try {
	            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
	        } catch (MalformedURLException e) {
	            System.out.println("Invalid grid URL");
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }
	/*
	 * Before Executing Test, Setup test environment
	 */
	/*@BeforeMethod
	public void invokeBrowser() {
		// Setup Firefox driver
		System.setProperty("webdriver.chrome.driver", Util.CHROME_PATH);
		// Launch Chrome
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// getCredentials();	//Commented as TestNG annotations are being used
	}*/

	/*
	 * Test Script 01 
	 * Test Steps 
	 * 1) To get credentials from Excel sheet For every set of Username and Password 
	 * 2) Go to http://www.demo.guru99.com/V4/ 
	 * 3) Enter UserId 
	 * 4) Enter Password 
	 * 5) Click Login 
	 * 6) Verify if Login is interrupted by verifying the error message that is displayed in popup 
	 * 7) else verify that Login is successful by verifying the title of Home page, verify ManagerId and Take Screenshot of the loggedin page
	 */
	@Test(dataProvider="LoginCredentialProvider",dataProviderClass = LoginDataProvider.class)
    public void testLogin(String userName,String password) throws InterruptedException{
		System.out.println("Username:" + userName + " " + "Password:" + password);
		// Go to Base URL
		driver.get(Util.BASE_URL + "v4/");
		// Enter userID
		WebElement user = new WebDriverWait(driver, 40)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='uid']")));
		user.sendKeys(userName);
		// Enter password
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		// Click on Login
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		//Verify if login is interrupted by popup else verify if login is successful
		try {
			driver.switchTo().alert();
			System.out.println("Login Status: " + driver.switchTo().alert().getText());
			driver.switchTo().alert().accept();
		} catch (NoAlertPresentException Ex) {
			String actualTitle = driver.getTitle();
			if (actualTitle.contains("Guru99 Bank Manager HomePage")) {
				System.out.println("Login Status: Login Successful");
			}
				else System.out.println("Login Status: Login Successful verification failed");					
			String actualId = driver.findElement(By.xpath("//tr[@class='heading3']")).getText();
			String expectedId = "Manger Id : "+userName;
			Assert.assertEquals(actualId, expectedId, "ManagerId verification failed");
			System.out.println("ManagerId verification successful");
			Util.screenshotProvider("Login");
   }
}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	
	// This method is used to fetch data from an excel and iterate without using Data Provider annotation of TestNG
	/*@Test(dataProvider = "LoginCredentialProvider")
	public void getCredentials(String... testData) throws Exception{
			System.out.println("Username:" + testData[0] + " " + "Password:" + testData[1]);
			// Go to Base URL
			driver.get(Util.BASE_URL);
			// Enter valid userID
			WebElement user = new WebDriverWait(driver, 40)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='uid']")));
			user.sendKeys(testData[0]);
			// Enter valid password
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(testData[1]);
			// Click on Login
			driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
			try {
				driver.switchTo().alert();
				System.out.println("Login Status: " + driver.switchTo().alert().getText());
				driver.switchTo().alert().accept();
			} catch (NoAlertPresentException Ex) {
				String actualTitle = driver.getTitle();
				if (actualTitle.contains("Guru99 Bank Manager HomePage")) {
					System.out.println("Login Status: Login Successful");
			}
		}
	}*/
	
	//This method is used to fetch data from excel using Data provider annotation of TestNG
	/*@DataProvider(name = "LoginCredentialProvider")
	public Object[][] loginTestData() throws IOException{
		return Credentials.readExcel("D:\\Guru99\\Credentials.xlsx", "Credentials.xlsx", "Login");		
	}*/

	/*
	 * Setup and launch browser
	 */

	/*
	 * public static void main(String[] args) { Login test = new Login();
	 * test.invokeBrowser(); }
	 */

}
