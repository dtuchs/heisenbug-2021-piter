package ru.heisenbug;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.heisenbug.page.HeisenbugMainPage;

class ChromeWebTest {

    private HeisenbugMainPage mainPage;

    @BeforeEach
    void setUp() {
        mainPage = Selenide.open("http://heisenbug-piter.ru", HeisenbugMainPage.class);
    }

    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Проверка страницы about")
    void checkAboutPage() {
    }

    @Test
    @DisplayName("Проверка спикера")
    void speakersTest() {
    }

    @Test
    @DisplayName("Проверка докладов в расписании")
    void scheduleTest() {
    }
}
