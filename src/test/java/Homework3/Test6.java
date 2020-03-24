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

public class Test6 {
    private WebDriver driver ;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createADriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void test6(){

        driver.get("https://www.fakemail.net/");
        BrowserUtils.wait(2);
        driver.findElement(By.xpath("//a[@class='wpcc-btn']")).click();
        BrowserUtils.wait(2);

        driver.findElement(By.linkText("Copy")).click();
        String fake = driver.findElement(By.id("email")).getText();

        driver.navigate().to("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        BrowserUtils.wait(2);
        driver.findElement(By.name("full_name")).sendKeys("sezgin");
        driver.findElement(By.name("email")).sendKeys(fake);
        driver.findElement(By.name("wooden_spoon")).click();

        String actual = driver.findElement(By.name("signup_message")).getText();
        String expected = "Thank you for signing up. Click the button below to return to the home page.";

        Assert.assertEquals(actual,expected);

        driver.navigate().to("https://www.fakemail.net/");

        WebElement sender = driver.findElement(By.xpath("//table//tbody//tr[1]//td[1]"));

        String actualSender = sender.getText().trim();
        String expectedSender = "do-not-reply@practice.cybertekschool.com";

        Assert.assertEquals(actualSender,expectedSender);

        driver.findElement(By.xpath("//table//tbody//tr[1]//td[1]")).click();

        WebElement email = driver.findElement(By.id("odesilatel"));
        String actualEmail = email.getText();
        String expectedEmail = "do-not-reply@practice.cybertekschool.com";

        Assert.assertEquals(actualEmail,expectedEmail);

        WebElement subject = driver.findElement(By.id("predmet"));

        String actualSubject = subject.getText();
        String expectedSubjet = "Thanks for subscribing to practice.cybertekschool.com!";

        Assert.assertEquals(actualSubject,expectedSubjet);

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
