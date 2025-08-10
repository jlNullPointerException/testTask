package api.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestUtils {

    public static List<String> readTestData(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    public static List<String> getTestData(String filePath) throws IOException {
        return readTestData(filePath);
    }

    public static String getShortUrl(String longUrl) {
        return longUrl.replaceAll("^(https?://[^/]+).*", "$1/");
    }

    public static String makeJsonBodyParam(String key, String value) {
        return  "{ \"" + key + "\": \"" + value.replace("\"", "\\\"") + "\" }";
    }
}
