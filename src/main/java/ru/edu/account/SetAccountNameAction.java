package ru.edu.account;

import ru.edu.Modifable;

public class SetAccountNameAction implements Modifable {
    private Account account;
    private String clientName;
    private String prevClientName;

    public SetAccountNameAction(Account account, String clientName) {
        this.account = account;
        this.clientName = clientName;
    }

    @Override
    public void modify() {
        prevClientName = account.getClientName();
        account.setClientName(clientName);
    }

    @Override
    public void undo() {
        account.setClientName(prevClientName);
    }
}
