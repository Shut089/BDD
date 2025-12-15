package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement amountInput = $("[data-test-id='amount'] input");
    private final SelenideElement fromInput = $("[data-test-id='from'] input");
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement cancelButton = $("[data-test-id='action-cancel']");

    public DashboardPage transferFromTo(int amount, DataHelper.CardInfo fromCard) {
        // сумма: приложение форматирует "1000" -> "1 000"
        amountInput.shouldBe(visible).setValue(String.valueOf(amount)).pressTab();


        // карта списания
        fromInput.shouldBe(visible).setValue(fromCard.getNumber()).pressTab();


        // кликаем только когда кнопка реально активна
        transferButton.shouldBe(visible, enabled).click();

        return new DashboardPage();
    }

    public DashboardPage cancel() {
        cancelButton.click();
        return new DashboardPage();
    }
}