package ru.netology.kursovaya.test;


import com.mysql.cj.CancelQueryTask;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.kursovaya.data.DataHelper;
import ru.netology.kursovaya.data.SQLHelper;
import ru.netology.kursovaya.pages.Page;
import ru.netology.kursovaya.pages.PageContinue;


import static com.codeborne.selenide.Selenide.open;

public class purchaseTest {

    @BeforeEach
    void setup() {
        open("http://localhost:8080/");
    }


    @Test // 1
    @SneakyThrows
    public void approvedCardTest() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.approvedCardHolder();
        var purchaseApproved = pageContinue.purchaseApproved();
        var sql = new SQLHelper();
        var runner = new QueryRunner();
        String expected = "APPROVED";
        String actual = SQLHelper.getPaymentStatus();
        Assertions.assertEquals(expected, actual);
    }


    @Test //2 баг репорт
    @SneakyThrows
    public void declinedCardTest() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.declinedCardHolder();
        var purchaseDeclined = pageContinue.purchaseDeclined();
        var sql = new SQLHelper();
        var runner = new QueryRunner();
        String expected = "DECLINED";
        String actual = SQLHelper.getPaymentStatus();
        Assertions.assertEquals(expected, actual);
    }

    @Test // 3
    public void leaveTheHolderFieldEmpty() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.formWithoutName();
        pageContinue.errorExpiredName();
    }

    @Test // 4
    public void leaveTheYearFieldEmpty() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.formWithoutYear();
        pageContinue.errorFormatOfYear();
    }

    @Test // 5
    public void leaveTheMonthFieldEmpty() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.formWithoutMonth();
        pageContinue.errorFormatOfMonth();
    }

    @Test // 6
    public void leaveTheCardNumberEmpty() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.formWithoutCardNumber();
        pageContinue.errorFormatOfCardNumber();
    }

    @Test // 7
    public void leaveTheCVCFieldEmpty() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.formWithoutCVC();
        pageContinue.errorFormatOfCVC();
    }

    @Test // 8
    public void fillTheYearFieldWithTheValue00() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.formWithoutYear();
        pageContinue.yearDate.setValue("00");
        pageContinue.errorFormatOfYear();
    }

    @Test // 9
    public void fillTheMonthFieldWithTheValue00() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.formWithoutMonth();
        pageContinue.monthDate.setValue("00");
        pageContinue.errorFormatOfMonth();

    }

    @Test // 10
    public void fillTheCVCFieldWithTheValue00() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.formWithoutCVC();
        pageContinue.cvc.setValue("00");
        pageContinue.errorFormatOfCVC();
    }

    @Test // 11
    public void fillTheMonthFieldWithValue13() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.formWithoutMonth();
        pageContinue.monthDate.setValue("15");
        pageContinue.errorFormatOfMonth();
    }

    @Test // 12 баг репорт???
    public void fillTheMonthFieldWithThePreviousMonth() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.formWithoutMonthAndYear();
        pageContinue.monthDate.setValue("09");
        pageContinue.yearDate.setValue("23");
        pageContinue.errorValidityMonth();
    }

    @Test // 13 баг репорт
    public void fillTheYearFieldWithThePreviousYear() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.formWithoutYear();
        pageContinue.yearDate.setValue("22");
        pageContinue.errorValidityYear();
    }

    @Test // 14 Баг репорт
    public void fillTheHolderFieldWithRussianLetters() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.formWithoutName();
        pageContinue.cardHolderName.setValue("Вася Пупкин");
        pageContinue.errorFormatOfName();
    }

    @Test // 15 баг репорт
    public void fillTheHolderFieldWithNumbers() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.formWithoutName();
        pageContinue.cardHolderName.setValue("123456");
        pageContinue.errorFormatOfName();
    }

    @Test // 16
    public void fillTheCVCFieldWithTwoNumbers() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.formWithoutCVC();
        pageContinue.cvc.setValue("99");
        pageContinue.errorFormatOfCVC();
    }

    @Test // 17 баг репорт
    public void fillTheYearFieldWithTheValue30() {
        var page = new Page();
        var pageContinue = Page.pageCont();
        var holder = DataHelper.approvedCardHolder();
        pageContinue.formWithoutYear();
        pageContinue.yearDate.setValue("30");
        pageContinue.errorYear();
    }


}



