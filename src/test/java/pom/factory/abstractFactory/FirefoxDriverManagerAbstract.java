package pom.factory.abstractFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverManagerAbstract extends DriverManagerAbstract{
private WebDriver driver;
	
@Override
protected void startDriver() {
	 WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
	 driver = new FirefoxDriver();
	 driver.manage().window().maximize();
	
}
}
