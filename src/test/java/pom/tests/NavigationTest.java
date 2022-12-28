package pom.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pom.base.BaseTest;
import pom.objects.Product;
import pom.pages.HomePage;
import pom.pages.ProductPage;
import pom.pages.StorePage;

public class NavigationTest extends BaseTest {
	
	@Test
	public void NavigateFromHomeToStoreUsingMainMenu () {
		
		StorePage storePage = new HomePage(getDriver()).load().
				getMyHeader().clickStoreMenulink();
		
		Assert.assertEquals(storePage.getTitle(), "Store");
	}


	@Test
    public void NavigateFromStoreToTheProduct() throws IOException {
        Product product = new Product(1215);
        ProductPage productPage = new StorePage(getDriver()).
                load().
                navigateToTheProduct(product.getId());
        Assert.assertEquals(productPage.getProductTitle(), product.getName());
    }

    @Test()
    public void NavigateFromHomeToTheFeaturedProduct() throws IOException {
        Product product = new Product(1215);
        ProductPage productPage = new HomePage(getDriver()).
                load().
                navigateToTheProduct(product.getId());
        Assert.assertEquals(productPage.getProductTitle(), product.getName());
    }
}
