package tqs.project.storebackend.SeleniumTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;


public class StoreTests {
    private WebDriver driver;
    

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/home/pedrocoelho5/Desktop/Driver/chromedriver");
        driver = new ChromeDriver();
        
    }

    @AfterEach
    public void tearDown() {
      driver.quit();
    }
    
    @Test
    public void validate_Login() {
        driver.get("http://localhost:3000/");
        WebElement txtUsername = driver.findElement(By.name("user"));
        txtUsername.sendKeys("user");
        WebElement txtPass = driver.findElement(By.name("pass"));
        txtPass.sendKeys("user");
        WebElement btnLogin = driver.findElement(By.name("btnLogin"));
        btnLogin.click();
        String expectedTitle = driver.getTitle();
        String title = "Store";
        assertEquals(expectedTitle,title );
    }

    @Test
    public void view_Sneackers() {
        driver.get("http://localhost:3000/app");
        WebElement card_Sneacker = driver.findElement(By.name("productToBuy"));
        card_Sneacker.click();
        String expectedTitle = driver.getTitle();
        String title = "Product";
        assertEquals(expectedTitle,title);
    }

    @Test
    public void buy_Sneackers() {
        driver.get("http://localhost:3000/app");

        WebElement card_Sneacker = driver.findElement(By.name("productToBuy"));
        card_Sneacker.click();

        WebElement btn_buy = driver.findElement(By.name("buy_now"));
        btn_buy.click();

        WebElement inputName = driver.findElement(By.name("name"));
        inputName.sendKeys("user");
        WebElement inputPhone = driver.findElement(By.name("phone"));
        inputPhone.sendKeys("12345");
        WebElement inputEmail = driver.findElement(By.name("email"));
        inputEmail.sendKeys("mail@ua.pt");
        WebElement inputAddress = driver.findElement(By.name("address"));
        inputAddress.sendKeys("Rua dos Correios");
        WebElement inputCreditCard = driver.findElement(By.name("creditCard"));
        inputCreditCard.sendKeys("99999");
        WebElement selectAcp = driver.findElement(By.name("selectACP"));
        Select select = new Select(selectAcp);
        select.selectByIndex(1);

        WebElement submitButton = driver.findElement(By.name("submitBtn"));
        submitButton.click();

        Alert simpleAlert = driver.switchTo().alert();
        simpleAlert.accept();

        String expectedTitle = driver.getTitle();
        String title = "Store";
        assertEquals(expectedTitle,title);
    }

}
