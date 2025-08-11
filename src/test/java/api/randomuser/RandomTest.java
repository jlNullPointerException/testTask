package api.randomuser;

import api.common.Specifications;
import api.randomuser.objects.Results;
import api.randomuser.objects.Root;
import api.randomuser.testData.Params;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class RandomTest {
    private static final String URL = "https://randomuser.me/api/";
    private static final int DEFAULT_PAGE_COUNT = 1;
    private static final int DEFAULT_RESULTS_COUNT = 1;

    @BeforeEach
    public void setSpecification() {
        RestAssured.reset();
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseStatus(200));
    }

    @Test
    public void withoutParam() {
        Root response = given()
                .when()
                .get()
                .then().log().all()
                .extract().response().as(Root.class);

        assertThat(response.getInfo().getPage()).isEqualTo(DEFAULT_PAGE_COUNT);
        assertThat(response.getInfo().getResults()).isEqualTo(DEFAULT_RESULTS_COUNT);
    }

    @ParameterizedTest
    @MethodSource("api.randomuser.testData.ResultParam#positiveResultValues")
    public void withDifferentPositiveResultParam(String key, String value) {
        Root response = given()
                .queryParam(key, value)
                .when()
                .get()
                .then().log().all()
                .extract().response().as(Root.class);

        assertThat(response.getInfo().getResults().toString()).isEqualTo(value);
        assertThat(response.getResults().size()).isEqualTo(Integer.parseInt(value));
    }

    @ParameterizedTest
    @MethodSource("api.randomuser.testData.ResultParam#negativeResultValues")
    public void withDifferentNegativeResultParam(String key, String value) {
        Root response = given()
                .queryParam(key, value)
                .when()
                .get()
                .then().log().all()
                .extract().response().as(Root.class);

        assertThat(response.getInfo().getResults()).isEqualTo(DEFAULT_PAGE_COUNT);
        assertThat(response.getResults().size()).isEqualTo(DEFAULT_RESULTS_COUNT);
    }

    @ParameterizedTest
    @MethodSource("api.randomuser.testData.MakeParamsForTest#genderPlusNation")
    public void withSelectedGenderAndNat(String gender, String nation) {
        Integer resultCount = 4;

        List<Results> response = given()
                .queryParam(Params.RESULT_KEY.getName(), resultCount)
                .queryParams(Params.GENDER_KEY.getName(), gender)
                .queryParams(Params.NATION_KEY.getName(), nation)
                .when()
                .get()
                .then().log().all()
                .extract().response().as(Root.class).getResults();

        List<String> actualGenders = response.stream().map(Results::getGender).collect(Collectors.toList());
        List<String> actualNations = response.stream().map(Results::getNat).collect(Collectors.toList());

        assertThat(actualGenders).containsOnly(gender);
        assertThat(actualNations).containsOnly(nation);
        assertThat(response.size()).isEqualTo(resultCount);
    }

    @ParameterizedTest
    @MethodSource("api.randomuser.testData.MakeParamsForTest#differentIncorrectParams")
    public void withDifferentIncorrectParam(String key, String value) {
        Integer resultCount = 40;

        List<Results> response = given()
                .queryParam(Params.RESULT_KEY.getName(), resultCount)
                .queryParam(key, value)
                .when()
                .get()
                .then().log().all()
                .extract().response().as(Root.class).getResults();

        List<String> actualValues = response.stream()
                .map(Results::getGender).collect(Collectors.toList());

        assertThat(actualValues).doesNotContain(value);
        assertThat(actualValues.stream().distinct().count()).isGreaterThan(1);
    }
}
