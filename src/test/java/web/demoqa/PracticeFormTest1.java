package web.demoqa;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class PracticeFormTest1 extends BaseTest{

    @Test
    public void successfulWithAllParamsTest() {
        String firstName = "Klaus";
        String lastName = "Meine";
        String userEmail = "klausMeine@music.com";
        String userNumber = "7999999666";

        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/automation-practice-form");

        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("userEmail")).sendKeys(userEmail);
        driver.findElement(By.xpath("//*[@id='genterWrapper']/div[2]/div[1]/label")).click();
        driver.findElement(By.id("userNumber")).sendKeys(userNumber);
        WebElement dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));

        dateOfBirth.click();
        Select month = new Select(driver.findElement(By.xpath("//*[@id='dateOfBirth']/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select")));
        month.selectByValue("4");

        Select year =  new Select(driver.findElement(By.xpath("//*[@id='dateOfBirth']/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select")));
        year.selectByValue("1948");
        driver.findElement(By.xpath("//*[@id='dateOfBirth']/div[2]/div[2]/div/div/div[2]/div[2]/div[5]/div[3]")).click();

        WebElement subject = driver.findElement(By.id("subjectsInput"));
               subject.sendKeys("English");
               subject.sendKeys(Keys.ENTER);
    }
}
