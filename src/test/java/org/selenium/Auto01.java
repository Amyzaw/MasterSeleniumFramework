package org.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Auto01 {

	@Test
	public void guestCheckoutUsingDirectBankTransfer() {

		WebDriver driver = new ChromeDriver();
		driver.get("https://askomdch.com");
		driver.manage().window().maximize();
	}

}
