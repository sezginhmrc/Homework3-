package Utilites;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    public  static WebDriver createADriver(String browserName){

        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().version("79.0").setup();
            return new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("opera")){
            WebDriverManager.operadriver().setup();
            return new OperaDriver();
        } else {
            return new SafariDriver();

        }


    }
}
