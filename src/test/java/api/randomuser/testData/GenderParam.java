package api.randomuser.testData;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class GenderParam {
    public static final String KEY = Params.GENDER_KEY.getName();

    static Stream<Arguments> genderValues() {
        return Stream.of(
                Arguments.of("male"),
                Arguments.of("female")
        );
    }

    static Stream<Arguments> genderNegValues() {
        return Stream.of(
                Arguments.of(KEY, "ghfhrtthe"),
                Arguments.of(KEY, "1899090")
        );
    }
}
