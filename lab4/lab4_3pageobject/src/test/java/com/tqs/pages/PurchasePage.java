package com.tqs.pages;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@ExtendWith(SeleniumJupiter.class)
public class PurchasePage {
    private WebDriver driver;

    @FindBy(id = "inputName")
    private WebElement inputName;

    @FindBy(id = "address")
    private WebElement address;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(id = "zipCode")
    private WebElement zipCode;

    @FindBy(id = "creditCardNumber")
    private WebElement creditCardNumber;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCard;

    @FindBy(id = "rememberMe")
    private WebElement rememberMeCheckbox;

    @FindBy(css = ".btn-primary")
    private WebElement purchaseButton;

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillOutPurchaseForm(String name, String addr, String cty, String st, String zip, String ccNum, String ccName, boolean rememberMe) {
        inputName.sendKeys(name);
        address.sendKeys(addr);
        city.sendKeys(cty);
        state.sendKeys(st);
        zipCode.sendKeys(zip);
        creditCardNumber.sendKeys(ccNum);
        nameOnCard.sendKeys(ccName);
        if (rememberMe) {
            rememberMeCheckbox.click();
        }
    }

    public void purchaseFlight() {
        purchaseButton.click();
    }
}
