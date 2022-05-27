import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;

public class Task4 {
    @Test
    public void Task4() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        Actions actions=new Actions(driver);
        WebElement women=driver.findElement(By.xpath("//a[@title='Women']"));
        actions.moveToElement(women).perform();
        WebElement summerDresses=driver.findElement(By.xpath("//li[@class]//a[.='Summer Dresses']"));
        summerDresses.click();

        WebElement productCounter=driver.findElement(By.xpath("//span[@class='heading-counter']"));
        System.out.println(productCounter.getText().trim());

        WebElement showingItems=driver.findElement(By.xpath("(//div[@class='product-count'])[1]"));
        String actualShowingItems=showingItems.getText().trim();
        String expectedShowingItems="Showing 1 - 3 of 3 items";

        Assert.assertEquals(actualShowingItems,expectedShowingItems);

      //  WebElement slider= driver.findElement(By.id("layered_price_slider"));
        WebElement range=driver.findElement(By.xpath("(//div[@id='layered_price_slider']//a)[1]"));
        String rangeNumber="left: 25%;";
        WebElement range2=driver.findElement(By.xpath("(//div[@id='layered_price_slider']//a)[2]"));
        String rangeNumber2="left: 75%;";


        while(!range.getAttribute("style").equals(rangeNumber)&&!range2.getAttribute("style").equals(rangeNumber2)){
            range.sendKeys(Keys.ARROW_RIGHT);
            range2.sendKeys(Keys.ARROW_LEFT);

        }









    }
}
