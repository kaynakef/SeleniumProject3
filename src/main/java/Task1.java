import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class Task1 {
    @Test
    public void Task1(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
       WebElement cart= driver.findElement(By.xpath("//a[@title='View my shopping cart']"));
       String actual=cart.getText().trim();
       String expected="Cart (empty)";
        System.out.println(actual);
        Assert.assertEquals(actual,expected);

        List<WebElement> productPrices= driver.findElements(By.xpath("//ul[@id='homefeatured']//div[@class='right-block']//span[@class='price product-price']"));
        List<WebElement> nameofProducts=driver.findElements(By.xpath("//ul[@id='homefeatured']//a[@class='product-name']"));

        List<Double> prices=new ArrayList<>();
        List<String> productNames=new ArrayList<>();

        int index=0;
        for (int i=0;i<productPrices.size();i++){
            prices.add((Double.parseDouble(productPrices.get(i).getText().trim().substring(1))));
            //double value= new Double(priveText); instead of PArse double
            double min=prices.get(0);
            if (min>prices.get(i)) {
                min=prices.get(i);
                 index=i;
            }
            productNames.add(nameofProducts.get(i).getText().trim());
        }

        System.out.println(productNames.get(index)+" $"+prices.get(index));

        Double newProductPrice=prices.get(index);
        System.out.println("New Product Price: $"+newProductPrice);

        List<WebElement> discountRateElement= driver.findElements(By.xpath("//ul[@id='homefeatured']//div[@class='right-block']//span[@class='price-percent-reduction']"));
        Double discountRate=Double.parseDouble(discountRateElement.get(1).getText().substring(1, discountRateElement.get(1).getText().trim().length()-1));
        System.out.println("Discount Rate: " + discountRate+ "%");

        List<WebElement> originalPrices=driver.findElements(By.xpath("//ul[@id='homefeatured']//div[@class='right-block']//span[2]"));
        double originalPrice=Double.parseDouble(originalPrices.get(1).getText().substring(1));
        System.out.println("Original Price: $" +originalPrice);

        double expectedPrice= originalPrice-(originalPrice*discountRate/100);
        Assert.assertEquals(newProductPrice,expectedPrice);




    }


}
