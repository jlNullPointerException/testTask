package web.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    @BeforeAll
    protected static void openBasePage() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1720x1080";
        Configuration.browser = "chrome";
        Configuration.holdBrowserOpen = false;
    }
}
