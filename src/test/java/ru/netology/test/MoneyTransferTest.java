package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoneyTransferTest {

    private DashboardPage dashboard;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var verifyPage = loginPage.validLogin(DataHelper.getAuthInfo());
        dashboard = verifyPage.validVerify(DataHelper.getVerificationCode());
    }

    @Test
    void shouldTransferMoneyFromSecondToFirst() {
        var to = DataHelper.getFirstCard();
        var from = DataHelper.getSecondCard();

        var balanceToBefore = dashboard.getCardBalance(to);
        var balanceFromBefore = dashboard.getCardBalance(from);

        int amount = Math.min(1000, balanceFromBefore / 2);
        assertTrue(amount > 0, "Недостаточно средств на карте-источнике для тестового перевода");

        var transferPage = dashboard.selectCardToTopUp(to);
        dashboard = transferPage.transferFromTo(amount, from);

        var balanceToAfter = dashboard.getCardBalance(to);
        var balanceFromAfter = dashboard.getCardBalance(from);

        assertEquals(balanceToBefore + amount, balanceToAfter);
        assertEquals(balanceFromBefore - amount, balanceFromAfter);
    }
}