package ru.netology.kursovaya.pages;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PageContinue {

    private SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthDate = $("[placeholder='08']");
    private SelenideElement yearDate = $("[placeholder='22']");
    private SelenideElement cardHolderName = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cvc = $("[placeholder='999']");
    private SelenideElement buttonNext = $(byText("Продолжить"));
    private SelenideElement error = $(".input__sub");
    private SelenideElement text = $(".notification__content");


    public void fillTheForm(String numberCard, String month, String year, String holder, String cvv) {
        cardNumber.setValue(numberCard);
        monthDate.setValue(month);
        yearDate.setValue(year);
        cardHolderName.setValue(holder);
        cvc.setValue(cvv);
        buttonNext.click();
    }

    public void error(String expectedText) {
        error.shouldBe(visible).shouldHave(text(expectedText));
    }

    public void purchaseSuccessful(String expectedText) {
        text.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text(expectedText));
    }

}
