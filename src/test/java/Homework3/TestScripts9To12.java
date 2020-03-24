package Homework3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestScripts9To12 {

    // TEST #9 #10 #11 #12
    @DataProvider(name ="testData")
    public Object [] testData(){
        return new Object[] {"200","301","404","500"};
    }

    @Test(description = "verifying displayed message",dataProvider = "testData")
    public void test(String code){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/");
        driver.findElement(By.linkText("Status Codes")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement statusCode =driver.findElement(By.linkText(code));
        statusCode.click();

        String expected = "This page returned a "+code+" status code";
        String actual = driver.findElement(By.xpath("//p")).getText();

        Assert.assertTrue(actual.contains(expected));

        driver.close();
    }
}
