package ru.edu.account;

import lombok.Getter;
import ru.edu.enums.Currency;

import java.util.HashMap;

public class AccountState {
    @Getter
    private final String clientName;
    private final HashMap<Currency, Integer> currencies;

    AccountState(String clientName, HashMap<Currency, Integer> currencies) {
        this.clientName = clientName;
        this.currencies = new HashMap<>(currencies);
    }

    HashMap<Currency, Integer> getCurrencies() {
        return new HashMap<>(currencies);
    }
}
