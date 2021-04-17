/*
 * Test script for testing Website Guru99 Bank
 * The test scripts is developed using Selenium Framework
 *
 */
package loginSection;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/*
 * Verify the Login Section of Demo Site 
 */
public class Login {

	public static WebDriver driver;

	/*
	 * Setup and launch browser
	 */
	@BeforeTest
	public void invokeBrowser() {
		// Setup Firefox driver
		System.setProperty("webdriver.chrome.driver", Util.FIREFOX_PATH);
		// Launch Chrome
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// getCredentials();
	}

	/*
	 * Test Script 01 
	 * Test Steps 
	 * 1) To get credentials from Excel sheet For every set of Username and Password 
	 * 2) Go to http://www.demo.guru99.com/V4/ 
	 * 3) Enter UserId 
	 * 4) Enter Password 
	 * 5) Click Login 
	 * 6) Verify if Login is interrupted by verifying the error message that is displayed in popup 
	 * 7) else verify that Login is successful by verifying the title of Home page and the Logout
	 */
	@Test
	public void getCredentials() {
		Credentials excelObj = new Credentials();
		try {
			excelObj.readExcel("D:\\Guru99\\Credentials.xlsx", "Credentials.xlsx", "Login");
		} catch (IOException e) {
		}
		int row = excelObj.loginCredentials.length;
		for (int i = 0; i < row; i++) {
			String userName = excelObj.loginCredentials[i][0];
			String password = excelObj.loginCredentials[i][1];
			System.out.println("Username:" + userName + " " + "Password:" + password);
			// Go to Base URL
			driver.get(Util.BASE_URL);
			// Enter valid userID
			WebElement user = new WebDriverWait(driver, 40)
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='uid']")));
			user.sendKeys(userName);
			// Enter valid password
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
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
					((JavascriptExecutor) driver).executeScript("window.scrollTo(0,500);");
					driver.findElement(By.xpath("//a[@href='Logout.php']")).click();
					driver.switchTo().alert().accept();
				}
			}
		}
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

	/*
	 * public static void main(String[] args) { Login test = new Login();
	 * test.invokeBrowser(); }
	 */

}