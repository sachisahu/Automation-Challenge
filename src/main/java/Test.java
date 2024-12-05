import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.*;

public class Test {


    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            // Set up Chrome options
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            // Set up Chrome WebDriver and maximize the browser window
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

            // Open the login page
            driver.get("https://automationexercise.com/login");

            // Locate email and password fields and enter login credentials
            WebElement getEmail = driver.findElement(By.name("email"));
            WebElement getPassword = driver.findElement(By.name("password"));
            getEmail.sendKeys("sachi@test.com");
            getPassword.sendKeys("12345");
            getPassword.submit();  // Submit login form

            // Locate the 'Add to Cart' button using XPath
            WebElement addToCartButton = driver.findElement(By.xpath("//a[@data-product-id='3' and @class='btn btn-default add-to-cart']"));

            // Wait for the 'Add to Cart' button to be clickable
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait2.until(ExpectedConditions.elementToBeClickable(addToCartButton));

            // Scroll into view to ensure the 'Add to Cart' button is not obstructed by other elements
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);

            // Optional: Wait for 1 second to ensure smooth scrolling
            Thread.sleep(1000);

            // Click the 'Add to Cart' button
            addToCartButton.click();

            // Wait for the 'View Cart' link to be clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement viewCartLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='text-center']/a[@href='/view_cart']")));

            // Scroll into view to ensure the 'View Cart' link is clickable
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewCartLink);

            // Click the 'View Cart' link
            viewCartLink.click();

            // Wait for the 'Proceed To Checkout' button to be clickable
            WebElement proceedToCheckoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btn btn-default check_out']")));

            // Scroll into view to ensure the 'Proceed To Checkout' button is clickable
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", proceedToCheckoutButton);

            // Click the 'Proceed To Checkout' button
            proceedToCheckoutButton.click();

            // Wait for the 'Place Order' button to be clickable
            WebElement placeOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/payment' and @class='btn btn-default check_out']")));

            // Scroll into view to ensure the 'Place Order' button is clickable
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrderButton);

            // Click the 'Place Order' button
            placeOrderButton.click();

            // Wait for the payment form fields to be visible and then fill them
            WebElement nameOnCardField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name_on_card")));
            WebElement cardNumberField = driver.findElement(By.name("card_number"));
            WebElement cvcField = driver.findElement(By.name("cvc"));
            WebElement expiryMonthField = driver.findElement(By.name("expiry_month"));
            WebElement expiryYearField = driver.findElement(By.name("expiry_year"));

            // Fill in the payment details (example values provided here)
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

            // Wait for the 'Pay and Confirm Order' button to be clickable
            WebElement payAndConfirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='form-control btn btn-primary submit-button' and @data-qa='pay-button']")));

            // Scroll into view to ensure the 'Pay and Confirm Order' button is clickable
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", payAndConfirmButton);

            // Click the 'Pay and Confirm Order' button to complete the order
            payAndConfirmButton.click();

            // Optionally, you can add some assertions or validation here to confirm successful checkout

        } catch (NoSuchElementException e) {
            System.err.println("Error: Element not found - " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
        } catch (TimeoutException e) {
            System.err.println("Error: Timeout while waiting for element - " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
        } catch (InterruptedException e) {
            System.err.println("Error: Thread interrupted - " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
        } finally {
            // Ensure the browser is closed regardless of success or failure
            //driver.quit();
        }
    }

}
