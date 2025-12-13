package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private final ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс:";
    private final String balanceFinish = "р.";

    public DashboardPage() {
        cards.shouldHave(sizeGreaterThan(0));
    }

    // 1) получение баланса по карте со страницы списка карт
    public int getCardBalance(DataHelper.CardInfo card) {
        var cardElement = findCard(card);
        var text = cardElement.getText();
        return extractBalance(text);
    }

    // 2) выбор карты для пополнения
    public TransferPage selectCardToTopUp(DataHelper.CardInfo card) {
        var cardElement = findCard(card);
        cardElement.$("[data-test-id='action-deposit']")
                .shouldBe(visible, enabled)
                .click();
        return new TransferPage();
    }

    private SelenideElement findCard(DataHelper.CardInfo card) {
        // ищем по последним 4 цифрам — так обычно сделано в шаблоне
        var last4 = card.getNumber().replaceAll("\\s", "");
        last4 = last4.substring(last4.length() - 4);
        return cards.findBy(com.codeborne.selenide.Condition.text(last4));
    }

    private int extractBalance(String text) {
        var start = text.indexOf("баланс:");
        var end = text.indexOf("р.", start);
        var value = text.substring(start + "баланс:".length(), end).trim();
        return Integer.parseInt(value.replaceAll("\\s", ""));
    }
}
