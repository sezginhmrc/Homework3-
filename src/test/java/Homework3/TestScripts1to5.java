package Homework3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestScripts1to5 {
    private WebDriver driver ;

    @BeforeMethod
    public void setup (){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Registration Form")).click();

    }

    @Test
    public void test1(){

        driver.findElement(By.name("birthday")).sendKeys("wrong_dob");
        String expected = "The date of birth is not valid";
        Assert.assertTrue(driver.findElement(By.xpath("//i[@style='display: block;']/following-sibling::small[2]"))
                .isDisplayed(),expected);
    }

    @Test
    public void test2(){
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//div[@class='form-check form-check-inline']"));
        for (WebElement boxes : checkBoxes){
            Assert.assertTrue(boxes.isDisplayed());
        }
    }

    @Test
    public void test3(){

        driver.findElement(By.name("firstname")).sendKeys("s");
        String expected = "first name must be more than 2 and less than 64 characters long";
        Assert.assertTrue(driver.findElement(By.xpath("//i[@data-bv-icon-for='firstname']/following-sibling::small[2]"))
                .isDisplayed(),expected);

    }
    @Test
    public void test4(){
        driver.findElement(By.name("lastname")).sendKeys("o");
        String expected = "first name must be more than 2 and less than 64 characters long";
        Assert.assertTrue(driver.findElement(By.xpath("//i[@data-bv-icon-for='lastname']/following-sibling::small[2]"))
                .isDisplayed(),expected);
    }
    @Test
    public void test5(){
        driver.findElement(By.name("firstname")).sendKeys("Sezgin");
        driver.findElement(By.name("lastname")).sendKeys("hamurcu");
        driver.findElement(By.name("username")).sendKeys("sezginhmrc");
        driver.findElement(By.name("email")).sendKeys("sezginhamurcu@icloud.com");
        driver.findElement(By.name("password")).sendKeys("yapmazsin");
        driver.findElement(By.name("phone")).sendKeys("413-231-2323");
        driver.findElement(By.xpath("//input[@value='male']")).click();
        driver.findElement(By.name("birthday")).sendKeys("02/02/1994");
        Select department = new Select(driver.findElement(By.name("department")));
        department.selectByVisibleText("Department of Engineering");
        Select jobTitle = new Select(driver.findElement(By.name("job_title")));
        jobTitle.selectByVisibleText("SDET");
        WebElement java = driver.findElement(By.id("inlineCheckbox2"));
        if(!java.isSelected()){
            java.click();
        }
        driver.findElement(By.id("wooden_spoon")).click();
        String actual = driver.findElement(By.tagName("p")).getText();
        String expected = "You've successfully completed registration!";
        Assert.assertEquals(actual,expected);

    }

    @AfterMethod
    public void close(){
        driver.quit();
    }
}
