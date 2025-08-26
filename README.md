# BrowserStack Demo Automation Framework

A robust TestNG + Selenium WebDriver automation framework for testing the BrowserStack demo e-commerce website. Built with Page Object Model (POM) design pattern and Maven build management.

## 🚀 Features

- **Page Object Model**: Clean separation of page logic and test cases
- **TestNG Framework**: Advanced test execution and reporting capabilities
- **Maven Build Management**: Easy dependency management and build automation
- **Chrome Browser Automation**: Focused on Chrome browser testing
- **Screenshot on Failure**: Automatic screenshots for failed tests
- **Explicit Waits**: Robust element synchronization
- **Configurable Properties**: Externalized configuration management

## 📁 Project Structure

```
bstackdemo-test-project/
├── src/
│   ├── main/java/com/bstackdemo/
│   │   ├── base/BasePage.java          # Base page class with common methods
│   │   ├── pages/                      # Page object classes
│   │   │   ├── LoginPage.java
│   │   │   ├── HomePage.java
│   │   │   └── CheckoutPage.java
│   │   └── utils/ConfigReader.java     # Configuration properties reader
│   └── test/java/com/bstackdemo/
│       ├── tests/E2ETest.java          # Test cases
│       └── testbase/TestBase.java      # Test base class with setup/teardown
├── src/main/resources/
│   └── config.properties               # Configuration settings
├── src/test/resources/
│   └── testng.xml                      # TestNG test suite configuration
├── pom.xml                             # Maven build configuration
└── README.md
```

## 🛠️ Prerequisites

- Java JDK 11 or higher
- Maven 3.6.0 or higher
- Chrome Browser (latest version)
- Internet connection

## ⚙️ Installation

1. **Clone the repository**:
   ```bash
   git clone <your-repository-url>
   cd bstackdemo-test-project
   ```

2. **Verify Java installation**:
   ```bash
   java -version
   ```

3. **Verify Maven installation**:
   ```bash
   mvn -version
   ```

## 🎯 Configuration

Update `src/main/resources/config.properties` for your environment:

```properties
# Browser Configuration
browser=chrome

# Timeouts
implicit.wait=10

# URLs
base.url=https://bstackdemo.com
login.url=https://bstackdemo.com/signin

# Test Data
username=demouser
password=testingisfun99
```

## 🧪 Test Cases

The framework includes the following test scenarios:

1. **testSuccessfulLogin()**: Validates user login functionality
2. **testAddProductToCart()**: Tests adding products to shopping cart
3. **testCheckoutProcess()**: Verifies checkout process with shipping details
4. **testEndToEndFlow()**: Complete end-to-end user journey test

## 🚦 Execution

### Run all tests:
```bash
mvn clean test
```

### Run specific test method:
```bash
mvn test -Dtest=E2ETest#testSuccessfulLogin
```

### Run with TestNG XML:
```bash
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Generate surefire reports:
```bash
mvn surefire-report:report
```

## 📊 Test Reports

After test execution, reports are available in:
- **Console output**: Immediate test results
- **target/surefire-reports**: Detailed HTML and XML reports
- **screenshots/**: Automatic screenshots for failed tests (created automatically)

## 🏗️ Page Objects

### LoginPage
- Handles user authentication
- Elements: usernameInput, passwordInput, loginButton
- Methods: login(), getLoggedInUsername()

### HomePage
- Manages product browsing and cart operations
- Elements: productItems, addToCartButtons, buyNowButton
- Methods: addProductToCart(), clickBuyNow(), getCartQuantity()

### CheckoutPage
- Processes order checkout
- Elements: form fields, continueButton, submitButton
- Methods: fillShippingDetails(), clickSubmit()

## 🔧 Customization

### Adding New Tests:
1. Create new test methods in `E2ETest.java`
2. Follow TestNG annotations (`@Test`, `@BeforeMethod`, etc.)
3. Use existing page objects or create new ones

### Adding New Pages:
1. Create new page class in `pages/` package
2. Extend `BasePage.java`
3. Define WebElements using `@FindBy` annotations
4. Add business logic methods

### Modifying Locators:
Update element locators in respective page classes if website structure changes.

## 🐛 Troubleshooting

### Common Issues:

1. **Element not found errors**:
   - Check if locators need updating
   - Verify page load completions

2. **Timeout exceptions**:
   - Increase wait times in `config.properties`
   - Check internet connection

3. **Browser not launching**:
   - Verify Chrome installation
   - Check WebDriverManager compatibility

4. **Test failures**:
   - Check screenshots in `screenshots/` folder
   - Review surefire reports for details

### Debug Mode:
Add debug logging by updating surefire plugin in `pom.xml`:
```xml
<configuration>
    <systemPropertyVariables>
        <org.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener>warn</org.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener>
    </systemPropertyVariables>
</configuration>
```

## 📝 Best Practices

1. **One Assertion per Test**: Keep tests focused on single functionality
2. **Independent Tests**: Each test should run independently
3. **Proper Waiting**: Use explicit waits instead of Thread.sleep()
4. **Meaningful Names**: Use descriptive test and method names
5. **Regular Updates**: Keep locators and dependencies updated

## 🙏 Acknowledgments

- [BrowserStack](https://bstackdemo.com/) for providing the demo site
- [TestNG](https://testng.org/) testing framework
- [Selenium WebDriver](https://www.selenium.dev/) browser automation
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) for driver management

---
