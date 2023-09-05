package com.sauceDemo.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    /*
    We make WebDriver private, because we want to  close
    access from outside of this class
    We make it static because we will use it in a static method
    */
    //private static WebDriver driver;
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    /*
    Create a re-usable utility method which will return same driver instance when we call it
     */
    public static WebDriver getDriver() {

        if (driverPool.get() == null) {
            /*
            We read our browserType from configuration.properties
            This way, we can control which browser is opened from our code,
            from configuration.properties
             */

            String browserType = ConfigurationReader.getProperty("browser");

            /*
                Depending on the browserType that will be return from configuration.properties file
                switch statement will determine the case and open the matching browser
             */
            switch (browserType) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                    driverPool.set(new ChromeDriver());
                    break;
                case "firefox":
                    driverPool.set(new FirefoxDriver());
                    break;
            }

        }
        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();//this line terminate the existing session.
            // But Value will not even be null
            driverPool.remove();
        }

    }

}
