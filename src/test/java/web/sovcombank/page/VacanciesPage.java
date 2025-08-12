package web.sovcombank.page;

import api.common.TestUtils;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class VacanciesPage {

    public VacanciesPage() {
//        Configuration.pageLoadStrategy = "normal";
        $x(".//*[@class='vacancies__title']")
                .shouldHave(Condition.exactText(" Вакансии в Совкомбанке "));
        sleep(5000);
//        $(By.tagName("body")).shouldBe(Condition.exist);
//        com.codeborne.selenide.Selenide.executeJavaScript("return document.readyState").equals("complete");

    }

    public VacanciesPage openPage() {
//        open("https://people.sovcombank.ru/vacancies");
        open(TestUtils.getPageUrl());
        WebDriverRunner.getWebDriver().manage().window().maximize();
        return this;
    }

    public VacanciesPage selectCity(String city) {
        $$x(".//*[@class='multiselect ui-selector']").get(0).should(appear).click();
        $x(".//*[@class='multiselect__element']").$(byText(city)).click();
        return this;
    }

    public VacanciesPage selectCompany(String company) {
        $$x(".//*[@class='multiselect ui-selector']").get(1).should(appear).click();
        $x(".//*[@class='multiselect__element' and .//span[contains(text(), '" + company + "')]]")
                .should(appear).click();
        return this;
    }

    public VacanciesPage confirmSelect() {
        $x(".//*[@class='cookie__btn']").should(appear).click();
        return this;
    }

    public ElementsCollection getElementsVacanciesList() {
        return $$x(".//*[@class='vacancy-card vacancies__card']");
    }

    public List<String> getCityVacanciesList() {
        return getElementsVacanciesList()
                .stream()
                .map(x -> x.$x(".//*[@class='vacancy-card__desc']")
                        .getText())
                .collect(Collectors.toList());
    }

}
