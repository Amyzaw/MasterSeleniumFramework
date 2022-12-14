package pom.factory;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pom.constant.DriverType;


public class DriverManagerOriginal {

	public WebDriver initializeDriver(String browser) {
		WebDriver driver;

		switch(DriverType.valueOf(browser)) {
			case CHROME :
			WebDriverManager.chromedriver().cachePath("Drivers").setup();
			 driver = new ChromeDriver();
			 break;
			case FIREFOX :
			WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
			 driver = new FirefoxDriver();
			 break;
			default : throw new IllegalStateException("Invalid browser name " + browser);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		return driver;
	}

}
