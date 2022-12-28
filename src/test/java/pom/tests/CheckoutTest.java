package pom.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.actions.BillingApi;
import api.actions.CartApi;
import api.actions.SignUpApi;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.Cookies;
import pom.base.BaseTest;
import pom.objects.BillingAddress;
import pom.objects.Product;
import pom.objects.User;
import pom.pages.CheckoutPage;
import pom.utils.FakerUtils;
import pom.utils.JacksonUtils;

@Epic("Test Epic")
@Feature("Test Feature")
public class CheckoutTest extends BaseTest{
	
	@Test
	public void GuestCheckoutUsingDirectBankTransfer() throws IOException {
		
		BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
		CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
		
		CartApi cartApi = new CartApi();
		cartApi.addToCart(1215,1);
		injectCookiessToBrowser(cartApi.getCookies());
		
		checkoutPage.load().
				 setBillingAddress(billingAddress).
				 selectDirectBankTransfer().
				 clickPlaceOrderBtn();

		Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");
	}
	
	@Story("Test Story")
	@Link("https://example.org")
	@Link(name = "allure", type = "mylink")
	@Issue("123")
	@TmsLink("test-1")
	@Description("This is allure description")
	@Test(description = "Testcase Descript 01" )
	public void LoginAndCheckoutUsingDirectBankTransfer() throws IOException, InterruptedException {
		
		BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
		String username= "demouser" + new FakerUtils().generateRandomNumber();
		User user = new User().
				setUsername(username).
				setPassword("demopwd").
				setEmail(username + "@askomdch.com");
		
		SignUpApi signUpApi = new SignUpApi();
		signUpApi.Register(user);
		CartApi cartApi = new CartApi(signUpApi.getCookies());//aftersignup/needcookies/andpassSignUpApi.getcookies()
		Product product = new Product(1215);
		cartApi.addToCart(product.getId(), 1);
		
		CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
		Thread.sleep(5000);
		injectCookiessToBrowser(signUpApi.getCookies());//InjectSinUpCookies
		checkoutPage.load();
		Thread.sleep(5000);
		 
		checkoutPage.setBillingAddress(billingAddress).
		 selectDirectBankTransfer().
		 clickPlaceOrderBtn();
		
		Assert.assertEquals(checkoutPage.getSuccessNotice(),"Thank you. Your order has been received.");
		
	}
	
	@Test
    public void GuestCheckoutUsingCashOnDelivery() throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        CartApi cartApi = new CartApi(new Cookies());
        cartApi.addToCart(1215, 1);
        injectCookiessToBrowser(cartApi.getCookies());

        checkoutPage.load().
                setBillingAddress(billingAddress).
                selectCashOnDeliveryTransfer().clickPlaceOrderBtn();
        Assert.assertEquals(checkoutPage.getSuccessNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void LoginAndCheckoutUsingCashOnDelivery() throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User(username, "demopwd", username + "@askomdch.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.Register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiessToBrowser(signUpApi.getCookies());
        checkoutPage.load();
        checkoutPage.setBillingAddress(billingAddress).
                selectCashOnDeliveryTransfer().clickPlaceOrderBtn();
        Assert.assertEquals(checkoutPage.getSuccessNotice(), "Thank you. Your order has been received.");
    }

    @Test
    public void CheckoutWithAnAccountHavingABillingAddress() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User(username, "demopwd", username + "@askomdch.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.Register(user);
        BillingApi billingApi = new BillingApi(signUpApi.getCookies());
        billingApi.addBillingAddress(billingAddress);

        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiessToBrowser(signUpApi.getCookies());
        checkoutPage.load();
        checkoutPage.selectDirectBankTransfer().clickPlaceOrderBtn();
        Assert.assertEquals(checkoutPage.getSuccessNotice(), "Thank you. Your order has been received.");
    }

}
