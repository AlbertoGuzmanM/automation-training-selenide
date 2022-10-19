package org.encorapedia.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;

public class EncorapediaResultsPage extends EncorapediaBasePage {

    private SelenideElement sortPricesBy = $(By.id("sort"));
    private ElementsCollection tableRows = $$(By.xpath("//*[@id='results']/table/tbody/tr"));
    private SelenideElement totalPriceSE = $(By.id("price-sum"));

    public void selectSortPricesBy(String sortBy) {
        sortPricesBy.shouldBe(visible, Duration.ofSeconds(10));
        sortPricesBy.selectOption(sortBy);
    }

    public List<Integer> getPrices() {
        tableRows.shouldBe(sizeGreaterThan(0));
        List<Integer> pricesList = new ArrayList<>();
        for (int i = 0; i < tableRows.size(); i++) {
            SelenideElement priceSE = $(By.xpath("//*[@id='results']/table/tbody/tr[" + (i + 1) + "]/td[4]"));
            priceSE.shouldBe(visible, Duration.ofSeconds(10));
            String price = priceSE.getText().replace("$", "");
            pricesList.add(Integer.parseInt(price));
        }
        return pricesList;
    }

    public int getTotalPrice() {
        totalPriceSE.shouldBe(visible, Duration.ofSeconds(10));
        return Integer.parseInt(totalPriceSE.getText().replace("$", ""));
    }

}
