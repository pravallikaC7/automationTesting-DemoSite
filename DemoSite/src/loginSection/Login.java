/*
 * Test script for testing Website Guru99 Bank
 * The test scripts is developed using Selenium Framework
 *
 */
package loginSection;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Assert;

/*
 * Verify the Login Section of Demo Site 
 */
public class Login {
	
	public static WebDriver driver;
	
/*
 * Setup and launch browser
 */
	public void invokeBrowser() {
		//Setup Firefox driver
		System.setProperty("webdriver.gecko.driver", Util.FIREFOX_PATH);
		//Launch Firefox 
		driver = new FirefoxDriver();	
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		loginSuccessful();
	}
	
	/*
	 * Test Script 01
       Test Steps
       1)  Go to http://www.demo.guru99.com/V4/
       2) Enter valid UserId
       3) Enter valid Password
       4) Click Login
       5) Verify that Login is successful by verifying the Title of Home Page
	 */
	public void loginSuccessful() {
		//Go to Base URL
		driver.get(Util.BASE_URL);
		//Enter valid userID
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(Util.userName);	
		//Enter valid password
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Util.password);
		//Click on Login
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		String expected = "Guru99 Bank Manager HomePage";
		Assert.assertEquals(expected, driver.getTitle());
		//Verify that Login is Successful.
		System.out.println("Login Successful");
		}

	public static void main(String[] args) {
		Login test = new Login();
		test.invokeBrowser();

	}

}
