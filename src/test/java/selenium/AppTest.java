package selenium;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.out.println("Iniciando configuración...");
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");

        URL url;        
        try {
                url = new URL("http://localhost:4444");
                driver = new RemoteWebDriver(url, options);
        } catch (MalformedURLException e) {
                e.printStackTrace();
        }

        //driver = new ChromeDriver(options);
        //driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void compraAutomationPractice() {
        driver.navigate().to("http://automationpractice.com/index.php");

        System.out.println("click a producto");
        String pathId = "/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]/div/div[2]/h5/a";
        driver.findElement(By.xpath(pathId)).click();

        System.out.println("agregando a carro");
        WebDriverWait waitTemp = new WebDriverWait(driver, 20);
        pathId = "/html/body/div/div[2]/div/div[3]/div/div/div/div[4]/form/div/div[3]/div/p/button/span";
        WebElement element = waitTemp.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(pathId))));
        element.click();

        System.out.println("haciendo checkout");
        pathId = "/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[4]/a/span";
        element = waitTemp.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(pathId))));
        element.click();

        System.out.println("haciendo checkout 2");
        pathId = "/html/body/div/div[2]/div/div[3]/div/p[2]/a[1]/span";
        element = waitTemp.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(pathId))));
        element.click();    

        element = waitTemp.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("email_create"))));
        Double randmon_email = Math.random();
        String correo = "test" + randmon_email + "@test.cl";
        element.sendKeys(correo);

        WebElement submitCreate = driver.findElement(By.id("SubmitCreate"));
        submitCreate.click();


        // registrarse
        WebDriverWait wait2 = new WebDriverWait(driver, 20);
        String customer_firstname = "test nombre";
        //wait2.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("customer_firstname"))));
        wait2.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_firstname")));
        driver.findElement(By.id("customer_firstname")).sendKeys(customer_firstname);

        element = driver.findElement(By.id("id_gender1"));
        element.click();

        String customer_lastname = "test apellido";
        element = driver.findElement(By.id("customer_lastname"));
        element.sendKeys(customer_lastname);

        String passwd = "oass12Wd__dwW";
        element = driver.findElement(By.id("passwd"));
        element.sendKeys(passwd);

        Select selement = new Select(driver.findElement(By.id("days")));
        selement.selectByValue("1");

        selement = new Select(driver.findElement(By.id("months")));
        selement.selectByValue("1");

        selement = new Select(driver.findElement(By.id("years")));
        selement.selectByValue("1982");

        String customer_company = "test company";
        element = driver.findElement(By.id("company"));
        element.sendKeys(customer_company);

        String customer_adress = "direccion 123, Santiago, Chile";
        element = driver.findElement(By.id("address1"));
        element.sendKeys(customer_adress);

        String customer_adress2 = "123";
        element = driver.findElement(By.id("address2"));
        element.sendKeys(customer_adress2);

        String customer_city = "test city";
        element = driver.findElement(By.id("city"));
        element.sendKeys(customer_city);

        String state = "Georgia";
        selement = new Select(driver.findElement(By.id("id_state")));
        selement.selectByVisibleText(state);

        String customer_postcode = "00000";
        element = driver.findElement(By.id("postcode"));
        element.sendKeys(customer_postcode);

        String customer_additional = "test";
        element = driver.findElement(By.id("other"));
        element.sendKeys(customer_additional);

        String customer_phone = "56966667777";
        element = driver.findElement(By.id("phone"));
        element.sendKeys(customer_phone);

        String customer_phone2 = "0987654321";
        element = driver.findElement(By.id("phone_mobile"));
        element.sendKeys(customer_phone2);

        String customer_alias = "test alias";
        element = driver.findElement(By.id("alias"));
        element.sendKeys(customer_alias);

        element = driver.findElement(By.id("submitAccount"));
        element.click();

        element = driver.findElement(By.cssSelector(".address_firstname"));
        assertEquals(element.getText(), customer_firstname +" "+ customer_lastname);

        element = driver.findElement(By.cssSelector(".address_company"));
        assertEquals(element.getText(), customer_company);

        element = driver.findElement(By.cssSelector(".address_address1"));
        assertEquals(element.getText(), customer_adress + " " + customer_adress2);

        element = driver.findElement(By.cssSelector(".address_city"));
        assertEquals(element.getText(), customer_city + ", " + state + " " + customer_postcode);

        element = driver.findElement(By.cssSelector(".address_country_name"));
        assertEquals(element.getText(), "United States");

        element = driver.findElement(By.cssSelector(".address_phone"));
        assertEquals(element.getText(), customer_phone);

        element = driver.findElement(By.cssSelector(".address_phone_mobile"));
        assertEquals(element.getText(), customer_phone2);

        element = driver.findElement(By.name("processAddress"));
        element.click();

        element = driver.findElement(By.id("cgv"));
        element.click();

        element = driver.findElement(By.name("processCarrier"));
        element.click();

        element = driver.findElement(By.cssSelector(".bankwire"));
        element.click();

        element = driver.findElement(By.id("cart_navigation"));
        element.click();

        //wait2.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cheque-indent")));

        driver.close();
        System.out.println("Fin test.");
    }
}

