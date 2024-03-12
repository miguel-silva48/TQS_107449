package com.tqs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.tqs.pages.HomePage;
import com.tqs.pages.PurchasePage;
import com.tqs.pages.ReservePage;

public class test {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void tripBuyTest() {

        HomePage homePage = new HomePage(driver);
        homePage.chooseTravelandDestiny("Mexico City", "London");
        homePage.proceedToFlights();

        ReservePage reservePage = new ReservePage(driver);
        reservePage.selectFlight(5);

        PurchasePage purchasePage = new PurchasePage(driver);
        purchasePage.fillOutPurchaseForm("Johny", "123 Rua qualquer", "Cidade", "Estado", "02020", "123456", "Johny Smith", true);
        purchasePage.purchaseFlight();

        // Nao criei confirmationPage pois não há interação com a página, apenas verificação do título
        assertEquals("BlazeDemo Confirmation", driver.getTitle());
    }
}
