import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    @Test
    public void Test2(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        List<WebElement> productPrices= driver.findElements(By.xpath("//ul[@id='homefeatured']//div[@class='right-block']//span[@class='price product-price']"));
        List<WebElement> nameofProducts=driver.findElements(By.xpath("//ul[@id='homefeatured']//a[@class='product-name']"));
        List<Double> prices=new ArrayList<>();
        List<String> productNames=new ArrayList<>();

        int index=0;
        double max=0;
        for (int i=0;i<productPrices.size();i++){
            prices.add((Double.parseDouble(productPrices.get(i).getText().trim().substring(1))));
            if (max<prices.get(i)) {
                max=prices.get(i);
                index=i;
            }
            productNames.add(nameofProducts.get(i).getText().trim());
        }

        System.out.println(productNames.get(index)+" $"+prices.get(index));

        Actions actions=new Actions(driver);
        actions.moveToElement(productPrices.get(index)).perform();
        WebElement more= driver.findElement(By.xpath("//a[@data-id-product='4']//..//span[.='More']"));
        actions.moveToElement(more).perform();
        more.click();

        WebElement priceElement=driver.findElement(By.id("our_price_display"));
        Double price= Double.parseDouble(priceElement.getText().trim().substring(1));
        Assert.assertEquals(price,prices.get(index));

        WebElement nameElement=driver.findElement(By.tagName("h1"));
        Assert.assertEquals(nameElement.getText().trim(),productNames.get(index));

        WebElement colorChange=driver.findElement(By.name("Pink"));
        colorChange.click();

        String currentUrl=driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(colorChange.getAttribute("name").toLowerCase()));


    }
}
