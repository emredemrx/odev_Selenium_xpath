package org.test;
import com.beust.ah.A;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class demoTest {
    private WebDriver driver;
    private WebDriverWait wait ;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/java/drivers/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-certificate-errors");
        // options.addArguments("--incognito");  gizli sekmede işlem yapmak istediğimizde
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);

        //page load timeout
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        //imlicit wait
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //explicit wait
        //wait =new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Test @Description("Test - Click Button")
    public void buttonClick (){
        driver.get("https://demoqa.com/elements");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        WebElement btnClick = driver.findElement(By.cssSelector("#item-4"));
        btnClick.click();

        WebElement btnClick2 = driver.findElement(By.cssSelector(".col-md-6 div:nth-of-type(3) > .btn"));
        btnClick2.click();

        WebElement text = driver.findElement(By.cssSelector("#dynamicClickMessage"));
        Assert.assertTrue(text.isDisplayed());
        Assert.assertEquals(text.getText(),"You have done a dynamic click");
    }

    @Test @Description("Test - ADD RECORD")
    public void addRecord (){
        driver.get("https://demoqa.com/webtables");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        WebElement addClick = driver.findElement(By.cssSelector("#addNewRecordButton"));
        addClick.click();

        WebElement firstNameInput = driver.findElement(By.cssSelector("#firstName"));
        firstNameInput.sendKeys("Emre");
        WebElement lastNameInput = driver.findElement(By.cssSelector("#lastName"));
        lastNameInput.sendKeys("Demir");
        WebElement eMailInput = driver.findElement(By.cssSelector("#userEmail"));
        eMailInput.sendKeys("emre@emre.com");
        WebElement ageInput = driver.findElement(By.cssSelector("#age"));
        ageInput.sendKeys("30");
        WebElement SalaryInput = driver.findElement(By.cssSelector("#salary"));
        SalaryInput.sendKeys("40000");
        WebElement departmentInput = driver.findElement(By.cssSelector("#department"));
        departmentInput.sendKeys("İK");
        WebElement submitClick = driver.findElement(By.cssSelector("#submit"));
        submitClick.click();
        WebElement updateClick = driver.findElement(By.cssSelector(".rt-tbody > div:nth-of-type(4) .mr-2"));
        updateClick.click();
        WebElement SalaryUpdateInput = driver.findElement(By.cssSelector("#salary"));
        SalaryUpdateInput.clear();
        SalaryUpdateInput.sendKeys("60000");
        WebElement departmentUpdateInput = driver.findElement(By.cssSelector("#department"));
        departmentUpdateInput.clear();
        departmentUpdateInput.sendKeys("insan kaynakları");
        WebElement submitUpdateClick = driver.findElement(By.cssSelector("#submit"));
        submitUpdateClick.click();


        WebElement SalaryUpdateControl = driver.findElement(By.cssSelector(".rt-tbody > div:nth-of-type(4) div:nth-of-type(5)"));
        Assert.assertTrue(SalaryUpdateControl.isDisplayed());
        Assert.assertEquals(SalaryUpdateControl.getText(),"60000");

        WebElement departmentUpdateControl = driver.findElement(By.cssSelector(".rt-tbody > div:nth-of-type(4) div:nth-of-type(6)"));
        Assert.assertTrue(departmentUpdateControl.isDisplayed());
        Assert.assertEquals(departmentUpdateControl.getText(),"insan kaynakları");
    }


    @AfterMethod
    public void kapanis(){
        if (driver!= null){
            driver.quit();
        }
    }
}
