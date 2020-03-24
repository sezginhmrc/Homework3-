package Homework3;

import Utilites.BrowserUtils;
import Utilites.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Test7 {
    private WebDriver driver ;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createADriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    @Test
    public void test(){
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("File Upload")).click();
        BrowserUtils.wait(3);

        WebElement file = driver.findElement(By.id("file-upload"));

        String path = "/Users/sezgin/Desktop/Screen Shot 2020-01-25 at 5.13.04 PM.png";

        file.sendKeys(path);
        BrowserUtils.wait(3);

        driver.findElement(By.id("file-submit")).click();
        BrowserUtils.wait(4);

        String expected ="File Uploaded!";
        String actual = driver.findElement(By.tagName("h3")).getText();

        Assert.assertEquals(actual,expected);
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
