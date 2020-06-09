package Homework4;

import Utilites.BrowserUtils;
import Utilites.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class AmazonTests_LinkTests {

    private WebDriver driver ;

    @BeforeMethod
    public void setup() {
        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        driver = DriverFactory.createADriver("chrome");
    }


    @Test
    public void test() {

        driver.get("https://www.amazon.com/");

        Select select = new Select(driver.findElement(By.cssSelector("select[name='url']")));

        String actual = select.getFirstSelectedOption().getText();
        String expected = "All";
        Assert.assertTrue(actual.contains(expected));

        List<WebElement> allOptions = select.getOptions();

/*
        List<String> list1 = new ArrayList<>();
        for (WebElement each : allOptions) {
            list1.add(each.getText().trim());
        }
        System.out.println(list1);

        List<String> list2 = new ArrayList<>(list1);
        System.out.println(list2);
        Collections.sort(list2);

        Assert.assertTrue(!list1.equals(list2));*/


        // Solution 2

        boolean notSorted = false ;
        for (int i = 0; i <allOptions.size()-1 ; i++) {
            if(allOptions.get(i).getText().compareTo(allOptions.get(i+1).getText())>0){
                notSorted=true;
                break;
            }
        }
        Assert.assertTrue(notSorted);




    }


    @Test
    public void verifyMainDepts (){
        String url = "https://www.amazon.com/gp/site-directory";

        driver.get(url);

        driver.get("https://www.amazon.com/gp/site-directory");

        Select select = new Select(driver.findElement(By.cssSelector("select[name='url']")));
        List<WebElement> allOptions = select.getOptions();

        // all department dropdown
        List<String> list1 = new ArrayList<>();
        for (WebElement each : allOptions) {
            list1.add(each.getText().trim());
        }
        System.out.println(list1);


        // all blue titles
        List<WebElement> allTittles = driver.findElements(By.cssSelector("div[class='fsdDeptBox'] > h2"));
        List<String> list2 = new ArrayList<>();
        for (WebElement blues : allTittles) {
            list2.add(blues.getText().trim());
        }
        System.out.println(list2);



        boolean statement  = false ;
        for (String each : list2) {
            for(String each1 : list1){
                statement = each1.contains(each);

            }
        }
        Assert.assertTrue(statement);

       // Assert.assertTrue(list1.containsAll(list2));

    }
    @Test
    public void verifyLinks(){

        String url = "https://www.w3schools.com/";
        driver.get(url);

        List<WebElement> listofLinks = driver.findElements(By.xpath("//a[@href]"));

        List<String> links = new ArrayList<>();
        for(WebElement each : listofLinks){
            Assert.assertTrue(each.isEnabled());
            links.add(each.getAttribute("href"));
            links.add(each.getText());
            System.out.println(each.getText());
            System.out.println(each.getAttribute("href"));
        }

    }

    @Test
    public void verifyBrokenLinks(){
        String website ="https://www.selenium.dev/documentation/en/";
        driver.get(website);

        String url = "";
        String domain = "https://www.selenium.dev";
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for(WebElement each :links){
                url = each.getAttribute("href");
                if(url == null || url.isEmpty()){
                    System.out.println("url is not confiured or realted another tag or it is empty");
                    continue;
                } if(!url.startsWith(domain)){
                System.out.println("Url not belong to our domain");
                continue;
            }
                try {
                    HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());
                    connection.setRequestMethod("HEAD");
                    connection.connect();
                    if (connection.getResponseCode()!=200)
                    Assert.assertEquals(200,connection.getResponseCode());
                    System.out.println(url +  "url is valid");
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

    }

    @Test
    public void verifyCartAmazon(){
        String url = "https://amazon.com";
        driver.get(url);

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon", Keys.ENTER);

        driver.findElement(By.id("B0000CCY1R-amazons-choice")).click();

        Select select = new Select(driver.findElement(By.id("quantity")));
        String actual = select.getFirstSelectedOption().getText().trim();
        String expected = String.valueOf(1);

        Assert.assertEquals(actual,expected);

        WebElement addTheCard = driver.findElement(By.id("submit.add-to-cart-announce"));
        Assert.assertTrue(addTheCard.isDisplayed());

    }
/*
* 1.go to https://amazon.com
* 2.search for "wooden spoon"
* 3.click search
* 4.remember name first resultthat has prime label
* 5.select Prime checkboxon the left
* 6.verifythatname first resultthat has prime label is same as step 4
* 7.check the lastcheckbox under Brand on the left
* 8.verifythatname first resultthat has prime label is different*/
    @Test
    public void verifyPrimeAmazon(){
        String url = "https://amazon.com";

        driver.get(url);

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon", Keys.ENTER);

        WebElement prime = driver.findElement(By.xpath("(//a[@class=\"a-link-normal a-text-normal\"])[2]"));
        String urlofFirst = prime.getAttribute("href");

        Assert.assertTrue(prime.isDisplayed());
         BrowserUtils.wait(5);

         driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();

        driver.navigate().refresh();

        Assert.assertTrue(prime.isDisplayed());

        driver.navigate().refresh();

        driver.findElement(By.xpath("(//input[@type='checkbox'])[17]")).click();
        WebElement alink = driver.findElement(By.xpath("(//a[@class=\"a-link-normal a-text-normal\"])[4]"));
        String urlofSecond = alink.getAttribute("href");

        Assert.assertNotEquals(urlofFirst,urlofSecond);
    }

    @Test
    public void moreSpoonsTest(){

        String url = "https://amazon.com";

        driver.get(url);

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon", Keys.ENTER);

    }



    @AfterMethod
    public void teardown(){
        driver.close();
    }
}

