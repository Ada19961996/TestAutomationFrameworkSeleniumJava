package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v142.overlay.model.LineStyle;

import java.util.List;

public class FirstTestCase {

    public static void main(String[] args){

        WebDriver driver= new ChromeDriver();
        driver.get("https://google.com");
        driver.findElement(By.id("")).sendKeys();
        driver.findElement(By.name("")).click();
        List<WebElement> headerLinks = driver.findElements(By.className(""));
        System.out.println(headerLinks.size());
        driver.findElement(By.tagName(""));

        driver.close();
    }


}
