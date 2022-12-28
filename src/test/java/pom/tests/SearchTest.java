package pom.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pom.base.BaseTest;
import pom.objects.Product;
import pom.pages.ProductPage;
import pom.pages.StorePage;
import pom.utils.FakerUtils;

public class SearchTest extends BaseTest {

	//protected WebDriver driver;
	@Test
	public void searchWithPartialMatch() throws InterruptedException {
		 
		String searchFor = "Blue";
		StorePage storePage = new StorePage(getDriver()).
				load().
				search(searchFor);
		Thread.sleep(3000);
		//WebDriverWait wait = new WebDriverWait(driver, TimeSpan.FromSeconds(5));
		//wait.until(ExpectedConditions.urlContains("/post_type=product"));
		Assert.assertTrue(storePage.getTitle().contains("Search results"));
		
	}
	

    @Test
    public void searchWithExactMatch() throws IOException {
        Product product = new Product(1215);
        ProductPage productPage = new StorePage(getDriver()).
                load().
                searchExactMatch(product.getName());
        Assert.assertEquals(productPage.getProductTitle(),product.getName());
    }

    @Test
    public void SearchForNonExistingProduct() {
        StorePage storePage = new StorePage(getDriver()).
                load().
                search(new FakerUtils().generateRandomName());
        Assert.assertEquals(storePage.getInfo(),"No products were found matching your selection.");
    }
}
