package api.randomuser.testData;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.stream.Stream;

public class ResultParam {
    public static final String KEY = Params.RESULT_KEY.getName();

    public static Stream<Arguments> positiveResultValues() {
        String[] values = {"1", "7", "666", "5000"};
        return Arrays.stream(values)
                .map(value -> Arguments.of(KEY, value));
    }

    public static Stream<Arguments> negativeResultValues() {
        String[] values = {"-1", "0", "5001", "7777"};
        return Arrays.stream(values)
                .map(value -> Arguments.of(KEY, value));
    }

}
