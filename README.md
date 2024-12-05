# Selenium Automation Project

This project automates the process of logging in, adding a product to the cart, and completing a checkout on [Automation Exercise](https://automationexercise.com). It uses **Java**, **Selenium WebDriver**, and the **ChromeDriver** to interact with the web application.

## Features
- Login functionality with credentials.
- Adding a product to the cart.
- Navigating through the cart and completing a checkout process.
- Filling out payment details to simulate an order placement.

## Prerequisites
Before running the project, ensure you have the following installed on your system:
1. **Java Development Kit (JDK)** (version 11 or later).
2. **Maven** for dependency management (optional but recommended).
3. **Chrome Browser** (latest version recommended).
4. **ChromeDriver** matching your Chrome browser version.

## Installation and Setup

### Clone the Repository
Clone the repository using:
```base
   git clone https://github.com/your-username/selenium-automation-project.git
   cd selenium-automation-project
```
## Download ChromeDriver

1. **Download the Correct Version**  
   - Visit the official [ChromeDriver download page]( https://sites.google.com/chromium.org/driver/).
   - Select the version that matches your Chrome browser version and operating system.

2. **Place ChromeDriver in Your Project**  
   - After downloading, locate the `chromedriver.exe` file.  
   - Move or copy it to the `src/test/resources/` directory of your project.  
   - Ensure the directory structure looks like this:  
     ```
     src/
       test/
         resources/
           chromedriver.exe
     ```

## Add Dependencies

If you are using Maven, include the following dependencies in your `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.6.0</version>
    </dependency>
</dependencies>
```
## Usage

### Running the Test

1. Navigate to the project directory and compile the code:

   ```bash
   javac -d bin src/main/java/Test.java
   ```

   ```base
   java -cp bin Test
   ```
## Credentials

Replace the following placeholder credentials in the `Test.java` file with valid ones:

```java
getEmail.sendKeys("sachi@test.com");
getPassword.sendKeys("12345");
```

## Expected Behavior
- The script will log in, add a product to the cart, and proceed to checkout.
- Payment details will be autofilled, and the order will be completed.


# Error Handling

Handles exceptions such as:
- `NoSuchElementException` for missing elements.
- `TimeoutException` for slow responses.
- `InterruptedException` during thread sleep.

# Troubleshooting

- **ChromeDriver version mismatch**: Ensure ChromeDriver matches your installed Chrome version.
- **Timeout issues**: Check your internet connection or increase the timeout duration in `WebDriverWait`.
- **Element not interactable**: Verify the element locators (e.g., XPath).

# Contributing

Contributions are welcome! Please fork the repository, create a new branch, and submit a pull request.

# License

This project is licensed under the MIT License.

# Author

Sachidanand Sahu





