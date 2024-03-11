package com.tqs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class HelloWorldBrowserJupiterRefactorTest {

    @Test
    public void testWithChrome(ChromeDriver driver) {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertTrue(driver.getTitle().contains("Selenium WebDriver"));
    }

    @Test
    public void testWithFirefox(FirefoxDriver driver) {
        driver.get("http://www.seleniumhq.org/");
        assertTrue(driver.getTitle().startsWith("Selenium"));
    }

}