package Lesson_3_HW;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;

public class DiaryLogin {
    private static WebDriver driver;
    private static final String LOGIN_PAGE_URL ="https://diary.ru";
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://diary.ru");
        Cookie sessionCookie = driver.manage().getCookieNamed("PHPSESSID");
        driver.manage().deleteCookie(sessionCookie);
        Cookie cookie = new Cookie("PHPSESSID", "0hjsbq9smq5t0ca6m5lnlr8snj");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
        Thread.sleep(50000);
        driver.close();
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