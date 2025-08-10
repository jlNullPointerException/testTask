package api.cleanuri;

import api.common.Specifications;
import api.common.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ShortenTest {
    private static final String URL = "https://cleanuri.com/api/v1/shorten";
    private static final String PARAM = "url";
    static List<String> testData;

    @Test
    public void successfulTest() throws IOException {
        String filePath = "F:\\testdata\\cleanuri\\PositiveCases.txt";
        testData = TestUtils.readTestData(filePath);

        for (String input: testData) {
            String jsonBody = TestUtils.makeJsonBodyParam(PARAM, input);
            Specifications.installSpecification(Specifications.requestSpec(input), Specifications.responseStatus(200));

            String response = given()
                    .body(jsonBody)
                    .when().log().all()
                    .post(URL)
                    .then().log().all()
                    .extract().body().jsonPath().getString("result_url");

            assertThat(response).isEqualTo(TestUtils.getShortUrl(input));
        }
    }

    @Test
    public void incorrectSymbolTest() throws IOException {
        String filePath = "F:\\testdata\\cleanuri\\NegativeCases.txt";
        String errorText = "API Error: URL is invalid";
        testData = TestUtils.readTestData(filePath);

        for (String input: testData) {
            String jsonBody = TestUtils.makeJsonBodyParam(PARAM, input);
            Specifications.installSpecification(Specifications.requestSpec(input), Specifications.responseStatus(400));

            String response = given()
                    .body(jsonBody)
                    .when().log().all()
                    .post(URL)
                    .then().log().all()
                    .extract().body().jsonPath().getString("error");

              assertThat(response).contains(errorText);
        }
    }

    @Test
    public void emptyTest() throws IOException {
        String errorText = "API Error: After sanitization URL is empty";
        String input = "";

        String jsonBody = TestUtils.makeJsonBodyParam(PARAM, input);
        Specifications.installSpecification(Specifications.requestSpec(input), Specifications.responseStatus(400));

        String response = given()
                .body(jsonBody)
                .when().log().all()
                .post(URL)
                .then().log().all()
                .extract().body().jsonPath().getString("error");

        assertThat(response).isEqualTo(errorText);
    }
}
