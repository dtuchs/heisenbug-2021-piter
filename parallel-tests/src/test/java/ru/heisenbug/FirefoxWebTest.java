package ru.heisenbug;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Isolated;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.heisenbug.page.HeisenbugMainPage;

@Isolated
class FirefoxWebTest {

    static {
        Configuration.browser = "firefox";
        Configuration.remote = "http://localhost:4444/wd/hub";
    }

    private HeisenbugMainPage mainPage;

    @BeforeEach
    void setUp() {
        mainPage = Selenide.open("http://heisenbug-piter.ru", HeisenbugMainPage.class);
        printBrowser();
    }

    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Проверка страницы about")
    void checkAboutPage() {
        mainPage.aboutPage()
                .getAboutText()
                .shouldHave(Condition.text("большая техническая конференция"));
    }

    void printBrowser() {
        Allure.step(((RemoteWebDriver) WebDriverRunner.getWebDriver()).getCapabilities().getBrowserName());
    }
}
