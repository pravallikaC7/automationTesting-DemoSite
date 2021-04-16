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
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		System.setProperty("webdriver.chrome.driver", Util.FIREFOX_PATH);
		//Launch Firefox 
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		getCredentials();
	}
	
	public void getCredentials() {
		Credentials excelObj = new Credentials();
		try {
			 excelObj.readExcel("D:\\Guru99\\Credentials.xlsx","Credentials.xlsx","Login");
		} catch (IOException e) {
		}
		int row = excelObj.loginCredentials.length;
		for(int i=0; i<row; i++) {
			loginSuccessful(excelObj.loginCredentials[i][0], excelObj.loginCredentials[i][1]);
		}
		
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
	public void loginSuccessful(String userName1, String password1) {
		String userName = userName1;
		String password = password1;
		//Go to Base URL
		driver.get(Util.BASE_URL);
		//Enter valid userID
		WebElement user = new WebDriverWait(driver, 40).until(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@name='uid']")));
		user.sendKeys(userName);
		//driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userName);	
		//Enter valid password
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		//Click on Login
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		boolean popupPresence = isAlertPresent();
		if(popupPresence==false) {
		String actualTitle = driver.getTitle();
		if (actualTitle.contains("Guru99 Bank Manager HomePage")) {
				    System.out.println("Login Successful");
				    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,500);");
				    driver.findElement(By.xpath("//a[@href='Logout.php']")).click();	
				    driver.switchTo().alert().accept();
		} 
		}
		else {
					driver.switchTo().alert().accept();
				    System.out.println("Login Failed");
		}
		
	}
	
	public boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	} 

	public static void main(String[] args) {
		Login test = new Login();
		test.invokeBrowser();
	}

}
