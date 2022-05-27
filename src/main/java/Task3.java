import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

public class Task3 {

    @Test
    public void Task3() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        Actions actions=new Actions(driver);
        WebElement women=driver.findElement(By.xpath("//a[@title='Women']"));
        actions.moveToElement(women).perform();
        WebElement summerDresses=driver.findElement(By.xpath("//li[@class]//a[.='Summer Dresses']"));
        summerDresses.click();
        WebElement firstProduct=driver.findElement(By.xpath("(//ul[@class='product_list grid row']//li)[1]"));
        actions.moveToElement(firstProduct).perform();

        WebElement priceElement=driver.findElement(By.xpath("(//span[@class='price product-price'])[1]"));
        Double price= Double.parseDouble(priceElement.getText().trim().substring(1));

        WebElement addToCart=driver.findElement(By.xpath("(//a[@title='Add to cart'])[1]"));
        addToCart.click();

        Thread.sleep(10000);
        String actualWarning= driver.findElement(By.xpath("(//h2)[1]")).getText().trim();
        String expectedWarning="Product successfully added to your shopping cart";

        Assert.assertEquals(actualWarning,expectedWarning);

        WebElement pricePopUpElement=driver.findElement(By.id("layer_cart_product_price"));
        Double pricePopUp=Double.parseDouble(pricePopUpElement.getText().trim().substring(1));

        Assert.assertEquals(price,pricePopUp);

        String itemInCartWarning= driver.findElement(By.xpath("(//h2)[2]")).getText().trim();
        String expectedItemInCartWarning="There is 1 item in your cart.";

        Assert.assertEquals(itemInCartWarning,expectedItemInCartWarning);

        WebElement continueShopping= driver.findElement(By.xpath("//*[text() [contains(. , 'Continue shopping')]]"));
        continueShopping.click();

        WebElement cart=driver.findElement(By.className("shopping_cart"));

        String actualCartText=cart.getText().trim();
        String expectedCartText="Cart 1 Product";

        Assert.assertEquals(actualCartText,expectedCartText);

        WebElement secondProduct=driver.findElement(By.xpath("(//ul[@class='product_list grid row']//li)[6]"));
        actions.moveToElement(secondProduct).perform();

        WebElement addToCart2=driver.findElement(By.xpath("(//a[@title='Add to cart'])[2]"));
        addToCart2.click();
        Thread.sleep(10000);

        String itemInCartWarning2= driver.findElement(By.xpath("(//h2)[2]")).getText().trim();
        String expectedItemInCartWarning2="There are 2 items in your cart.";

        Assert.assertEquals(itemInCartWarning2,expectedItemInCartWarning2);

        WebElement totalProductsPriceElement=driver.findElement(By.xpath("//div[@class='layer_cart_row']//span[@class='ajax_block_products_total']"));
        Double totalProductsPrice=Double.parseDouble(totalProductsPriceElement.getText().trim().substring(1));
        WebElement secondPriceElement=driver.findElement(By.id("layer_cart_product_price"));
        Double secondPrice=Double.parseDouble(secondPriceElement.getText().trim().substring(1));
        Double expectedTotal=pricePopUp+secondPrice;
        DecimalFormat df = new DecimalFormat("0.##");
        Double expected=Double.parseDouble(df.format(expectedTotal));

        Assert.assertEquals(totalProductsPrice,expected);

        WebElement proceedCheckout=driver.findElement(By.xpath("//a[@title='Proceed to checkout']"));
        proceedCheckout.click();

        Thread.sleep(2000);
        WebElement deleteSecond=driver.findElement(By.xpath("(//a/i[@class='icon-trash'])[2]"));
        deleteSecond.click();
        Thread.sleep(5000);

        WebElement iconPlus=driver.findElement(By.xpath("//a//i[@class='icon-plus']"));
        iconPlus.click();
        Thread.sleep(5000);
        iconPlus.click();
        Thread.sleep(7000);

        WebElement unitPriceElement=driver.findElement(By.xpath("//span[@class='price']//span[@class='price']"));
        Double unitPrice=Double.parseDouble(unitPriceElement.getText().trim().substring(1));
        WebElement totalElement=driver.findElement(By.xpath("//td[@class='cart_total']//span"));
        Double total=Double.parseDouble(totalElement.getText().trim().substring(1));
        Double expectedDouble=Double.parseDouble(df.format((unitPrice*3)));
        Assert.assertEquals(total,expectedDouble);

        WebElement totalPriceContainer=driver.findElement(By.id("total_price_container"));
        Double totalPrice=Double.parseDouble(totalPriceContainer.getText().trim().substring(1));

        WebElement shippingElement=driver.findElement(By.id("total_shipping"));
        Double shipping=Double.parseDouble(shippingElement.getText().trim().substring(1));

        Double expectedCheckout=Double.parseDouble(df.format((total+shipping)));

        Assert.assertEquals(totalPrice,expectedCheckout);

    }
}
