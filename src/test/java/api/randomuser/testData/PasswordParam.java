package api.randomuser.testData;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class PasswordParam {
    public static Stream<Arguments> passwordValues() {
        return Stream.of(
                Arguments.of("upper"),
                Arguments.of("lower, 1-8"),
                Arguments.of("special, number"),
                Arguments.of("17")
        );
    }
}
