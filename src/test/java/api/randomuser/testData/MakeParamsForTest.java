package api.randomuser.testData;

import api.common.TestUtils;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MakeParamsForTest {
    public static Stream<Arguments> genderPlusNation() {
        List<String> genders = GenderParam.genderValues()
                .map(arg -> (String) arg.get()[0])
                .collect(Collectors.toList());
        List<String> nations = NationParam.nationValues()
                .map(arg -> (String) arg.get()[0])
                .collect(Collectors.toList());


        return genders.stream()
                .flatMap(gender -> nations.stream()
                        .map(nation -> Arguments.of(gender, nation))
                );
    }

    public static Stream<Arguments> differentIncorrectParams() {
        return TestUtils.mergeStreams(
                GenderParam.genderNegValues(),
                NationParam.nationIncorrectValues()
        );
    }
}
