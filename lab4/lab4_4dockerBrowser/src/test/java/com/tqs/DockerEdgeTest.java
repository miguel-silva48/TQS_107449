package com.tqs;

import static io.github.bonigarcia.seljup.BrowserType.EDGE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.EnabledIfDockerAvailable;
import io.github.bonigarcia.seljup.SeleniumJupiter;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@EnabledIfDockerAvailable
@ExtendWith(SeleniumJupiter.class)
class DockerEdgeTest {

    @Test
    void testEdge(@DockerBrowser(type = EDGE) WebDriver driver) {
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