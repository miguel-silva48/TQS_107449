package com.tqs.pages;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@ExtendWith(SeleniumJupiter.class)
public class ReservePage {
    private WebDriver driver;

    @FindBy(css = "table tbody tr")
    private List<WebElement> flightRows;

    public ReservePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectFlight(int flightIndex) {
        // Verify if the flightIndex is valid
        if (flightIndex < 1 || flightIndex > flightRows.size()) {
            throw new IllegalArgumentException("Invalid flight index");
        }

        // Click the 'Choose This Flight' button for the specified flightIndex
        WebElement chooseFlightButton = driver.findElement(By.cssSelector("table tbody tr:nth-child(" + flightIndex + ") .btn"));
        chooseFlightButton.click();
    }
}

