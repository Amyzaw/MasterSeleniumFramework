package pom.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pom.base.BasePage;
import pom.objects.Product;
import pom.pages.components.MyHeader;
import pom.pages.components.ProductThumbnail;

public class HomePage extends BasePage {

	private MyHeader myHeader;
	private ProductThumbnail productThumbnail;
	
	public MyHeader getMyHeader() {
		return myHeader;
	}


	public ProductThumbnail getProductThumbnail() {
		return productThumbnail;
	}

	
	
	public HomePage(WebDriver driver) {
		super(driver);
		myHeader = new MyHeader(driver);
		productThumbnail = new ProductThumbnail(driver);

	}

	public HomePage load() {
		load("/");

		return this;
	}

	public ProductPage navigateToTheProduct(int id) throws IOException {
        driver.findElement(By.xpath("//h2[normalize-space()='"+ new Product(id).getName() + "']")).click();
        return new ProductPage(driver);
    } 
}
