package org.encorapedia.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class EncorapediaBasePage {

    private SelenideElement homeLink = $(By.linkText("Home"));
    private SelenideElement inventoryLink = $(By.linkText("Inventory"));
    private SelenideElement reportsLink = $(By.linkText("Reports"));

    public void clickOnReports() {
        reportsLink.click();
    }

    public void clickOnInventory() {
        inventoryLink.click();
    }

    public void clickOnHome() {
        homeLink.click();
    }

}
