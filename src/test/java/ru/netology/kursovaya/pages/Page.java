package ru.netology.kursovaya.pages;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class Page {
    private static SelenideElement buttonBuy = $(byText("Купить"));

    public PageContinue pageCont() {
        buttonBuy.click();
        return new PageContinue();
    }
}
