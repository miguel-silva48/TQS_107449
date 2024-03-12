package com.tqs.pages;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@ExtendWith(SeleniumJupiter.class)
public class HomePage {
	
	private final String URL = "https://blazedemo.com/";

	private WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;

		driver.get(URL);
		PageFactory.initElements(driver, this);
	}

    public void chooseTravelandDestiny(String from, String to) {
        // Select origin from the 'fromPort' dropdown
        WebElement fromPortDropdown = driver.findElement(By.name("fromPort"));
        fromPortDropdown.findElement(By.xpath("//option[. = '" + from + "']")).click();

        // Select destination from the 'toPort' dropdown
        WebElement toPortDropdown = driver.findElement(By.name("toPort"));
        toPortDropdown.findElement(By.xpath("//option[. = '" + to + "']")).click();
    }

    public void proceedToFlights() {
        // Click the 'Find Flights' button
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }
}
