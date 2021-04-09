package ru.heisenbug.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class HeisenbugMainPage {

    private ElementsCollection menuItems = $$(".navbar-nav .nav-item");

    public ElementsCollection getMenuItems() {
        return menuItems;
    }

    public SelenideElement getMenuItem(String text) {
        return menuItems.find(text(text));
    }

    public AboutPage aboutPage() {
        getMenuItem("О нас").click();
        return new AboutPage();
    }

    public BayTicketPage bayTicketPage() {
        getMenuItem("Купить билет").click();
        return new BayTicketPage();
    }

    public SpeakersPage speakersPage() {
        getMenuItem("Спикеры").click();
        return new SpeakersPage();
    }

    public SchedulePage schedulePage() {
        getMenuItem("Программа").click();
        return new SchedulePage();
    }

    public PartnersPage partnersPage() {
        getMenuItem("Партнеры").click();
        return new PartnersPage();
    }

    public PCPage pcPage() {
        getMenuItem("Программный комитет").click();
        return new PCPage();
    }
}
