package com.tqs;

import static io.github.bonigarcia.seljup.BrowserType.EDGE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.EnabledIfDockerAvailable;
import io.github.bonigarcia.seljup.SeleniumJupiter;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

@EnabledIfDockerAvailable
@ExtendWith(SeleniumJupiter.class)
class DockerEdgeTest {

    @Test
    void testEdge(@DockerBrowser(type = EDGE) WebDriver driver) {
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(980, 1193));
        assertThat(driver.getTitle()).isEqualTo("BlazeDemo");

        //fill fromPort and toPort
        driver.findElement(By.name("fromPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = 'Mexico City']")).click();
        }

        driver.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = 'London']")).click();
        }
        //click Find Flights
        driver.findElement(By.cssSelector(".btn-primary")).click();
        assertThat(driver.getTitle()).isEqualTo("BlazeDemo - reserve");

        //click Choose This Flight
        driver.findElement(By.cssSelector("tr:nth-child(5) .btn")).click();
        assertThat(driver.getTitle()).isEqualTo("BlazeDemo Purchase");

        //fill form data
        driver.findElement(By.id("inputName")).sendKeys("Johny");
        driver.findElement(By.id("address")).sendKeys("123 Rua qualquer");
        driver.findElement(By.id("city")).sendKeys("Cidade");
        driver.findElement(By.id("zipCode")).sendKeys("02020");
        driver.findElement(By.id("creditCardNumber")).sendKeys("123456");
        driver.findElement(By.id("nameOnCard")).sendKeys("Johny smith");
        //click Purchase Flight
        driver.findElement(By.cssSelector(".btn-primary")).click();

        //check purchase confirmation
        assertThat(driver.getTitle()).isEqualTo("BlazeDemo Confirmation");
    }
}