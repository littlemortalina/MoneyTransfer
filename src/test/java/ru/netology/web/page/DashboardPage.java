package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement header = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        header.shouldBe(visible);
    }

    private SelenideElement getCardElement(DataHelper.CardInfo cardInfo) {
        return cards.findBy(Condition.attribute("data-test-id", cardInfo.getTestId()));
    }

    public int getFirstCardBalance() {
        var text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        var text = cards.last().text();
        return extractBalance(text);
    }

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        var elementsTest = getCardElement(cardInfo).getText();
        return extractBalance(elementsTest);
    }

    public TransferPage transferToFirstCard() {
        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button").click();
        return new TransferPage();
    }

    public TransferPage transferToSecondCard() {
        $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button").click();
        return new TransferPage();
    }


    public TransferPage selectCard(DataHelper.CardInfo cardInfo) {
        getCardElement(cardInfo).$("button").click();
        return new TransferPage();
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}
