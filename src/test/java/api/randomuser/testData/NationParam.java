package api.randomuser.testData;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class NationParam {
    public static final String KEY = Params.NATION_KEY.getName();

    public static Stream<Arguments> nationValues() {
        return Stream.of(
                Arguments.of("BR"),
                Arguments.of("DE"),
                Arguments.of("NL")
        );
    }

    public static Stream<Arguments> nationIncorrectValues() {
        return Stream.of(
                Arguments.of(KEY, "RU"),
                Arguments.of(KEY, "zjhfgrgir"),
                Arguments.of(KEY, "1144556")
        );
    }
}
