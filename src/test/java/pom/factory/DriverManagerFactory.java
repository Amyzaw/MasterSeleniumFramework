package pom.factory;

import pom.constant.DriverType;

public class DriverManagerFactory {

	public static DriverManager getManager(DriverType driverType) {
		switch (driverType) {
		case CHROME -> {
			return new ChromeDriverManager();
			}
		case FIREFOX -> {
			return new FirefoxDriverManager();
			}
		default -> throw new IllegalStateException("Invalid driver:" + driverType);
		
		}
	}
}
