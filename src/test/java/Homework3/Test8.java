package Homework3;

import Utilites.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Test8 {

    private WebDriver driver ;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createADriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void test() {
        driver.get("http://practice.cybertekschool.com/");
        driver.findElement(By.linkText("Autocomplete")).click();
        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        driver.findElement(By.xpath("//input[@value='Submit']")).click();

        String expected = "You selected: United States of America";
        String actual = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(actual,expected);

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
