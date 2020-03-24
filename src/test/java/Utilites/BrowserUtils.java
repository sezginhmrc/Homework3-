package Utilites;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {
    public static void wait(int seconds){
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // this is the logic the get texts from List of Element
    // whatever they pass as aan element
    // it will get you the values of element as a collection of string
    public static List<String> getTextFromWebElements(List<WebElement> elements){
        List<String> textValues = new ArrayList<>();
        for (WebElement element : elements){
            textValues.add(element.getText());
        }
        return textValues ;
    }
}
