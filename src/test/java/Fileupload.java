import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public  class Fileupload {
    static WebDriver driver;

@Test
                                     // for chrome
    public void setUp() throws  IOException,TimeoutException {
    try {
        System.setProperty("webdriver.chrome.driver", "/home/knoldus/Downloads/FileUploading/.idea/resources/chromedriver");
    }
    catch (WebDriverException e)
    {
        System.out.println(e.getMessage());
    }
    driver = new ChromeDriver();
    driver.get("https://tus.io/demo.html");

    WebElement ChooseButton = driver.findElement(By.cssSelector("input#js-file-input"));
    ChooseButton.sendKeys("//home//knoldus//code.txt");
    driver.manage().window().setSize(new Dimension(375, 812));
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

}
        @Test
        public void SetupFirefox() throws IOException, TimeoutException {
         try {
             System.setProperty("webdriver.chrome.driver", "/home/knoldus/Downloads/FileUploading/.idea/resources/geckodriver");
         }
         catch (NoSuchElementException e)
         {
             System.out.println(e.getMessage());
         }
        driver = new FirefoxDriver();
        driver.get("https://tus.io/demo.html");
        WebElement ChooseButton= driver.findElement(By.cssSelector("input#js-file-input"));
        ChooseButton.sendKeys("//home//knoldus//code.txt");
        driver.manage().window().setSize(new Dimension(375, 812));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void SetupHeadless()
    {
        System.setProperty("webdriver.chrome.driver", "/home/knoldus/Downloads/FileUploading/.idea/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver= new ChromeDriver(options);
        driver.get("https://tus.io/demo.html");
        WebElement Choose= driver.findElement(By.cssSelector("input#js-file-input"));
        Choose.sendKeys("//home//knoldus//code.txt");
        driver.manage().window().setSize(new Dimension(375, 812));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult result) throws IOException {
        if(ITestResult.FAILURE==result.getStatus()){
            try{
                TakesScreenshot ts=(TakesScreenshot)driver;
                File source=ts.getScreenshotAs(OutputType.FILE);
                try{
                    FileHandler.copy(source, new File("screenshot"+result.getName()+".jpeg"));
                    System.out.println("Screenshot taken");
                }
                catch (Exception e)
                {
                    System.out.println("Exception while taking screenshot "+e.getMessage());
                }
            }
            catch (Exception e)
            {
                System.out.println("Exception while taking screenshot "+e.getMessage());
            }
        }
    }

}