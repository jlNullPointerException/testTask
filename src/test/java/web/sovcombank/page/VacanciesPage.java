package web.sovcombank.page;

import api.common.TestUtils;
import com.codeborne.selenide.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class VacanciesPage {
    private static final String TITLE_TEXT = " Вакансии в Совкомбанке ";

    private final SelenideElement
            title = $x(".//*[@class='vacancies__title']"),
            confirmButton = $x(".//*[@class='cookie__btn']"),
            elementInCityList = $x(".//*[@class='multiselect__element']");

    private final ElementsCollection
            multiselectsList = $$x(".//*[@class='multiselect ui-selector']"),
            vacanciesList = $$x(".//*[@class='vacancy-card vacancies__card']");

    public VacanciesPage() {
//        Configuration.pageLoadStrategy = "normal";
        title.shouldHave(Condition.exactText(TITLE_TEXT));
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
        multiselectsList.get(0).should(appear).click();
        elementInCityList.$(byText(city)).click();
        return this;
    }

    public VacanciesPage selectCompany(String company) {
        multiselectsList.get(1).should(appear).click();
        $x(".//*[@class='multiselect__element' and .//span[contains(text(), '" + company + "')]]")
                .should(appear).click();
        return this;
    }

    public VacanciesPage confirmSelect() {
        confirmButton.should(appear).click();
        return this;
    }

    public ElementsCollection getElementsVacanciesList() {
        return vacanciesList;
    }

    public List<String> getVacanciesTagList() {
        return getElementsVacanciesList()
                .stream()
                .map(x -> x.$x(".//*[@class='vacancy-card__desc']")
                        .getText())
                .collect(Collectors.toList());
    }
}
