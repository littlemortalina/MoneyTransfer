package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    // ОБЪЯВЛЕНИЕ ЭЛЕМЕНТОВ (добавьте эти строки)
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement cancelButton = $("[data-test-id=action-cancel]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public TransferPage() {
        amountField.shouldBe(visible);
        fromField.shouldBe(visible);
    }

    public DashboardPage makeValidTransfer(String amount, DataHelper.CardInfo fromCard) {
        amountField.setValue(amount);
        fromField.setValue(fromCard.getCardNumber());
        transferButton.click();
        return new DashboardPage();
    }


    private void makeTransfer(String amount, DataHelper.CardInfo fromCard) {
        amountField.setValue(amount);
        fromField.setValue(fromCard.getCardNumber());
        transferButton.click();
    }


}