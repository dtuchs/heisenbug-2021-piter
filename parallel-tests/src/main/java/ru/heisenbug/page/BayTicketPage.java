package ru.heisenbug.page;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class BayTicketPage {
    public static final String URL = "registration";

    public enum TicketType {
        Basic, Standard, Pro, Full;
    }

    public ElementsCollection getRegistrationCards() {
        return $$(".registration__item");
    }

    public BayTicketPage doPersonalRegistration(TicketType type) {
        getRegistrationCards().find(text("Personal")).click();
        clickOnButton(type);
        return this;
    }

    public BayTicketPage doCorporateRegistration(TicketType type) {
        getRegistrationCards().find(text("Corporate")).click();
        clickOnButton(type);
        return this;
    }

    private void clickOnButton(TicketType type) {
        ElementsCollection rowHeaders = $$(".buy-table__cell--content");
        int index = rowHeaders.indexOf(rowHeaders.find(text(type.name())));
        $$(".buy-table__cell a.btn-primary")
                .shouldBe(sizeGreaterThan(index))
                .get(index)
                .click();
    }
}
