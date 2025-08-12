package web.sovcombank.page;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StartPage {
    private static final String BASE_URL = "https://people.sovcombank.ru/";

    public StartPage openPage() {
        open(BASE_URL);
        WebDriverRunner.getWebDriver().manage().window().maximize();
        return this;
    }

    public VacanciesPage clickVacancies() {
        $(By.xpath("//a[contains(text(), 'Вакансии')]")).click();
        return new  VacanciesPage();
    }



}
