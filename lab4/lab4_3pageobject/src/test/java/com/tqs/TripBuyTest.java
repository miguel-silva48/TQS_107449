package com.tqs;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tqs.pages.HomePage;
import com.tqs.pages.PurchasePage;
import com.tqs.pages.ReservePage;

@ExtendWith(SeleniumJupiter.class)
public class TripBuyTest {

    @Test
    public void tripBuyTest(ChromeDriver driver) {

        HomePage homePage = new HomePage(driver);
        assertEquals("BlazeDemo", driver.getTitle());
        homePage.chooseTravelandDestiny("Mexico City", "London");
        homePage.proceedToFlights();

        ReservePage reservePage = new ReservePage(driver);
        assertEquals("BlazeDemo - reserve", driver.getTitle());
        reservePage.selectFlight(5);

        PurchasePage purchasePage = new PurchasePage(driver);
        assertEquals("BlazeDemo Purchase", driver.getTitle());
        purchasePage.fillOutPurchaseForm("Johny", "123 Rua qualquer", "Cidade", "Estado", "02020", "123456", "Johny Smith", true);
        purchasePage.purchaseFlight();

        //* Nao criei confirmationPage pois não há interação com a página, apenas verificação do título
        assertEquals("BlazeDemo Confirmation", driver.getTitle());
    }
}
