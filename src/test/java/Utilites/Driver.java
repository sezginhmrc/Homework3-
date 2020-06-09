package Utilites;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {


    // Sigleton Driver
    /*We have singleton driver which provides only one driver for whole project
    How to make singleton driver ?
    private static intance
    private consturcotr
    public getter method (getDriver)
    when we call this class we direclty apply getDriver method which returns instance of class
*/


    private static WebDriver driver ;

    private Driver(){
    }



    public static WebDriver getDriver(){
        if(driver==null){
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            if ("chrome".equals(browser)) {
                WebDriverManager.chromedriver().version("81").setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
            } else if ("chromeheadless".equals(browser)) {
                WebDriverManager.chromedriver().version("81").setup();
                ChromeOptions options = new ChromeOptions();
                options.setHeadless(true);
                driver = new ChromeDriver(options);
            } else if ("firefox".equals(browser)) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if ("safari".equals(browser)) {
                WebDriverManager.getInstance(SafariDriver.class).setup();
                driver = new SafariDriver();
            } else {
                throw new RuntimeException("Invalid browser name");
            }
        }
        return driver;
    }


    public static  void closeDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }


}
