package ru.netology.kursovaya.pages;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class PageContinue {

    public SelenideElement cardNumber = $("[placeholder='0000 0000 0000 0000']");
    public SelenideElement monthDate = $("[placeholder='08']");
    public SelenideElement yearDate = $("[placeholder='22']");
    public SelenideElement cardHolderName = $x("//*[@id='root']/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    public SelenideElement cvc = $("[placeholder='999']");
    public SelenideElement buttonNext = $(byText("Продолжить"));
    public SelenideElement error = $(".input__sub");
    private SelenideElement text = $(".notification__content");

    public void fillTheForm(SelenideElement cardNumber, SelenideElement monthDate, SelenideElement yearDate, SelenideElement cardHolderName, SelenideElement cvc) {
        buttonNext.click();
    }

    public void error(String expectedText) {
        error.shouldBe(visible).shouldHave(text(expectedText));
    }

    public void purchaseSuccessful(String expectedText) {
        text.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text(expectedText));
    }

}
