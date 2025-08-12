package web.sovcombank.tests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import web.sovcombank.page.StartPage;
import web.sovcombank.page.VacanciesPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class JobFilterTest extends BaseTest{

    @Test
    public void checkSelectWithCityAndCompany() {
        String city = "Москва";
        String company = "Совкомбанк Технологии";

//        VacanciesPage selectVacanciesPage = new StartPage()
        VacanciesPage selectVacanciesPage = new VacanciesPage()
                .openPage()
//                .clickVacancies()
                .selectCity(city)
                .selectCompany(company)
                .confirmSelect();

        sleep(5000);
        List<String> vacanciesTagList = selectVacanciesPage.getCityVacanciesList();
        List<String> v = selectVacanciesPage.getHrefVacanciesList();
        v.stream().forEach(t -> System.out.println(t));

        assertThat(vacanciesTagList.stream().allMatch(t -> t.contains(city))).isTrue();
        assertThat(vacanciesTagList.stream().allMatch(t -> t.contains(company))).isTrue();



    }

}
