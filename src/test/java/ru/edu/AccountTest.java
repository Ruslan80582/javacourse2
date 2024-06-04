package ru.edu;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import ru.edu.account.Account;
import ru.edu.account.AccountState;
import ru.edu.account.SetAccountCurrenciesAction;
import ru.edu.account.SetAccountNameAction;
import ru.edu.enums.Currency;
import ru.edu.exceptions.UndoException;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void notNullClientNameTest() {
        assertThrows(IllegalArgumentException.class, () -> new Account(null));
    }

    @Test
    public void notEmptyClientNameTest() {
        assertThrows(IllegalArgumentException.class, () -> new Account(""));
    }

    @Test
    public void currencyAmountTest() {
        Account account = new Account("Ivanov Ivan");
        assertThrows(IllegalArgumentException.class, () -> account.addCurrency(Currency.RUB, -5));
    }

    @Test
    public void addSameCurrencyTest() {
        Account account = new Account("Ivanov Ivan");
        account.addCurrency(Currency.RUB, 10);
        account.addCurrency(Currency.RUB, 15);
        assertEquals(account.getCurrencies().get(Currency.RUB), 15);
    }

    @Test
    public void getCopyCurrenciesTest() {
        Account account = new Account("Ivanov Ivan");
        assertNotSame(account.getCurrencies(),account.getCurrencies());
    }

    @Test
    public void modifyUndoTest() throws UndoException {
        Account account = new Account("Ivanov Ivan");
        ModifyManager modifyManager = new ModifyManager();
        Modifable actionSetCurrency = new SetAccountCurrenciesAction(account, Currency.RUB, 100);
        Modifable actionSetName = new SetAccountNameAction(account, "Vasyly Ivanov");
        modifyManager.modify(actionSetCurrency);
        modifyManager.modify(actionSetName);
        assertEquals(account.getClientName(), "Vasyly Ivanov");
        modifyManager.undo();
        assertEquals(account.getClientName(), "Ivanov Ivan");
        assertEquals(account.getCurrencies().get(Currency.RUB), 100);
        modifyManager.undo();
        assertNull(account.getCurrencies().get(Currency.RUB));
    }

    @SneakyThrows
    @Test
    public void modifyUndoExceptionTest() {
        Account account = new Account("Ivanov Ivan");
        ModifyManager modifyManager = new ModifyManager();
        Modifable actionSetName = new SetAccountNameAction(account, "Vasyly Ivanov");
        modifyManager.modify(actionSetName);
        modifyManager.undo();
        assertThrows(UndoException.class, modifyManager::undo);
    }

    @Test
    public void saveStateTest() {
        Account account = new Account("Ivanov Ivan");
        account.addCurrency(Currency.RUB, 100);
        AccountState accountState = account.saveState();
        account.setClientName("Vasyly Ivanov");
        account.addCurrency(Currency.RUB, 200);
        assertEquals(account.getClientName(), "Vasyly Ivanov");
        assertEquals(account.getCurrencies().get(Currency.RUB), 200);
        account.loadState(accountState);
        assertEquals(account.getClientName(), "Ivanov Ivan");
        assertEquals(account.getCurrencies().get(Currency.RUB), 100);
    }
}

