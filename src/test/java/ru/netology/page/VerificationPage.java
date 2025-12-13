package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeInput = $("[data-test-id='code'] input");
    private final SelenideElement verifyButton = $("[data-test-id='action-verify']");

    public DashboardPage validVerify(DataHelper.VerificationCode code) {
        codeInput.setValue(code.getCode());
        codeInput.shouldHave(value(code.getCode()));
        verifyButton.click();
        return new DashboardPage();
    }
}