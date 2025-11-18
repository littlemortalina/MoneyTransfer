package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyFromFirrstTpSecondCard() {
        open("http://localhost:9999");

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboard = verificationPage.validVerify(verificationCode);

        var initialBalance1 = dashboard.getFirstCardBalance();
        var initialBalance2 = dashboard.getSecondCardBalance();
        var amount = initialBalance2 / 2;

        dashboard.selectCard(DataHelper.getSecondCardInfo())
                .makeTransfer(String.valueOf(amount), DataHelper.getFirstCardInfo());

        assertEquals(initialBalance1 - amount, dashboard.getFirstCardBalance());
        assertEquals(initialBalance2 + amount, dashboard.getSecondCardBalance());

    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        open("http://localhost:9999");

        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboard = verificationPage.validVerify(verificationCode);

        var initialBalance1 = dashboard.getFirstCardBalance();
        var initialBalance2 = dashboard.getSecondCardBalance();
        var amount = initialBalance1 / 2;

        dashboard.selectCard(DataHelper.getFirstCardInfo())
                .makeTransfer(String.valueOf(amount), DataHelper.getSecondCardInfo());

        assertEquals(initialBalance1 + amount, dashboard.getFirstCardBalance());
        assertEquals(initialBalance2 - amount, dashboard.getSecondCardBalance());
    }
}
