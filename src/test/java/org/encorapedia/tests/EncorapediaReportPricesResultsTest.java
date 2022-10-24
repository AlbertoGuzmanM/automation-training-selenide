package org.encorapedia.tests;

import com.codeborne.selenide.Configuration;
import org.jetbrains.annotations.NotNull;
import org.encorapedia.pages.EncorapediaHomePage;
import org.encorapedia.pages.EncorapediaReportsPage;
import org.encorapedia.pages.EncorapediaResultsPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class EncorapediaReportPricesResultsTest {

    private String URL = "http://localhost:8080/choice-selenium-testing/index.html";

    @BeforeTest
    public void setup() throws Exception {
        //mvn clean install -Dselenide.browser="edge" to support different browsers
        open(URL);
    }

    @AfterTest
    public void tearDown(){
        closeWebDriver();
    }
    @Test
    public void validatingReportPricesResults() throws InterruptedException {
        navigateToReports();
//        // from date: today + 2 days
        LocalDate fromLocalDate = LocalDate.now().plusDays(2);
        Date fromDate = Date.from(fromLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        // to date: fromDate + 5 days
        LocalDate toLocalDate = fromLocalDate.plusDays(5);
        Date toDate = Date.from(toLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        EncorapediaReportsPage objEncorapediaReportsPage = new EncorapediaReportsPage();
        objEncorapediaReportsPage.setFromDate(fromDate);
        objEncorapediaReportsPage.setToDate(toDate);
        objEncorapediaReportsPage.selectReportByVisibleText("Prices report");
        objEncorapediaReportsPage.showTypeReport("full");
        objEncorapediaReportsPage.clickViewReport();
        EncorapediaResultsPage objEncorapediaResultsPage = new EncorapediaResultsPage();
        objEncorapediaResultsPage.selectSortPricesBy("Price descending");
        List<Integer> pricesList = objEncorapediaResultsPage.getPrices();
        Assert.assertTrue(isDescendingSorted(pricesList));
        int totalPrice = objEncorapediaResultsPage.getTotalPrice();
        Assert.assertEquals(totalPrice, getTotalFromPriceList(pricesList));
    }

    private void navigateToReports() throws InterruptedException {
        EncorapediaHomePage objEncorapediaHomePage = new EncorapediaHomePage();
        objEncorapediaHomePage.clickOnReports();
    }

    private int getTotalFromPriceList(@NotNull List<Integer> list) {
        return list.stream().reduce(0, Integer::sum);
    }

    private boolean isDescendingSorted(@NotNull List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) < list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
