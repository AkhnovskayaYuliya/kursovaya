package ru.netology.kursovaya.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.kursovaya.data.DataHelper;

import javax.xml.crypto.Data;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PageContinue {

    public SelenideElement cardNumber = $x("//*[@id='root']/div/form/fieldset/div[1]/span/span/span[2]/input");
    public SelenideElement monthDate = $x("//*[@id='root']/div/form/fieldset/div[2]/span/span[1]/span/span/span[2]/input");
    public SelenideElement yearDate = $x("//*[@id='root']/div/form/fieldset/div[2]/span/span[2]/span/span/span[2]/input");
    public SelenideElement cardHolderName = $x("//*[@id='root']/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    public SelenideElement cvc = $x("//*[@id='root']/div/form/fieldset/div[3]/span/span[2]/span/span/span[2]/input");
    public SelenideElement buttonNext = $x("//*[@id='root']/div/form/fieldset/div[4]/button/span/span");
    private SelenideElement errorFormatOfCardNumber = $x("//*[@id='root']/div/form/fieldset/div[1]/span/span/span[3]");
    private SelenideElement errorFormatOfMonth = $x("//*[@id='root']/div/form/fieldset/div[2]/span/span[1]/span/span/span[3]");
    private SelenideElement errorFormatOfYear = $x("//*[@id='root']/div/form/fieldset/div[2]/span/span[2]/span/span/span[3]");
    private SelenideElement errorFormatOfName = $x("//*[@id='root']/div/form/fieldset/div[3]/span/span[2]/span/span/span[3]");
    private SelenideElement errorFormatOfCVC = $x("//*[@id='root']/div/form/fieldset/div[3]/span/span[2]/span/span/span[3]");
    private SelenideElement errorYear = $x("//*[@id='root']/div/form/fieldset/div[2]/span/span[2]/span/span/span[3]");
    private SelenideElement errorValidityYear = $x("//*[@id='root']/div/form/fieldset/div[2]/span/span[2]/span/span/span[3]");
    private SelenideElement errorValidityMonth = $x("//*[@id='root']/div/form/fieldset/div[2]/span/span[1]/span/span/span[3]");
    private SelenideElement errorExpiredName = $x("//*[@id='root']/div/form/fieldset/div[3]/span/span[1]/span/span/span[3]");


    public PurchaseApproved purchaseApproved() {
        cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber());
        monthDate.setValue(DataHelper.approvedCardHolder().getMonth());
        yearDate.setValue(DataHelper.approvedCardHolder().getYear());
        cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder());
        cvc.setValue(DataHelper.approvedCardHolder().getCvc());
        buttonNext.click();
        return new PurchaseApproved();
    }

    public PurchaseDeclined purchaseDeclined() {
        cardNumber.setValue(DataHelper.declinedCardHolder().getCardNumber());
        monthDate.setValue(DataHelper.declinedCardHolder().getMonth());
        yearDate.setValue(DataHelper.declinedCardHolder().getYear());
        cardHolderName.setValue(DataHelper.declinedCardHolder().getHolder());
        cvc.setValue(DataHelper.declinedCardHolder().getCvc());
        buttonNext.click();
        return new PurchaseDeclined();
    }

    public void formWithoutCardNumber() {
        monthDate.setValue(DataHelper.approvedCardHolder().getMonth());
        yearDate.setValue(DataHelper.approvedCardHolder().getYear());
        cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder());
        cvc.setValue(DataHelper.approvedCardHolder().getCvc());
        buttonNext.click();
    }

    public void formWithoutMonth() {
        cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber());
        yearDate.setValue(DataHelper.approvedCardHolder().getYear());
        cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder());
        cvc.setValue(DataHelper.approvedCardHolder().getCvc());
        buttonNext.click();
    }

    public void formWithoutYear() {
        cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber());
        monthDate.setValue(DataHelper.approvedCardHolder().getMonth());
        cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder());
        cvc.setValue(DataHelper.approvedCardHolder().getCvc());
        buttonNext.click();
    }

    public void formWithoutName() {
        cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber());
        monthDate.setValue(DataHelper.approvedCardHolder().getMonth());
        yearDate.setValue(DataHelper.approvedCardHolder().getYear());
        cvc.setValue(DataHelper.approvedCardHolder().getCvc());
        buttonNext.click();
    }

    public void formWithoutCVC() {
        cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber());
        monthDate.setValue(DataHelper.approvedCardHolder().getMonth());
        yearDate.setValue(DataHelper.approvedCardHolder().getYear());
        cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder());
        buttonNext.click();
    }

    public void formWithoutMonthAndYear() {
        cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber());
        cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder());
        cvc.setValue(DataHelper.approvedCardHolder().getCvc());
        buttonNext.click();
    }


    public void errorFormatOfCardNumber() {
        errorFormatOfCardNumber.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void errorFormatOfMonth() {
        errorFormatOfMonth.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void errorFormatOfYear() {
        errorFormatOfYear.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void errorFormatOfName() {
        errorFormatOfName.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void errorFormatOfCVC() {
        errorFormatOfCVC.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void errorYear() {
        errorYear.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    public void errorValidityYear() {
        errorValidityYear.shouldBe(visible).shouldHave(text("Истёк срок действия карты"));
    }

    public void errorValidityMonth() {
        errorValidityMonth.shouldBe(visible).shouldHave(text("Неверно указан срок действия карты"));
    }

    public void errorExpiredName() {
        errorExpiredName.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }


}
