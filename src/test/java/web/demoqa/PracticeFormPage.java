package web.demoqa;

import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPage {
    private static final String URL_PATH = "/automation-practice-form";

    private final SelenideElement
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            userEmail = $("#userEmail"),
            gender = $x(".//*[@class='practice-form-wrapper']"),
            userNumber = $("#userNumber"),
            dateOfBirth = $x(".//*[@id='dateOfBirthInput']"),
            subject = $("#subjectsInput"),
            hobby = $("#hobbiesWrapper"),
            picture = $("#uploadPicture"),
            address = $("#currentAddress"),
            submit = $("#submit"),
            resultTableHeader = $x(".//*[@class='modal-header']"),
            resultTable = $x(".//*[@class='table-responsive']")

    ;

    public PracticeFormPage openPage() {
        open(URL_PATH);
        return this;
    }

    public PracticeFormPage sendFirstName(String name) {
        firstName.setValue(name);
        return this;
    }

    public PracticeFormPage sendLastName(String surname) {
        lastName.setValue(surname);
        return this;
    }

    public PracticeFormPage sendUserEmail(String email) {
        userEmail.setValue(email);
        return this;
    }

    public PracticeFormPage selectGender(String sex) {
        gender.$(byText(sex)).click();;
        return this;
    }

    public PracticeFormPage sendUserNumber(String number) {
        userNumber.setValue(number);
        return this;
    }

    public PracticeFormPage setDateOfBirth(String day, String month, String year) {
        dateOfBirth.click();
        $x(".//*[@class='react-datepicker__month-select']").$(byText(month)).click();
        $x(".//*[@class='react-datepicker__year-select']").$(byText(year)).click();
        $x(".//*[@class='react-datepicker__day react-datepicker__day--0" + day + "']").click();
        return this;
    }

    public PracticeFormPage selectSubjects(List<String> subjects) {
        subjects.stream().forEach(s -> this.subject.setValue(s).pressEnter());
        return this;
    }

    public PracticeFormPage selectHobbies(List<String> hobbies) {
        hobbies.stream().forEach(h -> this.hobby.$(byText(h)).click());
        return this;
    }

    public PracticeFormPage sendPicture(String pic) {
        picture.uploadFromClasspath(pic);
        return this;
    }

    public PracticeFormPage sendAddress(String currentAddress) {
        address.setValue(currentAddress);
        return this;
    }

    public PracticeFormPage selectStateAndCity(String state, String city) {
        $x(".//*[@class=' css-1wa3eu0-placeholder']").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $x(".//*[@class=' css-1wa3eu0-placeholder']").scrollTo().click();
        $("#stateCity-wrapper").$(byText(city)).click();
        return this;
    }

    public PracticeFormPage clickSubmit() {
        submit.scrollTo().click();
        return this;
    }

    public String getTableHeader() {
        return resultTableHeader.getText();
    }

    public Map<String, String> getTableDataAsMap() {
        return resultTable.$$("tr").stream()
                .filter(row -> row.$$("td").size() >= 2)
                .collect(Collectors.toMap(
                        row -> row.$("td").getText().trim(), // первый столб
                        row -> row.$$("td").get(1).getText().trim() // второй столб
                ));
    }

    public String getValueByKey(String key) {
        return getTableDataAsMap().get(key);
    }

}
