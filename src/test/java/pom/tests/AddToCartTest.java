package pom.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataproviders.MyDataProvider;
import pom.base.BaseTest;
import pom.objects.Product;
import pom.pages.CartPage;
import pom.pages.HomePage;
import pom.pages.ProductPage;
import pom.pages.StorePage;
import pom.utils.JacksonUtils;

public class AddToCartTest extends BaseTest {

	@Test
	public void addToCartFromStorePage() throws IOException{
		
		Product product = new Product(1215);
        CartPage cartPage = new StorePage(getDriver()).load().
        		getProductThumbnail().clickAddToCartBtn(product.getName()).clickViewCart();
        
        Assert.assertEquals(cartPage.getProductName(), product.getName());
	}
	
	@Test(dataProvider = "getFeaturedProducts", dataProviderClass= MyDataProvider.class)
	public void addToCartFeaturedProducts(Product product) {
		CartPage cartPage = new HomePage(getDriver()).load().
				getProductThumbnail().clickAddToCartBtn(product.getName()).clickViewCart();
		
		Assert.assertEquals(cartPage.getProductName(), product.getName());
		
	}
	
	 @Test()
	    public void AddToCartFromProductPage() throws IOException {
	        Product product = new Product(1215);
	        String productNameSeparatedByDash = product.getName().toLowerCase().replaceAll(" ", "-");

	        ProductPage productPage = new ProductPage(getDriver()).loadProduct(productNameSeparatedByDash).
	                addToCart();
	        Assert.assertTrue(productPage.getAlert().contains("“" + product.getName() +"” has been added to your cart."));
	    }
}
