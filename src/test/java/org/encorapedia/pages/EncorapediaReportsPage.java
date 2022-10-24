package org.encorapedia.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.util.DateUtil;

import java.util.Date;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class EncorapediaReportsPage extends EncorapediaBasePage {

    private SelenideElement fromDate = $(By.id("fromDate"));
    private SelenideElement toDate = $(By.id("toDate"));
    private SelenideElement selectReport = $(By.id("selectedReport"));
    private SelenideElement showTypeReport = $(By.name("reportType"));
    private ElementsCollection viewReportButtons = $$(By.className("btn-primary"));
    private DateUtil dateUtil;

    public void setFromDate(Date date) {
        fromDate.shouldBe(visible);
        fromDate.setValue(dateUtil.formatDate(date));
    }

    public void setToDate(Date date) {
        toDate.shouldBe(visible);
        toDate.setValue(dateUtil.formatDate(date));
    }

    /**
     * This method selects the report to display based on the string received.
     * The Select options available:
     * "Prices report"
     * "Intenvory report" [sic]
     *
     * @param report
     */
    public void selectReportByVisibleText(String report) {
        selectReport.shouldBe(visible);
        selectReport.selectOption(report);
    }

    /**
     * This method selects how the report is going to show.
     * "full"
     * "condensed"
     *
     * @param reportType
     */
    public void showTypeReport(String reportType) {
        showTypeReport.shouldBe(visible);
        showTypeReport.selectRadio(reportType);
    }

    public void clickViewReport() {
        viewReportButtons.get(1).shouldBe(visible);
        viewReportButtons.get(1).click();
    }
}
