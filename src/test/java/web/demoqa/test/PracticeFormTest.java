package web.demoqa.test;

import org.junit.jupiter.api.Test;
import web.demoqa.page.PracticeFormPage;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class PracticeFormTest extends BaseTest{
    public static final String TABLE_HEADER = "Thanks for submitting the form";

    @Test
    public void successfulWithAllParamsTest() {
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

    @Test
    public void successfulWithRequiredParamsTest() {
        String firstName = "Klaus";
        String lastName = "Meine";
        String gender = "Male";
        String userNumber = "7999999666";

        PracticeFormPage result = new PracticeFormPage()
                .openPage()
                .sendFirstName(firstName)
                .sendLastName(lastName)
                .selectGender(gender)
                .sendUserNumber(userNumber)
                .clickSubmit();

        assertThat(result.getTableHeader()).isEqualTo(TABLE_HEADER);
        assertThat(result.getValueByKey("Student Name"))
                .isEqualTo(firstName + " " + lastName);
        assertThat(result.getValueByKey("Gender")).isEqualTo(gender);
        assertThat(result.getValueByKey("Mobile")).isEqualTo(userNumber);
    }
}
