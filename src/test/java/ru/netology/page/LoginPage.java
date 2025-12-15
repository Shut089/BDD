package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginInput = $("[data-test-id='login'] input.input__control");
    private final SelenideElement passwordInput = $("[data-test-id='password'] input.input__control");
    private final SelenideElement loginButton = $("[data-test-id='action-login']");


    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginInput.shouldBe(visible).setValue(info.getLogin());
        passwordInput.shouldBe(visible).setValue(info.getPassword());

        loginInput.shouldHave(value(info.getLogin()));
        passwordInput.shouldHave(value(info.getPassword()));

        loginButton.click();
        return new VerificationPage();
    }

}