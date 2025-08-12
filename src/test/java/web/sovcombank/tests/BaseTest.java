package web.sovcombank.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

@BeforeAll
protected static void openBasePage() {
    Configuration.browser = "chrome";
    Configuration.holdBrowserOpen = true;
    Configuration.timeout = 5000;
    }
}
