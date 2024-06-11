package ru.edu.task1.account;

import lombok.Getter;
import ru.edu.task1.enums.Currency;

import java.util.HashMap;

public class Account {
    @Getter
    private String clientName;
    private HashMap<Currency,Integer> currencies = new HashMap<>();

    public Account(String clientName) {
        setClientName(clientName);
    }

    public void setClientName(String clientName) {
        if (clientName==null || clientName.isEmpty()) {
            throw new IllegalArgumentException("clientName must be not null");
        }
        this.clientName = clientName;
    }

    public HashMap<Currency,Integer> getCurrencies() {
        return new HashMap<>(currencies);
    }

    public void addCurrency(Currency currency, Integer amount) {
        if (amount<0) {
            throw new IllegalArgumentException("currency amount must be positive");
        }
        currencies.put(currency, amount);
    }

    public void removeCurrency(Currency currency) {
        currencies.remove(currency);
    }

    public AccountState saveState() {
        return new AccountState(clientName, currencies);
    }

    public void loadState(AccountState accountState) {
        this.clientName = accountState.getClientName();
        this.currencies = accountState.getCurrencies();
    }
}