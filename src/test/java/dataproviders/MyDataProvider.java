package dataproviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import pom.objects.Product;
import pom.utils.JacksonUtils;

public class MyDataProvider {
	
	@DataProvider(name="getFeaturedProducts", parallel=false)
	public Object[] getFeaturedProducts() throws IOException {
		
		return JacksonUtils.deserializeJson("products.json", Product[].class);
	}

}
