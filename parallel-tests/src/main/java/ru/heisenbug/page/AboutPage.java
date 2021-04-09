package ru.heisenbug.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AboutPage {
    public static final String URL = "#about";

    public SelenideElement getAboutText() {
        return $(".about-container");
    }
}
