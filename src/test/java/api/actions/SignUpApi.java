package api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import pom.objects.User;
import pom.utils.*;

public class SignUpApi {

	private Cookies cookies;
	
	public Cookies getCookies() {
		return cookies;
	}
	
	private String fetchRegisterNonceValueUsintGroovy() {
		Response response = getAccount();
		return response.htmlPath().getString("**.findAll{it.@name== 'woocommerce-register-nonce'}.@value");
	}
	
	public String fetchRegisterNonceValueUsingJSoup() {
		Response response = getAccount();
		Document doc = Jsoup.parse(response.body().prettyPrint());
		Element element = doc.selectFirst("#woocommerce-register-nonce");
		return element.attr("value");
	}
	
	private Response getAccount() {
		Cookies cookies = new Cookies();
		Response response =	given().baseUri(ConfigLoader.getInstance().getBaseUrl()).
		cookies(cookies).
		log().all().
	when().
		get("/account").
	then().
		log().all().
		extract().
		response();
		
if(response.getStatusCode() != 200) {
	throw new RuntimeException("Failed to fetch the account, HTTP status code:" + response.getStatusCode());
}
return response;
	}
	
	public Response Register(User user) {
		Cookies cookies = new Cookies();
		Header header = new Header("content-type","application/x-www-form-urlencoded");
		Headers headers = new Headers(header);
		HashMap<String, String> formParams = new HashMap<>();
		formParams.put("username", user.getUsername());
		formParams.put("email", user.getEmail());
		formParams.put("password", user.getPassword());
		formParams.put("woocommerce-register-nonce", fetchRegisterNonceValueUsingJSoup());
		formParams.put("register","Register");
		
		Response response =	given().baseUri(ConfigLoader.getInstance().getBaseUrl()).
				headers(headers).
				formParams(formParams).
		cookies(cookies).
		log().all().
	when().
		post("/account").
	then().
		log().all().
		extract().
		response();
		
if(response.getStatusCode() != 302) {
	throw new RuntimeException("Failed to register the account, HTTP status code:" + response.getStatusCode());
}
this.cookies = response.getDetailedCookies();
return response;
	}
	
}
