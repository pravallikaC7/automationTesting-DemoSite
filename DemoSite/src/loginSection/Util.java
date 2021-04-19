package loginSection;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Util {

	public final static String CHROME_PATH = "D:\\Selenium IDE\\chromedriver_win32_v89\\chromedriver.exe";
	public final static String BASE_URL = "http://www.demo.guru99.com/";
	
	public static void screenshotProvider(String passedFileName) {
		TakesScreenshot scrShot = ((TakesScreenshot) Login.driver);
		File takenSS = scrShot.getScreenshotAs(OutputType.FILE);
		String fileName = "./Screenshots/"+passedFileName+".png";
		try {
			FileUtils.copyFile(takenSS, new File(fileName));
		} catch (IOException e) {
		} 
	}

}

//GeckoDriverPath: D:\\Selenium IDE\\geckodriver-v0.29.1-win64\\geckodriver.exe
