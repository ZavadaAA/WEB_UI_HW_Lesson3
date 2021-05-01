package Lesson_3_HW;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class DiaryCreatePost {
    private static WebDriver driver;
    private static final String LOGIN_PAGE_URL = "https://diary.ru/user/login";

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        login();

        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[contains(@title,'Новая запись')]")).click();

        driver.findElement(By.name("BlogsPosts[title]")).sendKeys("New post");

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'message_ifr')]")));
        driver.findElement(By.xpath("//body")).sendKeys("My new post");

        driver.switchTo().defaultContent();

        driver.findElement(By.xpath("//input[contains(@name,'rewrite')]")).click();
        Thread.sleep(10000);

        driver.quit();
    }

    private static void login() throws InterruptedException {
            driver.get(LOGIN_PAGE_URL);
            driver.findElement(By.id("loginform-username")).sendKeys("azavada");
            driver.findElement(By.id("loginform-password")).sendKeys("zavada");
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'reCAPTCHA')]")));
            driver.findElement(By.xpath("//body")).click();
            driver.switchTo().defaultContent();
            Thread.sleep(10000);
            driver.findElement(By.id("login_btn")).click();
    }
}