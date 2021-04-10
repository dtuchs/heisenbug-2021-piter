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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.heisenbug.page.HeisenbugMainPage;

class WebTest {

    static {
        Configuration.browser = "chrome";
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

    @ValueSource(strings = {
            "Артем Ерошенко",
            "Андрей Солнцев",
            "Виталий Фридман",
    })
    @ParameterizedTest(name = "Проверка спикера {0}")
    void speakersTest(String name) {
        mainPage.speakersPage()
                .getSpeakers()
                .find(Condition.text(name))
                .shouldBe(Condition.visible);
    }

    @ValueSource(strings = {
            "JUnit 5",
            "Flaky tests",
            "TestOps",
    })
    @ParameterizedTest(name = "Проверка доклада {0}")
    void scheduleTest(String talk) {
        mainPage.schedulePage()
                .checkTalkExist(talk);
    }

    void printBrowser() {
        Allure.step(((RemoteWebDriver) WebDriverRunner.getWebDriver()).getCapabilities().getBrowserName());
    }
}
