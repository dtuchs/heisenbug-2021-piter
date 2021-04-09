package ru.heisenbug.page;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class SpeakersPage {
    public static final String URL = "#speakers";

    public ElementsCollection getSpeakers() {
        return $$(".people-card");
    }
}
