package ru.heisenbug.page;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class PCPage {
    public static final String URL = "organizers/#program-committee";

    public PCPage checkExpertExist(String expectedSpeaker) {
        $$(".people-card").find(text(expectedSpeaker))
                .shouldBe(visible);
        return this;
    }
}
