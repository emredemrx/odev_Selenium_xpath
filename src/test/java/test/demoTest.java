package test;

import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @Test @Description("Test - Click Button")
    public void buttonClick (){
        driver.get("https://demoqa.com/elements");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        WebElement btnClick = driver.findElement(By.xpath("//li[.='Buttons']"));
        btnClick.click();

        WebElement btnClick2 = driver.findElement(By.xpath("//button[text()='Click Me']"));
        btnClick2.click();

        WebElement text = driver.findElement(By.xpath("//p[@id='dynamicClickMessage']"));
        Assert.assertTrue(text.isDisplayed());
        Assert.assertEquals(text.getText(),"You have done a dynamic click");
    }

    @Test @Description("Test - ADD RECORD")
    public void addRecord (){
        driver.get("https://demoqa.com/webtables");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        WebElement addClick = driver.findElement(By.xpath("//button[@id='addNewRecordButton']"));
        addClick.click();
        WebElement firstNameInput = driver.findElement(By.xpath("//input[@id='firstName']"));
        firstNameInput.sendKeys("Emre");
        WebElement lastNameInput = driver.findElement(By.xpath("//input[@id='lastName']"));
        lastNameInput.sendKeys("Demir");
        WebElement eMailInput = driver.findElement(By.xpath("//input[@id='userEmail']"));
        eMailInput.sendKeys("emre@emre.com");
        WebElement ageInput = driver.findElement(By.xpath("//input[@id='age']"));
        ageInput.sendKeys("30");
        WebElement SalaryInput = driver.findElement(By.xpath("//input[@id='salary']"));
        SalaryInput.sendKeys("40000");
        WebElement departmentInput = driver.findElement(By.xpath("//input[@id='department']"));
        departmentInput.sendKeys("İK");
        WebElement submitClick = driver.findElement(By.xpath("//button[@id='submit']"));
        submitClick.click();
        WebElement updateClick = driver.findElement(By.xpath("//div[@class='rt-tbody']/div[4]//span[@class='mr-2']"));
        updateClick.click();
        WebElement SalaryUpdateInput = driver.findElement(By.xpath("//input[@id='salary']"));
        SalaryUpdateInput.clear();
        SalaryUpdateInput.sendKeys("60000");
        WebElement departmentUpdateInput = driver.findElement(By.xpath("//input[@id='department']"));
        departmentUpdateInput.clear();
        departmentUpdateInput.sendKeys("insan kaynakları");
        WebElement submitUpdateClick = driver.findElement(By.xpath("//button[@id='submit']"));
        submitUpdateClick.click();
        WebElement SalaryUpdateControl = driver.findElement(By.xpath("//div[@class='rt-tr-group']/following::div[27]"));
        Assert.assertTrue(SalaryUpdateControl.isDisplayed());
        Assert.assertEquals(SalaryUpdateControl.getText(),"60000");
        WebElement departmentUpdateControl = driver.findElement(By.xpath("//div[@class='rt-tr-group']/following::div[28]"));
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
