package api.common;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class TestUtils {

    public static List<String> getTestData(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    public static String getShortUrl(String longUrl) {
        return longUrl.replaceAll("^(https?://[^/]+).*", "$1/");
    }

    public static String makeJsonBodyParam(String key, String value) {
        return  "{ \"" + key + "\": \"" + value.replace("\"", "\\\"") + "\" }";
    }

    public static String getPageUrl() {
        return WebDriverRunner.url();
    }


    @SafeVarargs
    public static Stream<Arguments> mergeStreams(Stream<Arguments>... streams) {
        return Stream.of(streams)
                .flatMap(s -> s);
    }
}
