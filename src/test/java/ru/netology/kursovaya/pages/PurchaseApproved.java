package ru.netology.kursovaya.pages;


import com.codeborne.selenide.SelenideElement;


import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PurchaseApproved {
    private SelenideElement text = $(".notification__content");

    public PurchaseApproved() {
        text.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text("Операция одобрена Банком."));
    }
}
