package pom.base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pom.utils.ConfigLoader;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));//create wait object
	}

	public void load(String endpoint) {
		driver.get(ConfigLoader.getInstance().getBaseUrl()+ endpoint);
	}

	public void waitForOverLaysToDisappear(By overlay) {
		List<WebElement> overLays = driver.findElements(overlay);
		System.out.println("");
		if(overLays.size() > 0) {
			wait.until(
					ExpectedConditions.invisibilityOfAllElements(overLays));

			System.out.println("Overlay Invisible");
		}else {
			System.out.println("Overlay Not Found");
		}
	}

	/*
	 * public WebElement waitForElementToBeVisible(By element) { return
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(element)); }
	 */

}
