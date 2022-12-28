package pom.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.actions.CartApi;
import api.actions.SignUpApi;
import pom.base.BaseTest;
import pom.objects.Product;
import pom.objects.User;
import pom.pages.AccountPage;
import pom.pages.CheckoutPage;
import pom.utils.FakerUtils;

public class LoginTest extends BaseTest {
	
	@Test
	public void LoginDuringCheckout() throws Exception {
		String username= "demouser" + new FakerUtils().generateRandomNumber();
		User user = new User().
				setUsername(username).
				setPassword("demopwd").
				setEmail(username + "@askomdch.com");
		
		SignUpApi signUpApi = new SignUpApi();
		signUpApi.Register(user);
		CartApi cartApi = new CartApi();
		Product product = new Product(1215);
		cartApi.addToCart(product.getId(), 1);
		
		CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
		Thread.sleep(5000);
		injectCookiessToBrowser(cartApi.getCookies());
		checkoutPage.load();
		Thread.sleep(5000);
		checkoutPage.clickShowLoginBtn().
				enterUserName("demouser").
				enterPassword("demopwd").clickLogin();
		Thread.sleep(5000);
		Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
	}
	
	 @Test()
	    public void shouldNotLoginWithAnInvalidPassword(){
	        String username = "demouser" + new FakerUtils().generateRandomNumber();
	        User user = new User(username, "demopwd", username + "@askomdch.com");
	        new SignUpApi().Register(user);

	        AccountPage accountPage = new AccountPage(getDriver()).load();
	        accountPage.login(user.getUsername(), "invalidPassword");
	        Assert.assertEquals(accountPage.getErrorTxt(), "Error: The password you entered for the username "
	                + user.getUsername() + " is incorrect. Lost your password?");
	    }

	    @Test()
	    public void shouldNotLoginWithANonExistingUser(){
	        String username = "demouser" + new FakerUtils().generateRandomNumber();
	        User user = new User(username, "demopwd", username + "@askomdch.com");

	        AccountPage accountPage = new AccountPage(getDriver()).load();
	        accountPage.login(user.getUsername(), "demopwd");
	        Assert.assertEquals(accountPage.getErrorTxt(), "Error: The username " + user.getUsername() +
	                " is not registered on this site." +
	                " If you are unsure of your username, try your email address instead.");
	    }

}
