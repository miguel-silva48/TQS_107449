package com.tqs;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

public class C_TripBuyTest {

    static final Logger log = getLogger(lookup().lookupClass());

    private WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }
    
    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void test() {
        //open
        driver.get("https://blazedemo.com/");
        //set window size
        driver.manage().window().setSize(new Dimension(980, 1193));
        //select fromPort
        driver.findElement(By.name("fromPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = 'Mexico City']")).click();
        }
        //select toPort
        driver.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = 'London']")).click();
        }
        //click Find Flights
        driver.findElement(By.cssSelector(".btn-primary")).click();
        //click Choose This Flight
        driver.findElement(By.cssSelector("tr:nth-child(5) .btn")).click();
        //type inputName
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("Johny");
        //type address
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("123 Rua qualquer");
        //type city
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("Cidade");
        //type zipCode
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys("02020");
        //type creditCardNumber
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys("123456");
        //type nameOnCard
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys("Johny smith");
        //click Purchase Flight
        driver.findElement(By.cssSelector(".btn-primary")).click();
        //check Page Title
        assertThat(driver.getTitle()).isEqualTo("BlazeDemo Confirmation");
    }
}