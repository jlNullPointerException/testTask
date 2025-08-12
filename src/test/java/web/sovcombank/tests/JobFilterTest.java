package web.sovcombank.tests;

import org.junit.jupiter.api.Test;
import web.sovcombank.page.StartPage;
import web.sovcombank.page.VacanciesPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class JobFilterTest extends BaseTest{

    @Test
    public void checkSelectWithCityAndCompany() {
        String city = "Москва";
        String company = "Совкомбанк Технологии";

        VacanciesPage selectVacanciesPage = new StartPage()
                .openPage()
                .clickVacancies()
                .openPage()
                .selectCity(city)
                .selectCompany(company)
                .confirmSelect();

        sleep(5000);
        List<String> vacanciesTagList = selectVacanciesPage.getCityVacanciesList();

        assertThat(vacanciesTagList.stream().allMatch(t -> t.contains(city))).isTrue();
        assertThat(vacanciesTagList.stream().allMatch(t -> t.contains(company))).isTrue();
    }

}
