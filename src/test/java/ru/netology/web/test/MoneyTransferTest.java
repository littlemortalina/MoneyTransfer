package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyAndReturnBack() {
        open("http://localhost:9999");

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboard = verificationPage.validVerify(verificationCode);

        var initialBalance1 = dashboard.getFirstCardBalance(); // 10000
        var initialBalance2 = dashboard.getSecondCardBalance(); // 10000
        var amount = 500;

        // Перевод с карты 1 на карту 2
        dashboard.selectCard(DataHelper.getSecondCardInfo())
                .makeValidTransfer(String.valueOf(amount), DataHelper.getFirstCardInfo());

        // Проверяем балансы после первого перевода
        assertEquals(initialBalance1 - amount, dashboard.getFirstCardBalance()); // 10000 - 500 = 9500
        assertEquals(initialBalance2 + amount, dashboard.getSecondCardBalance()); // 10000 + 500 = 10500

    }

    @Test
    void shouldTransferMoneyAndReturnBack2() {
        open("http://localhost:9999");

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboard = verificationPage.validVerify(verificationCode);

        var initialBalance1 = dashboard.getFirstCardBalance(); // 10000
        var initialBalance2 = dashboard.getSecondCardBalance(); // 10000
        var amount = 1000;

        // Перевод с карты 1 на карту 2
        dashboard.selectCard(DataHelper.getFirstCardInfo())
                .makeValidTransfer(String.valueOf(amount), DataHelper.getSecondCardInfo());

        // Проверяем балансы после первого перевода
        assertEquals(initialBalance1 + amount, dashboard.getFirstCardBalance()); // 10000 - 500 = 9500
        assertEquals(initialBalance2 - amount, dashboard.getSecondCardBalance()); // 10000 + 500 = 10500

    }
}
