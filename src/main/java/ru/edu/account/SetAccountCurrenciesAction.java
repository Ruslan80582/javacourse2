package ru.edu.account;

import ru.edu.enums.Currency;
import ru.edu.Modifable;

import java.util.HashMap;

public class SetAccountCurrenciesAction implements Modifable {
    private Account account;
    private Currency currency;
    private Integer amount;
    HashMap<Currency,Integer> prevCurrencies;

    public SetAccountCurrenciesAction(Account account, Currency currency, Integer amount) {
        this.account = account;
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public void modify() {
        prevCurrencies = account.getCurrencies();
        account.addCurrency(currency,amount);
    }

    @Override
    public void undo() {
        if (prevCurrencies.isEmpty()||!prevCurrencies.containsKey(currency)) {
            account.removeCurrency(currency);
        } else {
            account.addCurrency(currency, prevCurrencies.get(currency));
        }
    }
}
