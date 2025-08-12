package web.demoqa;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PracticeFormTest extends BaseTest{
    public static final String TABLE_HEADER = "Thanks for submitting the form";

    @Test
    public void successfulWithAllParamsTest() {
        String firstName = "Klaus";
        String lastName = "Meine";
        String userEmail = "klausMeine@music.com";
        String userNumber = "7999999666";
        String dateOfBirth = "25.05.1948";
        String address = "Germany, Berlin, Schwarzstrasse 11";

        open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $x(".//*[@class='practice-form-wrapper']").$(byText("Male")).click();
        $("#userNumber").setValue(userNumber);

        $x(".//*[@id='dateOfBirthInput']").click();
        $x(".//*[@class='react-datepicker__month-select']").$(byText("May")).click();
        $x(".//*[@class='react-datepicker__year-select']").$(byText("1948")).click();
        $x(".//*[@class='react-datepicker__day react-datepicker__day--025']").click();

        $("#subjectsInput").setValue("English").pressEnter();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("km.jpg");
        $("#currentAddress").setValue(address);

        $x(".//*[@class=' css-1wa3eu0-placeholder']").scrollTo().click();
        $("#stateCity-wrapper").$(byText("Uttar Pradesh")).click();

        $x(".//*[@class=' css-1wa3eu0-placeholder']").scrollTo().click();
        $("#stateCity-wrapper").$(byText("Merrut")).click();

        $("#submit").click();

        String form = $x(".//*[@class='modal-header']").getText();
        System.out.println(form);
        assertThat(form).contains("Thanks for submitting the form");

        SelenideElement table = $x(".//*[@class='table-responsive']");
        String studentName = table.$(byText("Student Name")).parent().getText();
        System.out.println(studentName);
        assertThat(studentName).contains(firstName + " " + lastName);
    }

    @Test
    public void successfulWithAllParamsTestNew() {
        String firstName = "Klaus";
        String lastName = "Meine";
        String userEmail = "klausMeine@music.com";
        String gender = "Male";
        String userNumber = "7999999666";
        String dayOfBirth = "25";
        String monthOfBirth = "May";
        String yearOfBirth = "1948";
        String picture = "km.jpg";
        String address = "Germany, Berlin, Schwarzstrasse 11";
        String state = "Uttar Pradesh";
        String city = "Merrut";
        List<String> subjects = List.of("English");
        List<String> hobbies = List.of("Music", "Reading");

        PracticeFormPage result = new PracticeFormPage()
                .openPage()
                .sendFirstName(firstName)
                .sendLastName(lastName)
                .sendUserEmail(userEmail)
                .selectGender(gender)
                .sendUserNumber(userNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .selectSubjects(subjects)
                .selectHobbies(hobbies)
                .sendPicture(picture)
                .sendAddress(address)
                .selectStateAndCity(state, city)
                .clickSubmit();

        assertThat(result.getTableHeader()).isEqualTo(TABLE_HEADER);
        assertThat(result.getValueByKey("Student Name"))
                .isEqualTo(firstName + " " + lastName);
        assertThat(result.getValueByKey("Student Email")).isEqualTo(userEmail);
        assertThat(result.getValueByKey("Gender")).isEqualTo(gender);
        assertThat(result.getValueByKey("Mobile")).isEqualTo(userNumber);
        assertThat(result.getValueByKey("Date of Birth"))
                .isEqualTo(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth);
        assertThat(result.getValueByKey("Subjects")).isEqualTo(subjects
                .stream().collect(Collectors.joining(", ")));
        assertThat(result.getValueByKey("Hobbies")).isEqualTo(hobbies
                .stream().collect(Collectors.joining(", ")));
        assertThat(result.getValueByKey("Picture")).isEqualTo(picture);
        assertThat(result.getValueByKey("Address")).isEqualTo(address);
        assertThat(result.getValueByKey("State and City"))
                .isEqualTo(state + " " + city);
    }
}
