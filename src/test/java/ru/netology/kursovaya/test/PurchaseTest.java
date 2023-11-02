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
        pageContinue.fillTheForm(DataHelper.approvedCardHolder().getCardNumber(),
                DataHelper.approvedCardHolder().getMonth(),
                DataHelper.approvedCardHolder().getYear(),
                DataHelper.approvedCardHolder().getHolder(),
                DataHelper.approvedCardHolder().getCvc());
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
        pageContinue.fillTheForm(DataHelper.declinedCardHolder().getCardNumber(),
                DataHelper.declinedCardHolder().getMonth(),
                DataHelper.declinedCardHolder().getYear(),
                DataHelper.declinedCardHolder().getHolder(),
                DataHelper.declinedCardHolder().getCvc());
        pageContinue.purchaseSuccessful("Карта заблокирована");
        String expected = "DECLINED";
        String actual = SQLHelper.getPaymentStatus();
        Assertions.assertEquals(expected, actual);
    }

    @Test // 3
    public void leaveTheHolderFieldEmpty() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(DataHelper.approvedCardHolder().getCardNumber(),
                DataHelper.approvedCardHolder().getMonth(),
                DataHelper.approvedCardHolder().getYear(),
                null,
                DataHelper.approvedCardHolder().getCvc());
        pageContinue.error("Поле обязательно для заполнения");
    }

    @Test // 4
    public void leaveTheYearFieldEmpty() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(DataHelper.approvedCardHolder().getCardNumber(),
                DataHelper.approvedCardHolder().getMonth(),
                null,
                DataHelper.approvedCardHolder().getHolder(),
                DataHelper.approvedCardHolder().getCvc());
        pageContinue.error("Неверный формат");
    }

    @Test // 5
    public void leaveTheMonthFieldEmpty() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(DataHelper.approvedCardHolder().getCardNumber(),
                null,
                DataHelper.approvedCardHolder().getYear(),
                DataHelper.approvedCardHolder().getHolder(),
                DataHelper.approvedCardHolder().getCvc());
        pageContinue.error("Неверный формат");
    }

    @Test // 6
    public void leaveTheCardNumberEmpty() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(null,
                DataHelper.approvedCardHolder().getMonth(),
                DataHelper.approvedCardHolder().getYear(),
                DataHelper.approvedCardHolder().getHolder(),
                DataHelper.approvedCardHolder().getCvc());
        pageContinue.error("Неверный формат");
    }

    @Test // 7
    public void leaveTheCVCFieldEmpty() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(DataHelper.approvedCardHolder().getCardNumber(),
                DataHelper.approvedCardHolder().getMonth(),
                DataHelper.approvedCardHolder().getYear(),
                DataHelper.approvedCardHolder().getHolder(),
                null);
        pageContinue.error("Поле обязательно для заполнения");
    }

    @Test // 8
    public void fillTheYearFieldWithTheValue00() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(DataHelper.approvedCardHolder().getCardNumber(),
                DataHelper.approvedCardHolder().getMonth(),
                "00",
                DataHelper.approvedCardHolder().getHolder(),
                DataHelper.approvedCardHolder().getCvc());;
        pageContinue.error("Истёк срок действия карты");
    }

    @Test // 9 баг репорт
    public void fillTheMonthFieldWithTheValue00() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(DataHelper.approvedCardHolder().getCardNumber(),
                "00",
                DataHelper.approvedCardHolder().getYear(),
                DataHelper.approvedCardHolder().getHolder(),
                DataHelper.approvedCardHolder().getCvc());
        pageContinue.error("Неверный формат");

    }

    @Test // 10
    public void fillTheCVCFieldWithTheValue00() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(DataHelper.approvedCardHolder().getCardNumber(),
                DataHelper.approvedCardHolder().getMonth(),
                DataHelper.approvedCardHolder().getYear(),
                DataHelper.approvedCardHolder().getHolder(),
                "00");
        pageContinue.error("Неверный формат");
    }

    @Test // 11
    public void fillTheMonthFieldWithValue13() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(DataHelper.approvedCardHolder().getCardNumber(),
                "13",
                DataHelper.approvedCardHolder().getYear(),
                DataHelper.approvedCardHolder().getHolder(),
                DataHelper.approvedCardHolder().getCvc());
        pageContinue.error("Неверно указан срок действия карты");
    }

    @Test // 12
    public void fillTheMonthFieldWithThePreviousMonth() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(DataHelper.approvedCardHolder().getCardNumber(),
                "09",
                "23",
                DataHelper.approvedCardHolder().getHolder(),
                DataHelper.approvedCardHolder().getCvc());
        pageContinue.error("Неверно указан срок действия карты");
    }

    @Test // 13 
    public void fillTheYearFieldWithThePreviousYear() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(DataHelper.approvedCardHolder().getCardNumber(),
                DataHelper.approvedCardHolder().getMonth(),
                "22",
                DataHelper.approvedCardHolder().getHolder(),
                DataHelper.approvedCardHolder().getCvc());
        pageContinue.error("Истёк срок действия карты");
    }

    @Test // 14 Баг репорт
    public void fillTheHolderFieldWithRussianLetters() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(DataHelper.approvedCardHolder().getCardNumber(),
                DataHelper.approvedCardHolder().getMonth(),
                DataHelper.approvedCardHolder().getYear(),
                "Вася Пупкин",
                DataHelper.approvedCardHolder().getCvc());;
        pageContinue.error("Неверный формат");
    }

    @Test // 15 баг репорт
    public void fillTheHolderFieldWithNumbers() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(DataHelper.approvedCardHolder().getCardNumber(),
                DataHelper.approvedCardHolder().getMonth(),
                DataHelper.approvedCardHolder().getYear(),
                "123456",
                DataHelper.approvedCardHolder().getCvc());
        pageContinue.error("Неверный формат");
    }

    @Test // 16
    public void fillTheCVCFieldWithTwoNumbers() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(DataHelper.approvedCardHolder().getCardNumber(),
                DataHelper.approvedCardHolder().getMonth(),
                DataHelper.approvedCardHolder().getYear(),
                DataHelper.approvedCardHolder().getHolder(),
                "99");
        pageContinue.error("Неверный формат");
    }

    @Test // 17 
    public void fillTheYearFieldWithTheValue30() {
        var pageContinue = new Page().pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.fillTheForm(DataHelper.approvedCardHolder().getCardNumber(),
                DataHelper.approvedCardHolder().getMonth(),
                "30",
                DataHelper.approvedCardHolder().getHolder(),
                DataHelper.approvedCardHolder().getCvc());
        pageContinue.error("Неверно указан срок действия карты");
    }

}



