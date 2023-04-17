package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.time.Duration;

public class LoginTest extends BaseTest {


    @Before
    public void setUp() {
        openBrowser("Chrome");
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        //implicitly wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //sending text to username field
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        //finding click button element
        driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
        //expected element
        String expectedElement = "Secure Area";
        //finding xpath for actual element
        String actualElement = driver.findElement(By.xpath("//h2[contains(text(),' Secure Area')]")).getText();
        Assert.assertEquals("Secure Area", expectedElement, actualElement);
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        //implicitly wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //sending text to the username field
        driver.findElement(By.id("username")).sendKeys("tomsmith1");
        //sending text to password field
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        //finding click button
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String expectedResult = "Your username is invalid!" +
                "\n×";

        //actual result
        String actualResult = driver.findElement(By.xpath("//div[@class='flash error']")).getText();
        Assert.assertEquals("User name is invalid!", expectedResult, actualResult);

    }

    @Test
    public void verifyThePasswordErrorMessage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //sending text to the username field
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        //sending text to password field
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword");
        //finding click element
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //expected result
        String expectedElement = "Your password is invalid!" +
                "\n×";

        //actual result
        String actualText = driver.findElement(By.xpath("//div[@class='flash error']")).getText();
        Assert.assertEquals("Password is invalid!", expectedElement, actualText);


    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
