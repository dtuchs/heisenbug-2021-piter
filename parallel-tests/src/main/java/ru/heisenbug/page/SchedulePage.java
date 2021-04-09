package ru.heisenbug.page;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class SchedulePage {
    public static final String URL = "2021/spb/schedule";

    public SchedulePage checkTalkExist(String expectedSpeaker) {
        $$(".schedule__talk").find(text(expectedSpeaker))
                .shouldBe(visible);
        return this;
    }
}
