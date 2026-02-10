package testBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class FirstTestCase {

    public static void main(String[] args){

        WebDriver driver= new ChromeDriver();
        driver.get("https://demo.nopcommerce.com");
        driver.get("hhttps://tutorialsninja.com/demo");

        driver.findElement(By.id("")).sendKeys();
        driver.findElement(By.name("")).click();
        List<WebElement> headerLinks = driver.findElements(By.className(""));
        System.out.println(headerLinks.size());
        driver.findElement(By.tagName(""));
        driver.manage().window().maximize();

        driver.close();
    }


}
