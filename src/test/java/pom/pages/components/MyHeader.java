package pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pom.base.BasePage;
import pom.pages.StorePage;

public class MyHeader extends BasePage {
	
	By storeMenuLink = By.cssSelector("#menu-item-1227 > a");
	
	public MyHeader(WebDriver driver) {
		super(driver);
	}

	
	
	public StorePage clickStoreMenulink() {
		
		 wait.until(ExpectedConditions.elementToBeClickable(storeMenuLink)).click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(storeMenuLink)).click();
		 return new StorePage(driver);
		}


}
