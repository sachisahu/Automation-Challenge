import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testCheckoutProcess() throws InterruptedException {
        try {
            // Open the login page
            driver.get("https://automationexercise.com/login");

            // Locate email and password fields and enter login credentials
            WebElement getEmail = driver.findElement(By.name("email"));
            WebElement getPassword = driver.findElement(By.name("password"));
            getEmail.sendKeys("sachi@test.com");
            getPassword.sendKeys("12345");
            getPassword.submit();  // Submit login form

            // Wait for and locate the 'Add to Cart' button and click it
            WebElement addToCartButton = driver.findElement(By.xpath("//a[@data-product-id='3' and @class='btn btn-default add-to-cart']"));
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait2.until(ExpectedConditions.elementToBeClickable(addToCartButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
            Thread.sleep(1000);  // Optional delay
            addToCartButton.click();

            // Proceed to View Cart
            WebElement viewCartLink = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='text-center']/a[@href='/view_cart']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewCartLink);
            viewCartLink.click();

            // Proceed to Checkout
            WebElement proceedToCheckoutButton = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btn btn-default check_out']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", proceedToCheckoutButton);
            proceedToCheckoutButton.click();

            // Fill in payment form (card details, name, etc.)
            WebElement placeOrderButton = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/payment' and @class='btn btn-default check_out']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrderButton);
            placeOrderButton.click();

            // Fill out the payment form with card details and name
            WebElement nameOnCardField = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.name("name_on_card")));
            WebElement cardNumberField = driver.findElement(By.name("card_number"));
            WebElement cvcField = driver.findElement(By.name("cvc"));
            WebElement expiryMonthField = driver.findElement(By.name("expiry_month"));
            WebElement expiryYearField = driver.findElement(By.name("expiry_year"));

            nameOnCardField.sendKeys("Sachidanand Sahu");
            Thread.sleep(1000);

            cardNumberField.sendKeys("4111111111111111");
            Thread.sleep(1000);
            cvcField.sendKeys("123");
            Thread.sleep(1000);
            expiryMonthField.sendKeys("12");
            Thread.sleep(1000);
            expiryYearField.sendKeys("2025");
            Thread.sleep(1000);

            // Confirm the payment
            WebElement payAndConfirmButton = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='form-control btn btn-primary submit-button' and @data-qa='pay-button']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", payAndConfirmButton);
            payAndConfirmButton.click();

            // Assert that payment was successful (you can check the success message, page title, or other indicators)
            // Here, we'll assert that the current URL should be something that indicates successful payment.
            wait2.until(ExpectedConditions.urlContains("payment_success"));
            Assert.assertTrue(driver.getCurrentUrl().contains("payment_success"), "Payment was not successful!");

        } catch (Exception e) {
            // If any error occurs, the test will fail
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after the test is complete
        if (driver != null) {
            driver.quit();
        }
    }
}
