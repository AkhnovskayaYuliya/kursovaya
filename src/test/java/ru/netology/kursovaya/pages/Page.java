package ru.netology.kursovaya.pages;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Page {
    private static SelenideElement buttonBuy = $x("//*[@id='root']/div/button[1]/span");

    public static PageContinue pageCont() {
        buttonBuy.click();
        return new PageContinue();
    }


}
