package pom.base;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.restassured.http.Cookies;
import pom.constant.DriverType;
import pom.factory.DriverManagerFactory;
import pom.factory.DriverManagerOriginal;
import pom.factory.abstractFactory.DriverManagerAbstract;
import pom.factory.abstractFactory.DriverManagerFactoryAbstract;
import pom.utils.CookieUtils;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class BaseTest {
	
	protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	protected ThreadLocal<DriverManagerAbstract> driverManager = new ThreadLocal<>();

	private void setDriver(WebDriver driver) {
		this.driver.set(driver);
	}

	protected WebDriver getDriver() {
		return this.driver.get();
	}

	private void setDriverManager(DriverManagerAbstract driverManager) {
		this.driverManager.set(driverManager);
	}

	protected DriverManagerAbstract getDriverManager() {
		return this.driverManager.get();
	}

	
	@Parameters("browser")
	@BeforeMethod
	public synchronized void startDriver(@Optional String browser) {
		browser = System.getProperty("browser",browser); // it takes the browser value from testng
		if(browser== null) browser ="CHROME";
	//	setDriver(new DriverManagerOriginal().initializeDriver(browser));
	//	setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
		setDriverManager(DriverManagerFactoryAbstract.getManager(DriverType.valueOf(browser)));
		setDriver(getDriverManager().getDriver());
		System.out.print("CURRENT THRED: " + Thread.currentThread().getId() +","+
				"DRIVER =" + getDriver());
	}

	@Parameters("browser")
	@AfterMethod
	public synchronized void quitDriver(@Optional String browser,ITestResult result) throws InterruptedException, IOException {

		Thread.sleep(300);
		System.out.print("CURRENT THRED: " + Thread.currentThread().getId() +","+
				"DRIVER =" + getDriver());
//		getDriver().quit();
		
		
		if(result.getStatus()== ITestResult.FAILURE) {
			File destFile = new File("scr"+ File.separator+ browser + File.separator +
					result.getTestClass().getRealClass().getSimpleName() +"_" +
					result.getMethod().getMethodName()+".png");
		//	takeScreenshot(destFile);
			takeScreenshotUsingAShot(destFile);
		}

		getDriverManager().getDriver().quit();
	}
	
	public void injectCookiessToBrowser(Cookies cookies) {
		List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
		for (Cookie cookie: seleniumCookies) {
			getDriver().manage().addCookie(cookie);
		}
	}

	private void takeScreenshot(File destfile) throws IOException {
		TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
		File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, destfile);
		
	}
	
	private void takeScreenshotUsingAShot(File destFile)
	{
		Screenshot screenshot = new AShot().
		shootingStrategy(ShootingStrategies.viewportPasting(100)).
		takeScreenshot(getDriver());
		
		try {
			ImageIO.write(screenshot.getImage(),"PNG", destFile);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

