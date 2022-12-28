package pom.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pom.base.BaseTest;
import pom.objects.BillingAddress;
import pom.objects.Product;
import pom.objects.User;
import pom.pages.CartPage;
import pom.pages.CheckoutPage;
import pom.pages.HomePage;
import pom.pages.StorePage;
import pom.utils.ConfigLoader;
import pom.utils.JacksonUtils;

public class TestCase1 extends BaseTest{

	//@Test
	public void CheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {

		BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
		Product product = new Product(1215);

		StorePage storePage = new HomePage(getDriver()).load().
				getMyHeader().clickStoreMenulink();

		
		storePage.search("Blue");
		System.out.println("Search with Blue keywork");

		//Assert.assertTrue(storePage.getTitle().contains("Search results"));
		System.out.println("Search Result title ");
		//Assert.assertEquals(storePage.getTitle(), "Search results: �Blue�");
		storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
		
		CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
		
		Assert.assertEquals(cartPage.getProductName(), product.getName());

		//Assert.assertEquals(checkoutPage.getCheckoutTitle(), "Checkout");
		
		//Using Builder Pattern
		CheckoutPage checkoutPage= cartPage.clickCheckoutBtn().
				 setBillingAddress(billingAddress).selectDirectBankTransfer().
				 clickPlaceOrderBtn();

		//Thread.sleep(3000);
		Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");


	}


	//@Test
	public void LoginAndCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {


		BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
		Product product = new Product(1215);
		User user = new User(ConfigLoader.getInstance().getUsername(),
				ConfigLoader.getInstance().getPassword());

		StorePage storePage = new HomePage(getDriver()).load().
				getMyHeader().clickStoreMenulink();
		Thread.sleep(3000);
		
		storePage.search("Blue");
		storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
		Thread.sleep(3000);
		
		CartPage cartPage = storePage.getProductThumbnail().clickViewCart();
		Thread.sleep(3000);
		
		Assert.assertEquals(cartPage.getProductName(), product.getName());

		CheckoutPage checkoutPage= cartPage.clickCheckoutBtn();
		Thread.sleep(3000);
		Assert.assertEquals(checkoutPage.getCheckoutTitle(), "Checkout");

		checkoutPage.clickShowLoginBtn().
		enterUserName("Amy").
		enterPassword("amytest123").clickLogin();

		checkoutPage.setBillingAddress(billingAddress).selectDirectBankTransfer().clickPlaceOrderBtn();

		Thread.sleep(3000);
		Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");

	}


}
