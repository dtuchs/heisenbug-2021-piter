package ru.heisenbug.page;

import static com.codeborne.selenide.Selenide.$$;

public class PartnersPage {
    public static final String URL = "#partners";

    public PartnersPage checkPartners() {
        $$(".sponsors__container").shouldHaveSize(3);
        return this;
    }
}
