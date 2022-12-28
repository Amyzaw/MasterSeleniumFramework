package pom.factory.abstractFactory;

import pom.constant.DriverType;

public class DriverManagerFactoryAbstract {

	public static DriverManagerAbstract getManager(DriverType driverType) {
		switch (driverType) {
		case CHROME -> {
			return new ChromeDriverManagerAbstract();
			}
		case FIREFOX -> {
			return new FirefoxDriverManagerAbstract();
			}
		default -> throw new IllegalStateException("Invalid driver:" + driverType);
		
		}
	}
}
