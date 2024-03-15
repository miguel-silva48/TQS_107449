package com.tqs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BuyTripSteps {

    private WebDriver driver;

    @Given("the customer is at {string}")
    public void setURL(String url) {
        driver = WebDriverManager.chromedriver().create();
        driver.get(url);
    }

    @Given("the customer is on the {string} page")
    public void checkCurrentPage(String page) {
        assertThat(driver.getTitle().toLowerCase()).contains(page.toLowerCase());
    }

    private void selectLocation(String dropdownName, String location) {
        WebElement dropdown = driver.findElement(By.name(dropdownName));
        dropdown.click();
        WebElement option = dropdown.findElement(By.xpath("//option[. = '" + location + "']"));
        option.click();
    }

    @When("the customer selects the origin as {string}")
    public void selectOrigin(String origin) {
        selectLocation("fromPort", origin);
    }

    @When("the customer selects the destination as {string}")
    public void selectDestination(String destination) {
        selectLocation("toPort", destination);
    }

    @When("the customer clicks on the {string} button")
    public void clickButton(String buttonText) {
        driver.findElement(By.cssSelector("input[value='" + buttonText + "']")).click();
    }

    @When("the customer fills out the form with valid data")
    public void fillForm() {
        driver.findElement(By.id("inputName")).sendKeys("Johny");
        driver.findElement(By.id("address")).sendKeys("123 Rua qualquer");
        driver.findElement(By.id("city")).sendKeys("Cidade");
        driver.findElement(By.id("zipCode")).sendKeys("02020");
        driver.findElement(By.id("creditCardNumber")).sendKeys("123456");
        driver.findElement(By.id("nameOnCard")).sendKeys("Johny smith");
    }

    @Then("the customer should be redirected to the {string} page")
    public void checkRedirectedPage(String page) {
        assertThat(driver.getTitle().toLowerCase()).contains(page.toLowerCase());
    }

    @Then("should see a list of flights from {string} to {string}")
    public void checkFlights(String origin, String destination) {
        //check the origin and destination in the h3 tag
        WebElement h3_title = driver.findElement(By.cssSelector("h3"));
        assertThat(h3_title.getText()).contains(origin + " to " + destination);
        //check if there is a list of flights
        List<WebElement> flights = driver.findElements(By.cssSelector("tr"));
        assertThat(flights.size()).isGreaterThan(0);
    }
}