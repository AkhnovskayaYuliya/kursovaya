package ru.netology.kursovaya.test;


import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.kursovaya.data.DataHelper;
import ru.netology.kursovaya.data.SQLHelper;
import ru.netology.kursovaya.pages.Page;

import static com.codeborne.selenide.Selenide.open;


public class PurchaseTest {

    @BeforeEach
    void setup() {
        open("http://localhost:8080/");
    }


    @Test // 1
    @SneakyThrows
    public void approvedCardTest() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber()),
                pageContinue.monthDate.setValue(DataHelper.approvedCardHolder().getMonth()),
                pageContinue.yearDate.setValue(DataHelper.approvedCardHolder().getYear()),
                pageContinue.cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder()),
                pageContinue.cvc.setValue(DataHelper.approvedCardHolder().getCvc()));
        pageContinue.purchaseSuccessful("Операция одобрена Банком");
        String expected = "APPROVED";
        String actual = SQLHelper.getPaymentStatus();
        Assertions.assertEquals(expected, actual);
    }

    @Test //2 баг репорт
    @SneakyThrows
    public void declinedCardTest() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.declinedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue(DataHelper.declinedCardHolder().getCardNumber()),
                pageContinue.monthDate.setValue(DataHelper.declinedCardHolder().getMonth()),
                pageContinue.yearDate.setValue(DataHelper.declinedCardHolder().getYear()),
                pageContinue.cardHolderName.setValue(DataHelper.declinedCardHolder().getHolder()),
                pageContinue.cvc.setValue(DataHelper.declinedCardHolder().getCvc()));
        pageContinue.purchaseSuccessful("Карта заблокирована");
        String expected = "DECLINED";
        String actual = SQLHelper.getPaymentStatus();
        Assertions.assertEquals(expected, actual);
    }

    @Test // 3
    public void leaveTheHolderFieldEmpty() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue(DataHelper.declinedCardHolder().getCardNumber()),
                pageContinue.monthDate.setValue(DataHelper.declinedCardHolder().getMonth()),
                pageContinue.yearDate.setValue(DataHelper.declinedCardHolder().getYear()),
                pageContinue.cardHolderName.setValue((String) null),
                pageContinue.cvc.setValue(DataHelper.declinedCardHolder().getCvc()));
        pageContinue.error("Поле обязательно для заполнения");
    }

    @Test // 4
    public void leaveTheYearFieldEmpty() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber()),
                pageContinue.monthDate.setValue(DataHelper.approvedCardHolder().getMonth()),
                pageContinue.yearDate.setValue((String) null),
                pageContinue.cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder()),
                pageContinue.cvc.setValue(DataHelper.approvedCardHolder().getCvc()));
        pageContinue.error("Неверный формат");
    }

    @Test // 5
    public void leaveTheMonthFieldEmpty() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber()),
                pageContinue.monthDate.setValue((String) null),
                pageContinue.yearDate.setValue(DataHelper.approvedCardHolder().getYear()),
                pageContinue.cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder()),
                pageContinue.cvc.setValue(DataHelper.approvedCardHolder().getCvc()));
        pageContinue.error("Неверный формат");
    }

    @Test // 6
    public void leaveTheCardNumberEmpty() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue((String) null),
                pageContinue.monthDate.setValue(DataHelper.approvedCardHolder().getMonth()),
                pageContinue.yearDate.setValue(DataHelper.approvedCardHolder().getYear()),
                pageContinue.cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder()),
                pageContinue.cvc.setValue(DataHelper.approvedCardHolder().getCvc()));
        pageContinue.error("Неверный формат");
    }

    @Test // 7
    public void leaveTheCVCFieldEmpty() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber()),
                pageContinue.monthDate.setValue(DataHelper.approvedCardHolder().getMonth()),
                pageContinue.yearDate.setValue(DataHelper.approvedCardHolder().getYear()),
                pageContinue.cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder()),
                pageContinue.cvc.setValue((String) null));
        pageContinue.error("Поле обязательно для заполнения");
    }

    @Test // 8
    public void fillTheYearFieldWithTheValue00() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber()),
                pageContinue.monthDate.setValue(DataHelper.approvedCardHolder().getMonth()),
                pageContinue.yearDate.setValue("00"),
                pageContinue.cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder()),
                pageContinue.cvc.setValue(DataHelper.approvedCardHolder().getCvc()));
        pageContinue.error("Истёк срок действия карты");
    }

    @Test // 9 bug
    public void fillTheMonthFieldWithTheValue00() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber()),
                pageContinue.monthDate.setValue("00"),
                pageContinue.yearDate.setValue(DataHelper.approvedCardHolder().getYear()),
                pageContinue.cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder()),
                pageContinue.cvc.setValue(DataHelper.approvedCardHolder().getCvc()));
        pageContinue.error("Неверный формат");

    }

    @Test // 10
    public void fillTheCVCFieldWithTheValue00() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber()),
                pageContinue.monthDate.setValue(DataHelper.approvedCardHolder().getMonth()),
                pageContinue.yearDate.setValue(DataHelper.approvedCardHolder().getYear()),
                pageContinue.cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder()),
                pageContinue.cvc.setValue("00"));
        pageContinue.error("Неверный формат");
    }

    @Test // 11
    public void fillTheMonthFieldWithValue13() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber()),
                pageContinue.monthDate.setValue("13"),
                pageContinue.yearDate.setValue(DataHelper.approvedCardHolder().getYear()),
                pageContinue.cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder()),
                pageContinue.cvc.setValue(DataHelper.approvedCardHolder().getCvc()));
        pageContinue.error("Неверно указан срок действия карты");
    }

    @Test // 12
    public void fillTheMonthFieldWithThePreviousMonth() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber()),
                pageContinue.monthDate.setValue("09"),
                pageContinue.yearDate.setValue("23"),
                pageContinue.cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder()),
                pageContinue.cvc.setValue(DataHelper.approvedCardHolder().getCvc()));
        pageContinue.error("Неверно указан срок действия карты");
    }

    @Test // 13 баг репорт!
    public void fillTheYearFieldWithThePreviousYear() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber()),
                pageContinue.monthDate.setValue(DataHelper.approvedCardHolder().getMonth()),
                pageContinue.yearDate.setValue("22"),
                pageContinue.cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder()),
                pageContinue.cvc.setValue(DataHelper.approvedCardHolder().getCvc()));
        pageContinue.error("Истёк срок действия карты");
    }

    @Test // 14 Баг репорт
    public void fillTheHolderFieldWithRussianLetters() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber()),
                pageContinue.monthDate.setValue(DataHelper.approvedCardHolder().getMonth()),
                pageContinue.yearDate.setValue(DataHelper.approvedCardHolder().getYear()),
                pageContinue.cardHolderName.setValue("Вася Пупкин"),
                pageContinue.cvc.setValue(DataHelper.approvedCardHolder().getCvc()));
        pageContinue.error("Неверный формат");
    }

    @Test // 15 баг репорт
    public void fillTheHolderFieldWithNumbers() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber()),
                pageContinue.monthDate.setValue(DataHelper.approvedCardHolder().getMonth()),
                pageContinue.yearDate.setValue(DataHelper.approvedCardHolder().getYear()),
                pageContinue.cardHolderName.setValue("123456"),
                pageContinue.cvc.setValue(DataHelper.approvedCardHolder().getCvc()));
        pageContinue.error("Неверный формат");
    }

    @Test // 16
    public void fillTheCVCFieldWithTwoNumbers() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber()),
                pageContinue.monthDate.setValue(DataHelper.approvedCardHolder().getMonth()),
                pageContinue.yearDate.setValue(DataHelper.approvedCardHolder().getYear()),
                pageContinue.cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder()),
                pageContinue.cvc.setValue("99"));
        pageContinue.error("Неверный формат");
    }

    @Test // 17 баг репорт
    public void fillTheYearFieldWithTheValue30() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(pageContinue.cardNumber.setValue(DataHelper.approvedCardHolder().getCardNumber()),
                pageContinue.monthDate.setValue(DataHelper.approvedCardHolder().getMonth()),
                pageContinue.yearDate.setValue("30"),
                pageContinue.cardHolderName.setValue(DataHelper.approvedCardHolder().getHolder()),
                pageContinue.cvc.setValue(DataHelper.approvedCardHolder().getCvc()));
        pageContinue.error("Неверно указан срок действия карты");
    }


}



