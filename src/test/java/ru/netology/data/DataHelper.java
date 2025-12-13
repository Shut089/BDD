package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class CardInfo {
        String id;      // например "1" и "2"
        String number;  // "5559 0000 0000 0001"
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static CardInfo getFirstCard() {
        return new CardInfo("1", "5559 0000 0000 0001");
    }

    public static CardInfo getSecondCard() {
        return new CardInfo("2", "5559 0000 0000 0002");
    }


}
