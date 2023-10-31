package ru.netology.kursovaya.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private static final Faker faker = new Faker(new Locale("en"));

    @Value
    public static class ApprovedCardHolder {
        String cardNumber;
        String month;
        String year;
        String holder;
        String cvc;
    }

    @Value
    public static class DeclinedCardHolder {
        String cardNumber;
        String month;
        String year;
        String holder;
        String cvc;
    }

    public static String generateMonth(int addMonth, String pattern) {
        return LocalDate.now().plusMonths(addMonth).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String generateYear(int addYear, String pattern) {
        return LocalDate.now().plusYears(addYear).format(DateTimeFormatter.ofPattern(pattern));
    }

    static String randomMonth = generateMonth(1, "MM");
    static String randomYear = generateYear(3, "yy");

    public static ApprovedCardHolder approvedCardHolder() {
        return new ApprovedCardHolder("1111 2222 3333 4444", randomMonth, randomYear, faker.name().firstName() + " " + faker.name().lastName(), faker.numerify("###"));
    }

    public static DeclinedCardHolder declinedCardHolder() {
        return new DeclinedCardHolder("5555 6666 7777 8888", randomMonth, randomYear, faker.name().firstName() + " " + faker.name().lastName(), faker.numerify("###"));
    }


}
